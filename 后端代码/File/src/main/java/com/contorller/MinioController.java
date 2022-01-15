package com.contorller;

import com.config.formatFileSize;
import com.pojo.LoginInfo;
import com.pojo.StatusCode;
import com.receive.ReceiveBody;
import com.service.MinioService;
import io.minio.*;
import io.minio.messages.Item;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@CrossOrigin
@RestController
@Slf4j
public class MinioController {
    @Autowired
    private MinioClient minioClient;

    @Value("${minio.bucketName}")
    private String bucketName;

    @Autowired
    private MinioService minioService;
    //获取当前桶里的内容
    @GetMapping("/list")
    public com.pojo.Result<Object> list() throws Exception {
        //获取bucket内容列表
        try {
            Iterable<Result<Item>> myObjects = minioClient.listObjects(
                    ListObjectsArgs.builder().bucket(bucketName).build());
            Iterator<Result<Item>> iterator = myObjects.iterator();

            List<Object> items = new ArrayList<>();
            String format = "{'filename': '%s', 'filesize': '%s'}";
            while (iterator.hasNext()) {
                Item item = iterator.next().get();
                items.add(com.alibaba.fastjson.JSON.parseObject(
                        String.format(format, item.objectName(), formatFileSize.getSize(item.size()))
                ));
            }
            return new com.pojo.Result<> (true, StatusCode.OK, "获取列表成功", items);
        }catch(Exception e){
            return new com.pojo.Result<> (false, StatusCode.SERVE_ERROR, "服务器错误");
        }

    }

    //上传文件
    @PostMapping("/upload")
    public com.pojo.Result<Object> upload(@RequestBody ReceiveBody rec){
        if (rec.getFile() == null || rec.getFile().length == 0) {
            return new com.pojo.Result<Object> (false, StatusCode.ERROR, "文件大小为0");
        }

        int res_state = minioService.upload(rec.getFile(), rec.getAccountId());

        if (res_state == 0)
            return new com.pojo.Result<Object> (true, StatusCode.OK, "上传成功");
        else
            return new com.pojo.Result<Object> (false, StatusCode.ERROR, "上传失败");
    }


    //下载文件
    @RequestMapping("/download/{filename}")
    public void download(HttpServletResponse response, @PathVariable("filename") String filename){
        minioService.download(response, filename);
    }

    //删除文件
    @DeleteMapping("/delete/{filename}")
    public com.pojo.Result<Object> delete(@PathVariable("filename") String filename){
        try {
            minioClient.removeObject(
                    RemoveObjectArgs.builder().bucket(bucketName).object(filename).build()
            );
        }catch(Exception e){
            log.error(e.getMessage());
            return new com.pojo.Result<> (false, StatusCode.ERROR, "删除失败");
        }
        return new com.pojo.Result<> ("删除成功");
    }
}

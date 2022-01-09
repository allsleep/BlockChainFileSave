package com.contorller;

import com.config.formatFileSize;
import io.minio.ListObjectsArgs;
import io.minio.MinioClient;
import io.minio.Result;
import io.minio.messages.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@RestController
@Slf4j
public class MinioController {
    @Autowired
    private MinioClient minioClient;

    @Value("${minio.bucketName}")
    private String bucketName;

    @GetMapping("list")
    public List<Object> list() throws Exception {
        //获取bucket内容列表
        Iterable<Result<Item>> myObjects = minioClient.listObjects(
                ListObjectsArgs.builder().bucket(bucketName).build());
        Iterator<Result<Item>> iterator = myObjects.iterator();

        List<Object> items = new ArrayList<>();
        String format = "{'filename': '%s', 'filesize': '%s'}";
        while(iterator.hasNext()) {
            Item item = iterator.next().get();
            items.add(com.alibaba.fastjson.JSON.parseObject(
                    String.format(format, item.objectName(), formatFileSize.getSize(item.size()))
            ));
        }
        return items;
    }


}

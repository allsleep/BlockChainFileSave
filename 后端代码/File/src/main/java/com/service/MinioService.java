package com.service;

import com.CommonMethods.FileHash;
import com.mapper.MinioMapper;
import com.pojo.FileInfo;
import com.pojo.LoginInfo;
import io.minio.*;
import kotlin.Pair;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class MinioService {
    @Autowired
    private MinioClient minioClient;

    @Value("${minio.bucketName}")
    private String bucketName;

    @Autowired
    private MinioMapper minioMapper;
    /*
    success: 0
    failed: -1
    filePushDatabaseFailed: -2
     */
    public List upload(MultipartFile[] file, String accountId) {
        List<Map<String, Object>> list_res = new ArrayList<>();
        for (MultipartFile multipartFile : file) {
            //获取MD5值
            try{
                Map<String, Object> res = new HashMap<>();
                String fileMD5 = FileHash.getFileMD5(multipartFile.getInputStream().readAllBytes());
                FileInfo fileInfo = new FileInfo();
                fileInfo.setFileMD5(fileMD5);
                String fileId = getFileId(accountId, fileMD5);
                fileInfo.setFileId(fileId);
                fileInfo.setFileName(multipartFile.getOriginalFilename());
                fileInfo.setAccountId(accountId);
                if (!addFileInfo(fileInfo))      //文件信息入库
                    return list_res;
                fileUpload(multipartFile);  //文件上传

                res.put("fileId", fileId);
                res.put("fileMD5", fileMD5);
                list_res.add(res);
            }catch (Exception e){
                log.error(e.getMessage());
                return list_res;
            }
        }
        //打印加密后的MD5值
        return list_res;
    }

    public void download(HttpServletResponse response, String filename){
        InputStream in = null;
        try {
            //获取对象信息
            StatObjectResponse stat = minioClient.statObject(
                    StatObjectArgs.builder().bucket(bucketName).object(filename).build()
            );

            response.setContentType(stat.contentType());
            response.setHeader("Content-Disposition", "attachment;filename=" +
                    URLEncoder.encode(filename, StandardCharsets.UTF_8));

            //文件下载
            in = minioClient.getObject(
                    GetObjectArgs.builder().bucket(bucketName)
                            .object(filename).build());
            IOUtils.copy(in, response.getOutputStream());
        }catch(Exception e){
            log.error(e.getMessage());
        }finally {
            if (in != null){
                try{
                    in.close();
                }catch(IOException e){
                    log.error(e.getMessage());
                }
            }
        }
    }

    public List<FileInfo> findFileInfoByAccountId(String accountId){
        return minioMapper.findFileInfoByAccountID(accountId);
    }

    private boolean addFileInfo(FileInfo fileInfo){
        return minioMapper.addFileInfo(fileInfo);
    }

    private String getFileId(String accountId,String MD5){
        return FileHash.getFileId(accountId, MD5);
    }

    //文件上传服务器
    private void fileUpload(MultipartFile file) throws  Exception{
        String orgFileName = file.getOriginalFilename();
        //文件上传
        InputStream in = file.getInputStream();
        minioClient.putObject(
                PutObjectArgs.builder().bucket(bucketName)
                        .object(orgFileName)
                        .stream(in, file.getSize(), -1)
                        .contentType(file.getContentType())
                        .build());
        in.close();
    }
}
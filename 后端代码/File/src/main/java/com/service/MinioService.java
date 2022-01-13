package com.service;

import com.CommonMethods.FileHash;
import com.pojo.StatusCode;
import com.pojo.person.person;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
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



    /*
    success: 0
    failed: -1
     */
    public int upload(MultipartFile[] file, person aut) {
        List<person> orgFileNameList = new ArrayList<>(file.length);

        //获取MD5值
        List<Map<person, String>> secs = new ArrayList<>(file.length);

        for (MultipartFile multipartFile : file) {
            String orgFileName = multipartFile.getOriginalFilename();
            orgFileNameList.add(aut);
            //获取MD5值
            secs.add(FileHash.getFileHash(multipartFile));

            try {
                //文件上传
                InputStream in = multipartFile.getInputStream();

                minioClient.putObject(
                        PutObjectArgs.builder().bucket(bucketName)
                                .object(orgFileName)
                                .stream(in, multipartFile.getSize(), -1)
                                .contentType(multipartFile.getContentType())
                                .build());
                in.close();
            } catch (Exception e) {
                log.error(e.getMessage());
                return -1;
            }
        }
        //打印加密后的MD5值
        log.info(String.valueOf(secs));



        return 0;
    }

    private void PushFileId(List<Map<String, String>> MD5S){

    }
}
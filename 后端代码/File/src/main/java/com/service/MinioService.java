package com.service;

import com.CommonMethods.FileHash;
import com.pojo.Person;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import kotlin.Pair;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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
    public int upload(MultipartFile[] file, Person aut) {
        List<Person> orgFileNameList = new ArrayList<>(file.length);

        //获取MD5值
        List<String> secs = new ArrayList<>(file.length);
        for (MultipartFile multipartFile : file) {
            orgFileNameList.add(aut);
            //获取MD5值
            secs.add(FileHash.getFileHash(multipartFile));
            try{
                fileUpload(multipartFile);
            }catch (Exception e){
                log.error(e.getMessage());
                return -1;
            }
        }
        //打印加密后的MD5值
        Pair<Person, List<String>> MD5S = new Pair<>(aut, secs);
        PushFileId(MD5S);
        log.info(String.valueOf(secs));
        return 0;
    }

    private void PushFileId(Pair<Person, List<String>> MD5S){
        List<String> md5s = MD5S.getSecond();
        List<String> fileIds = new ArrayList<>();
        for (String var: md5s) {
            fileIds.add(FileHash.getFileId(var));
        }
    }

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
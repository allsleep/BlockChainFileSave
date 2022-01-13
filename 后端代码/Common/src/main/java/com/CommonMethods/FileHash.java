package com.CommonMethods;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class FileHash {

    public static String getFileHash(MultipartFile file) {
        File toFile = null;
        String sec = "";
        try{
            InputStream ins = file.getInputStream();        //获取MultipartFile的输入流
            sec = DigestUtils.md5Hex(ins);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sec;
    }

//    public static Map<String, String> getFileCacheId(String MD5) {
//        String fileCacheID = new String(DigestUtils.sha512(MD5));
//        Map<String, String> res = new HashMap<>();
//        res.put(MD5, fileCacheID);
//        return res;
//    }

    public static  String getFileId(String MD5){

        IdWorker idWorker = new IdWorker(0,0);

        String fileId = String.valueOf(idWorker.nextId());

        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMDD");
        fileId = format.format(now) + fileId;

        log.info("fileMD：" + MD5 +" fileId: " + fileId);
        return fileId;
    }
}

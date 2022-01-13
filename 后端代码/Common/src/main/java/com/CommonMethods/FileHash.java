package com.CommonMethods;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.SimpleFormatter;

@Slf4j
public class FileHash {
    public static Map<String, String> getFileHash(MultipartFile file) {
        File toFile = null;
        Map<String, String> res = new HashMap<>();
        try{
            InputStream ins = file.getInputStream();        //获取MultipartFile的输入流
            String filePath =  file.getOriginalFilename();
            String sec = DigestUtils.md5Hex(ins);
            res.put(filePath, sec);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static  Map<String, String> getFileId(String fileMD5){
        Map<String, String> res = new HashMap<>();

        String fileId = new String(DigestUtils.sha512(fileMD5));

        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMDD");
        fileId += format.format(now);
        log.info("fileMD：" + fileMD5 +" fileId: " + fileId);
        res.put(fileMD5, fileId);
        return res;
    }
}

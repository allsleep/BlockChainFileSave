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

    public static Map<String, String> getFileCacheId(String MD5) {
        String fileCacheID = new String(DigestUtils.sha512(MD5));
        Map<String, String> res = new HashMap<>();
        res.put(MD5, fileCacheID);
        return res;
    }

    public static  Map<String, String> getFileId(String file_cache_id){
        Map<String, String> res = new HashMap<>();
        IdWorker idWorker = new IdWorker(0,0);

        String fileId = String.valueOf(idWorker.nextId());

        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMDD");
        fileId = format.format(now) + fileId;

        log.info("fileMD：" + file_cache_id +" fileId: " + fileId);
        res.put(file_cache_id, fileId);
        return res;
    }
}

package com.CommonMethods;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

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
}

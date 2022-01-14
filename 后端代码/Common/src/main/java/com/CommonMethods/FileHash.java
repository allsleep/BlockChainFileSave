package com.CommonMethods;
import org.apache.commons.codec.digest.DigestUtils;
import java.text.SimpleDateFormat;
import java.util.Date;


public class FileHash {

    public static String getFileHash(byte[] file) {
        return DigestUtils.md5Hex(file);
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
        return fileId;
    }
}

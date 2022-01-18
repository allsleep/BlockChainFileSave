package com.CommonMethods;
import org.apache.commons.codec.digest.DigestUtils;
import java.text.SimpleDateFormat;
import java.util.Date;


public class FileHash {

    public static String getFileMD5(byte[] file) {
        return DigestUtils.md5Hex(file);
    }

//    public static Map<String, String> getFileCacheId(String MD5) {
//        String fileCacheID = new String(DigestUtils.sha512(MD5));
//        Map<String, String> res = new HashMap<>();
//        res.put(MD5, fileCacheID);
//        return res;
//    }

    public static  String getFileId(String accountId,String MD5){
        byte[] bytes = DigestUtils.sha3_224(accountId + MD5);
        return new String(bytes);
    }
}

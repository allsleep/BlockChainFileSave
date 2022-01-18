package com.fisco.controller;

import com.fisco.client.KVClient;
import com.fisco.entity.File;
import com.fisco.entity.ResponseData;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blockchain/api")
public class KVController {

    @Autowired
    private KVClient kvClient;

    @GetMapping("/verify/{fileId}")
    public ResponseData get(@PathVariable("fileId") String fileId) throws Exception {
        try {
            return ResponseData.success("查询成功", kvClient.get(fileId));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return ResponseData.error("查询失败");
    }

    @PostMapping("/upload")
    public ResponseData set(@RequestBody File file) {
        TransactionReceipt transactionReceipt = kvClient.set(file.getFileId(), file.getFileMD5());
        return ResponseData.success("新增成功", transactionReceipt);
    }
}

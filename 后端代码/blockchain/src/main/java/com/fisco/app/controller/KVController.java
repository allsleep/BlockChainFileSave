package com.fisco.app.controller;

import com.fisco.app.client.KVClient;
import com.fisco.app.entity.File;
import com.fisco.app.entity.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Classname KVController
 * @Description 通过接口调用sdk
 * @Date 2021/3/25 22:25
 * @Created by zyt
 */
@RestController
public class KVController {

    @Autowired
    private KVClient kvClient;

    @GetMapping("/get/{name}")
    public ResponseData get(@PathVariable("fileId") String fileID) throws Exception {

        return ResponseData.success(kvClient.get(fileID));
    }

    @PostMapping("/set")
    public ResponseData set(@RequestBody File file) {
        kvClient.set(file.getFileId(), file.getFileMD5());
        return ResponseData.success("新增成功");
    }
}

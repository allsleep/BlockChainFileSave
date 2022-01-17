package com.fisco.app.controller;

import com.fisco.app.client.KVClient;
import com.fisco.app.entity.File;
import com.fisco.app.entity.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/")
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

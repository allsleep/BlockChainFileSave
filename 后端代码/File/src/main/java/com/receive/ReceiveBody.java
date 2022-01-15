package com.receive;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ReceiveBody {
    private MultipartFile[] file;
    private String accountId;
}

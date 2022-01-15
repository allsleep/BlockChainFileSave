package com.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FileInfo {
    private String accountId;
    private String fileId;
    private String fileName;
    private String fileMD5;
}

package com.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Table(name = "tb_file_info")
public class FileInfo {
    @Column(name = "account_id")
    private String accountId;
    @Column(name = "file_id")
    private String fileId;
    @Column(name = "file_name")
    private String fileName;
    @Column(name = "file_md5")
    private String fileMD5;
}

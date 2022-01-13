package com.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class person {
    private String author;
    private String idCard;
    private String phoneNumber;
    private String fileName;
}

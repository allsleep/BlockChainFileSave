package com.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
@AllArgsConstructor
public class LoginInfo {
    @Column(name = "account_id")
    private String accountId;
    @Column(name = "author")
    private String author;
    @Column(name = "id_card")
    private String idCard;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "sign_time")
    private Date date;
}

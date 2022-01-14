package com.pojo;

import lombok.NoArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;


@Data
@NoArgsConstructor
@Table(name = "tb_user")
public class User {
    @Column(name = "account_id")
    private String accountId;
    
    @Column(name = "username")
    private String username;
    
    @Column(name = "password")
    private String password;
}

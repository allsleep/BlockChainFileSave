package com.receive;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("接收的body")
public class receiveBody {
    @ApiModelProperty("用户全局id")
    private String accountId;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("用户姓名")
    private String author;
    @ApiModelProperty("用户身份证号码")
    private String idCard;
    @ApiModelProperty("用户手机号码")
    private String phoneNumber;
}

package com.learn.lspringboot.chapter5.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-26 16:15
 **/
@ApiModel
@Data
public class Customer {
    @ApiModelProperty(value = "用户id")
    private Long id;
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "创建时间")
    private String createTime;
    @ApiModelProperty(value = "地址")
    private String addr;

    public Customer() {
    }

    public Customer(Long id, String username, String password, String createTime, String addr) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.createTime = createTime;
        this.addr = addr;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", createTime='" + createTime + '\'' +
                ", addr='" + addr + '\'' +
                '}';
    }
}

package com.imooc.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName UserBO
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/12/20 16:02
 * @Version 1.0
 **/
@ApiModel(value = "用户对象BO")
public class UserBO {
    @ApiModelProperty(value = "用户名",name = "username",example = "Tom",required = true)
    private String username;
    @ApiModelProperty(value = "密码",name = "password",example = "123bm322",required = true)
    private String password;
    @ApiModelProperty(value = "确认密码",name = "confirmPassword",example = "123bm322",required = true)
    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}

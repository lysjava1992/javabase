package com.imooc.controller;

import com.imooc.pojo.Users;
import com.imooc.pojo.bo.UserBO;
import com.imooc.service.UserService;
import com.imooc.util.CommonJSONResult;
import com.imooc.util.CookieUtils;
import com.imooc.util.JsonUtils;
import com.imooc.util.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName StuController
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/12/20 14:20
 * @Version 1.0
 **/
@Api(value = "注册登录", tags = "注册登录相关接口")
@RestController
@RequestMapping("passport")
public class PassportController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户名是否存在", notes = "用户名是否存在", httpMethod = "GET")
    @GetMapping("/usernameIsExist")
    public CommonJSONResult usernameIsExist(@RequestParam String username) {
        if (StringUtils.isBlank(username)) {
            return CommonJSONResult.errorMsg("用户名为空");
        }
        boolean isExist = userService.queryUsernameIsExist(username);
        if (isExist) {
            return CommonJSONResult.errorMsg("用户名已存在");
        }
        return CommonJSONResult.ok();
    }

    @ApiOperation(value = "用户注册", notes = "用户注册", httpMethod = "POST")
    @PostMapping("/regist")
    public CommonJSONResult regist(@RequestBody UserBO userBO,
                                   HttpServletResponse response,
                                   HttpServletRequest request) {
        String username = userBO.getUsername();
        String password = userBO.getPassword();
        String confirmPassword = userBO.getConfirmPassword();
        if (StringUtils.isBlank(username) ||
                StringUtils.isBlank(password) ||
                StringUtils.isBlank(confirmPassword)) {
            return CommonJSONResult.errorMsg("用户名密码不能为空");
        }
        boolean isExist = userService.queryUsernameIsExist(username);
        if (isExist) {
            return CommonJSONResult.errorMsg("用户名已存在");
        }
        if (password.length() < 6) {
            return CommonJSONResult.errorMsg("密码长度不能小于6");
        }
        if (!password.equals(confirmPassword)) {
            return CommonJSONResult.errorMsg("两次密码不一致");
        }
        Users result=userService.createUser(userBO);
        result = setNullProperty(result);
        CookieUtils.setCookie(request,response,"user",
                JsonUtils.objectToJson(result),true);
        return CommonJSONResult.ok();
    }

    @ApiOperation(value = "用户登录", notes = "用户登录", httpMethod = "POST")
    @PostMapping("/login")
    public CommonJSONResult login(@RequestBody UserBO userBO,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {
        String username = userBO.getUsername();
        String password = userBO.getPassword();
        if (StringUtils.isBlank(username) ||
                StringUtils.isBlank(password)) {
            return CommonJSONResult.errorMsg("用户名密码不能为空");
        }

        Users result = userService.queryUserForLogin(username, MD5Utils.getMD5Str(password));
        if (result == null) {
            return CommonJSONResult.errorMsg("用户名或密码错误");
        }
        result = setNullProperty(result);
        CookieUtils.setCookie(request,response,"user",
                   JsonUtils.objectToJson(result),true);
        return CommonJSONResult.ok();
    }

    @ApiOperation(value = "用户退出", notes = "用户退出", httpMethod = "POST")
    @PostMapping("/logout")
    public CommonJSONResult logout(@RequestParam String userId,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {
        CookieUtils.deleteCookie(request,response,"user");
        return CommonJSONResult.ok();
    }
    private Users setNullProperty(Users user) {
        user.setPassword(null);
        user.setRealname(null);
        user.setBirthday(null);
        user.setCreatedTime(null);
        user.setUpdatedTime(null);
        user.setEmail(null);
        return user;
    }
}

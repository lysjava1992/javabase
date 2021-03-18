package com.imooc.service;

import com.imooc.pojo.Stu;
import com.imooc.pojo.Users;
import com.imooc.pojo.bo.UserBO;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName StuService
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/12/20 14:13
 * @Version 1.0
 **/
public interface UserService {
    /**
     *  用户名是否存在
     * @param username 用户名
     * @return
     */
   public boolean queryUsernameIsExist(String username);

   public Users createUser(UserBO userBO);

   public Users queryUserForLogin(String username,String password);
 }


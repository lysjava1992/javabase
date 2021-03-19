package com.imooc.service.impl;

import com.imooc.enums.Sex;
import com.imooc.mapper.UsersMapper;
import com.imooc.pojo.Users;
import com.imooc.pojo.bo.UserBO;
import com.imooc.service.UserService;
import com.imooc.util.DateUtil;
import com.imooc.util.MD5Utils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName StuServiceImpl
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/12/20 14:16
 * @Version 1.0
 **/
@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private Sid sid;
    private static String USER_FACE="http://127.0.0.1:8088/img/default.jpg";
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryUsernameIsExist(String username) {
        Example userExample=new Example(Users.class);
        Example.Criteria userCriteria=userExample.createCriteria();
        userCriteria.andEqualTo("username",username);
        Users users=usersMapper.selectOneByExample(userExample);
        return users != null;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Users createUser(UserBO userBO) {

        Users users=new Users();
        String id=sid.nextShort();
        users.setId(id);
        users.setUsername(userBO.getUsername());
        try {
            users.setPassword(MD5Utils.getMD5Str(userBO.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        users.setNickname(userBO.getUsername());
        users.setFace(USER_FACE);
        users.setBirthday(DateUtil.stringToDate("1900-01-01"));
        users.setSex(Sex.secret.type);
        users.setCreatedTime(new Date());
        users.setUpdatedTime(new Date());
        usersMapper.insert(users);
        return users;
    }

    @Override
    public Users queryUserForLogin(String username, String password) {
        Example userExample=new Example(Users.class);
        Example.Criteria userCriteria=userExample.createCriteria();
        userCriteria.andEqualTo("username",username);
        userCriteria.andEqualTo("password",password);
        Users users=usersMapper.selectOneByExample(userExample);
        return users;
    }
}

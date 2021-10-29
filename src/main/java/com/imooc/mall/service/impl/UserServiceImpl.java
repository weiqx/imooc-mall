package com.imooc.mall.service.impl;

import com.imooc.mall.exception.ImoocMallException;
import com.imooc.mall.exception.ImoocMallExceptionEnum;
import com.imooc.mall.model.dao.UserMapper;
import com.imooc.mall.model.pojo.User;
import com.imooc.mall.service.UserService;
import com.imooc.mall.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

/**
 *
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;  // 视频没解决  spring里面没有这个对象报错，其实没问题，dao（Mapper）no bean 框架自身的问题
    // 红色下划线  报错？ 其实没错  原因：ide要求有注解  mapperscan把这个类告诉了mybatis 没有在此处声明
    // 1.在UserMapper上加上 @Repository 注解标志为资源idea就不会报错 2.还可以抑制波浪线
    @Override
    public User getUser() {
        return userMapper.selectByPrimaryKey(1);
    }

    @Override
    public void register(String userName, String password) throws ImoocMallException {
        // 查询用户名 存在？ 重名？
        User result = userMapper.selectByName(userName);
        if (result != null) {
            throw new ImoocMallException(ImoocMallExceptionEnum.NAME_EXISTED);
        }

        // 写到数据库
        User user =  new User();
        user.setUsername(userName);
        try {
            user.setPassword(MD5Utils.getMD5Str(password));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
//        user.setPassword(password);  //
        int count = userMapper.insertSelective(user);
        if (count== 0){
            throw new ImoocMallException(ImoocMallExceptionEnum.INSERT_FAILED);
        }
    }

    @Override
    public User login(String userName, String password) throws ImoocMallException {
        String md5Password = null;
        try {
            md5Password =  MD5Utils.getMD5Str(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        User user = userMapper.selectLogin(userName, md5Password);  // 用户 密码 把所有信息全部查出来了
        if (user == null) {
            throw new ImoocMallException(ImoocMallExceptionEnum.WRONG_PASSWORD);
        }
        return user;
    }

    @Override
    public void updateInformation(User user) throws ImoocMallException {
        //更新个信签名
        int updateCount = userMapper.updateByPrimaryKeySelective(user);
        if (updateCount > 1) {
            throw new ImoocMallException(ImoocMallExceptionEnum.UPDATE_FAILED);
        }
    }

    @Override
    public boolean checkAdminRole(User user) {
        //1是普通用户，2是管理员
        return user.getRole().equals(2);
    }
}

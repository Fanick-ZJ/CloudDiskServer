package com.fanick.clouddiskserver.service.impl;

import com.aliyun.oss.OSS;
import com.fanick.clouddiskserver.config.OSSConfiguration;
import com.fanick.clouddiskserver.dao.UserMapper;
import com.fanick.clouddiskserver.entity.R;
import com.fanick.clouddiskserver.entity.UserInfo;
import com.fanick.clouddiskserver.service.ICloudUserService;
import com.fanick.clouddiskserver.util.OSSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CloudUserServiceImpl implements ICloudUserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private OSS oSSClient;
    @Autowired
    private OSSConfiguration ossConfiguration;
    @Override
    public R userRegister(UserInfo userInfo) {
        R r = OSSUtil.createFolder(oSSClient, userInfo.getUserAccount() + "/", ossConfiguration.getBucketName());
        if(!r.getSuccess())return r;
        userInfo.setUserCloudRootPath(userInfo.getUserAccount() + "/");
        userInfo.setUserCloudRootPath(userInfo.getUserAccount()+"/");
        int register = userMapper.register(userInfo);
        if(register == 1){
            r.setMessage("用户创建成功");
            Map<String,Object> map = new HashMap<>();
            map.put("userInfo",userInfo);
        }else{
            r.setMessage("用户创建失败");
            r.setSuccess(false);
        }
        return r;
    }

    @Override
    public UserInfo userLoginByAccount(UserInfo userInfo) {
        UserInfo user = userMapper.loginByAccount(userInfo);
        return user;
    }

    @Override
    public UserInfo userLoginByEmail(UserInfo userInfo) {
        return userMapper.loginByAccount(userInfo);
    }

    @Override
    public UserInfo getUseryEmail(String email) {
        UserInfo user = userMapper.getUseryEmail(email);
        return user;
    }

    @Override
    public UserInfo getUserByAccount(String account) {
        UserInfo user = userMapper.getUserByAccount(account);
        return user;
    }
}

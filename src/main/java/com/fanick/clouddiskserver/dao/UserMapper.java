package com.fanick.clouddiskserver.dao;

import com.fanick.clouddiskserver.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    int register(UserInfo userInfo);
    UserInfo loginByAccount(UserInfo userInfo);
    UserInfo loginByEmail(UserInfo userInfo);
    UserInfo getUseryEmail(String email);
    UserInfo getUserByAccount(String account);
}

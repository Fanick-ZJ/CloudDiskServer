package com.fanick.clouddiskserver.service;

import com.fanick.clouddiskserver.entity.R;
import com.fanick.clouddiskserver.entity.UserInfo;

public interface ICloudUserService {
    R userRegister(UserInfo userInfo);
    UserInfo userLoginByAccount(UserInfo userInfo);
    UserInfo userLoginByEmail(UserInfo userInfo);
    UserInfo getUseryEmail(String email);
    UserInfo getUserByAccount(String account);

}

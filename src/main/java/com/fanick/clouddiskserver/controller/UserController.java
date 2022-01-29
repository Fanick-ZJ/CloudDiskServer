package com.fanick.clouddiskserver.controller;

import com.fanick.clouddiskserver.entity.R;
import com.fanick.clouddiskserver.entity.UserInfo;
import com.fanick.clouddiskserver.service.impl.CloudUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cloudedisk/user")
public class UserController {
    @Autowired
    private CloudUserServiceImpl cloudUserService;

    //注册
    @PostMapping("/register")
    public R userRegister(@RequestBody UserInfo userInfo){
        R r = cloudUserService.userRegister(userInfo);
        return r;
    }

    //查找账号是否存在
    @GetMapping("/accountExist")
    public R accountExist(@RequestParam("account") String account){
        UserInfo user = cloudUserService.getUserByAccount(account);
        R r;
        if(user!=null){
            r = R.error();
            r.setMessage("账号已存在，请更换");
        }else{
            r = R.ok();
            r.setMessage("当前账号还无人注册");
        }
        return r;
    }

    //查找邮箱是否存在
    @GetMapping("/emailExist")
    public R emailExist(@RequestParam("email") String email){
        UserInfo user = cloudUserService.getUseryEmail(email);
        R r;
        if(user!=null){
            r = R.error();
            r.setMessage("邮箱已存在，请更换");
        }else{
            r = R.ok();
            r.setMessage("当前邮箱还无人注册");
        }
        return r;
    }
    //登录
    @GetMapping ("/login")
    public R userLoginByAccount(@RequestParam("accountOrEmail") String accountOrEmail , @RequestParam( "userPassword") String password){
        R r = null;
        if(accountOrEmail.contains("@")){
            UserInfo user = new UserInfo();
            user.setUserEmail(accountOrEmail);
            user.setUserPassword(password);
            user = cloudUserService.userLoginByEmail(user);
            r = user!=null? R.ok():R.error();
            if(!r.getSuccess())r.setMessage("邮箱或密码错误");
            Map<String ,Object> map = new HashMap<>();
            map.put("userInfo",user);
            r.setData(map);
        }else{

            UserInfo user = new UserInfo();
            user.setUserAccount(accountOrEmail);
            user.setUserPassword(password);
            user = cloudUserService.userLoginByAccount(user);
            r = user!=null? R.ok():R.error();
            if(!r.getSuccess())r.setMessage("账号或密码错误");
            Map<String ,Object> map = new HashMap<>();
            map.put("userInfo",user);
            r.setData(map);
        }
        return r;
    }
}

package com.fanick.clouddiskserver.entity;


import com.fanick.clouddiskserver.entity.enums.ResultCode;

import java.util.HashMap;
import java.util.Map;

//统一返回结果的类
public class R {
    //请求是否成功
    private Boolean success;

    //返回码
    private Integer code;

    //返回消息
    private String message;

    //返回数据
    private Map<String ,Object> data = new HashMap<>();

    //将构造方法私有化
    private R(){}

    //成功的静态方法
    public static R ok(){
        R r = new R();
        r.setCode(ResultCode.SUCCESS);
        r.setSuccess(true);
        r.setMessage("成功");
        return r;
    }

    //失败的静态方法
    public static R error(){
        R r = new R();
        r.setCode(ResultCode.ERROR);
        r.setMessage("失败");
        r.setSuccess(false);
        return r;
    }


    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}

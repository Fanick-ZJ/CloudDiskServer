package com.fanick.clouddiskserver;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.ListObjectsRequest;
import com.aliyun.oss.model.OSSObjectSummary;
import com.fanick.clouddiskserver.config.OSSConfiguration;
import com.fanick.clouddiskserver.entity.UserInfo;
import com.fanick.clouddiskserver.service.impl.CloudUserServiceImpl;
import com.fanick.clouddiskserver.util.OSSUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class CloudDiskServerApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private CloudUserServiceImpl registerService;
    @Test
    public void register(){
        UserInfo userInfo = new UserInfo();
        //userInfo.setId(null);
        userInfo.setRegisterDate(new Date());
        userInfo.setUserAccount("account");
        userInfo.setUserNickname("asd");
        userInfo.setUserPassword("password");
        userInfo.setUserEmail("email");
        userInfo.setUserCloudRootPath("root");
        registerService.userRegister(userInfo);
    }
    @Autowired
    private OSS oSSClient;
    @Autowired
    private OSSConfiguration ossConfiguration;
    @Test
    public void osstest(){
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest(ossConfiguration.getBucketName());
        listObjectsRequest.setMaxKeys(10);
        List<OSSObjectSummary> files = OSSUtil.getFiles(oSSClient, listObjectsRequest);
        //System.out.println(files.size());
        for (OSSObjectSummary file : files) {
            System.out.println(file.getKey());
        }
        oSSClient.shutdown();

    }

}

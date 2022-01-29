package com.fanick.clouddiskserver.service.impl;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ListObjectsRequest;
import com.fanick.clouddiskserver.entity.OSSEntity.MyObject;
import com.fanick.clouddiskserver.service.ICloudOssService;

import java.util.List;

public class CloudOssServiveImpl implements ICloudOssService {
    @Override
    public List<MyObject> getAllFile(OSSClient oss, ListObjectsRequest listObjectsRequest, int userID) {
        return null;
    }

    @Override
    public boolean createFolder(String path) {
        return false;
    }
}

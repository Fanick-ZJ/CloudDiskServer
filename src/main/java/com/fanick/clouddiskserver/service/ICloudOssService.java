package com.fanick.clouddiskserver.service;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ListObjectsRequest;
import com.fanick.clouddiskserver.entity.OSSEntity.MyObject;
import com.fanick.clouddiskserver.entity.R;

import java.io.File;
import java.util.List;

public interface ICloudOssService {
    List<MyObject> getAllFile(OSSClient oss, ListObjectsRequest listObjectsRequest,int userID);
    boolean createFolder(String path);
}

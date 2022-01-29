package com.fanick.clouddiskserver.entity.OSSEntity;

import com.aliyun.oss.model.OSSObjectSummary;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MyBucket {
    private List<OSSObjectSummary> filePath;
    private int fileNum;
    public MyBucket(){
        filePath = new ArrayList<OSSObjectSummary>();
    }

}

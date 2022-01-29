package com.fanick.clouddiskserver.entity.OSSEntity;

import com.aliyun.oss.model.OSSObjectSummary;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class MyObject {
    private boolean isFolder;
    private long size;
    private String path;
    private Date lastModified;
    private String objectName;
    private String objectType;
    private List<OSSObjectSummary> subFileList;

    public MyObject(){
        subFileList = new ArrayList<>();
    }
}

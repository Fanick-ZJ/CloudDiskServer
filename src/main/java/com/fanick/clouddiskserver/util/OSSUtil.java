package com.fanick.clouddiskserver.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.*;
import com.fanick.clouddiskserver.config.OSSConfiguration;
import com.fanick.clouddiskserver.entity.OSSEntity.MyBucket;
import com.fanick.clouddiskserver.entity.OSSEntity.MyObject;
import com.fanick.clouddiskserver.entity.R;
import com.fanick.clouddiskserver.entity.enums.MyBucketInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class OSSUtil {

    public static int getBucketFileNum(OSS oss, ListObjectsRequest listObjectsRequest){
        return getFileNum(oss,listObjectsRequest);
    }

    private static int getFileNum(OSS oss, ListObjectsRequest listObjectsRequest){
        return getBucketInfo(MyBucketInfo.FILENUM,oss,listObjectsRequest).getFileNum();
    }
    private static MyBucket getBucketInfo(MyBucketInfo bucketInfo, OSS oss, ListObjectsRequest request){
        MyBucket myBucket = new MyBucket();
        ObjectListing objectListing  = null;
        List<OSSObjectSummary> objectSummaries = null;
        objectListing = oss.listObjects(request);

        int num = 0;
        do {
            //获得objectListing中的文件列表
            objectSummaries = objectListing.getObjectSummaries();
            //将文件写入列表中
            if(bucketInfo == MyBucketInfo.FILEPATH)myBucket.getFilePath().addAll(objectSummaries);
            //设置marker，记录目前筛选出来的最后一个文件，下次从这个文件向下筛选
            String markerKey = objectSummaries.get(objectSummaries.size()-1).getKey();
            //重新获得ObjectListing列表
            objectListing = oss.listObjects(request.withMarker(markerKey));
        }while(objectSummaries.size() == request.getMaxKeys());
        myBucket.setFileNum(num);
        return myBucket;
    }


    public static List<OSSObjectSummary> getFiles(OSS oss, ListObjectsRequest request){
        MyBucket bucketInfo = getBucketInfo(MyBucketInfo.FILEPATH, oss, request);
        return bucketInfo.getFilePath();
    }

    public static List<SimplifiedObjectMeta> getObjectMate(OSS oss,String bucketName,List<OSSObjectSummary> objectSummaries){
        List<SimplifiedObjectMeta> list = new ArrayList<>();
        for (OSSObjectSummary objectSummary : objectSummaries) {
            list.add(oss.getSimplifiedObjectMeta(bucketName,objectSummary.getKey()));
        }
        return list;
    }

    public static SimplifiedObjectMeta getObjectMate(OSS oss,GenericRequest request){
        SimplifiedObjectMeta simplifiedObjectMeta = oss.getSimplifiedObjectMeta(request);
        return simplifiedObjectMeta;
    }

    public static List<MyObject> getObjectCollection(List<OSSObjectSummary> list, int folderLevel){
        List<MyObject> objectsCollection = new ArrayList<>();
        //当前文件所在层级
        for (int i = 0 ; i < list.size() ; i++) {
            OSSObjectSummary objectSummary = list.get(i);
            MyObject myObject;
            String key = objectSummary.getKey();
            String[] keySplit = key.split("/");//根据/分段
            if(keySplit.length<=folderLevel)continue;
            if(objectsCollection.stream().
                    filter(myObject1 -> myObject1.getObjectName().equals(keySplit[folderLevel])).
                    count() == 0){//在objectsCollection中没有找到文件夹
                myObject = new MyObject();
                //判断不是文件夹
                    if(objectSummary.getSize()>0 && keySplit.length-folderLevel == 1){
                        String objectName = keySplit[keySplit.length-1];
                        String objectType = objectName.substring(objectName.lastIndexOf("."));
                        String path = "";
                        for(int j = 0 ; j < folderLevel ; j++)path+=keySplit[j];
                        myObject.setFolder(false);
                        myObject.setObjectName(objectName);
                        myObject.setSize(objectSummary.getSize());
                        myObject.setPath(path);
                        myObject.setLastModified(list.get(i).getLastModified());
                        myObject.setObjectType(objectType);
                        objectsCollection.add(myObject);
                    }//判断是文件夹
                    else if(keySplit.length-folderLevel >= 0){
                        String path = "";
                        for(int j = 0 ; j < folderLevel ; j++)path+=keySplit[j];
                        myObject.setFolder(true);
                        myObject.setObjectName(keySplit[folderLevel]);
                        myObject.setSize(0);
                        myObject.setLastModified(list.get(i).getLastModified());
                        myObject.setPath(path);
                        myObject.setObjectType("Folder");
                        objectsCollection.add(myObject);
                        String[] nextKey = null;
                        do{
                            myObject.getSubFileList().add(list.get(i));
                            nextKey = list.get(++i).getKey().split("/");
                        }while((nextKey.length>= keySplit.length)&&(nextKey[folderLevel].equals(keySplit[folderLevel])));
                        i--;
                    }
            }
        }
        return objectsCollection;
    }

    public static R createFolder(OSS oss,String path,String bucketname){
        System.out.println(System.getProperty("user.dir"));
        File file = new File(System.getProperty("user.dir"),"test");
        R r;
        try{
            file.createNewFile();
            oss.putObject(bucketname,path+"/",file);
        }catch (Exception e){
            e.printStackTrace();
            r = R.error();
            r.setMessage("文件夹创建失败");
            return r;
        }
        file.delete();
        r = R.ok();
        r.setMessage("创建成功");
        return r;
    }
}

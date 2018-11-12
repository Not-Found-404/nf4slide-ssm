package com.qtu404.util.oss;

import com.aliyun.oss.OSSClient;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

public class OSSHelper {
    // nf4-slide-img
    private static final String accessKeyId = "LTAICksVS7MiueZQ";
    private static final String accessKeySecret = "h9OJs03EdyVm7OLuyrKN7mMfe06K8Q";
    private static final String endpoint = "http://oss-cn-qingdao.aliyuncs.com";
    private static final String bucketName = "qtu-404";
    private static final String website = "http://static.qtu404.com/";
    private static final String fileFolder = "nf4slide/slide-img/";
    private static OSSClient ossClient;

    public static void main(String[] args) throws FileNotFoundException {
//        FileInputStream inputStream = new FileInputStream(new File("/Users/admin/Desktop/软件151丁星创新创业实践学分申请.zip"));
//        ossClient.putObject(bucketName, "学分.zip", inputStream);
//        ossClient.shutdown();1
    }

    public static String upLoadImgToOSS(InputStream file, String fileName) {
        ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        fileName = new Date().getTime() + fileName;
        ossClient.putObject(bucketName, fileFolder + fileName, file);
        ossClient.shutdown();
        return website + fileFolder + fileName;
    }
}

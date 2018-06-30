package com.qtu404.util.poi;

import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class ImageDeal {
    private final static String userAvatorUrl = "avator";
    private final static String avatorName = "user.jpg";

    public static String getUserAvatorUrl(Integer userId) {
        return userAvatorUrl + File.separator + userId + File.separator + avatorName;
    }

    public static String getAvatorPath(String rootPath, Integer userId) {
        String avatprImageFilePath = rootPath + userAvatorUrl + File.separator + userId + File.separator;
        File saveFile = new File(avatprImageFilePath);
        if (!saveFile.exists()) saveFile.mkdirs();
        return avatprImageFilePath + avatorName;
    }

    //base64字符串转化成图片
    public static boolean GenerateImage(String imgStr, String imgPath) {   //对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) //图像数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            //Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {//调整异常数据
                    b[i] += 256;
                }
            }
            //生成jpeg图片
            OutputStream out = new FileOutputStream(imgPath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}

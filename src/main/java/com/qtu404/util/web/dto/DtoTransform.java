package com.qtu404.util.web.dto;

import com.qtu404.util.web.dto.handler.DtoHandler;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class DtoTransform {
    DtoHandler dtoHandler = new DtoHandler();

    /**
     * 使用反射机制，构造一个dto
     *
     * @param tClass
     * @param map
     * @return
     */
    public Object dtoTransform(Class tClass, Map map) {

        Object obj = null;
        try {
            obj = tClass.newInstance();
            Field[] fields = tClass.getDeclaredFields();
            String cname = tClass.getSimpleName().toLowerCase();

            //循环各个字段
            for (Field field : fields) {
                Class fClass = field.getType();

                //获得写方法
                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), tClass);
                Method wM = pd.getWriteMethod();

                //得到值并写入
                String value = (String) map.get(cname + "." + field.getName());
                if (value == null) {
                    value = (String) map.get(field.getName());
                }
                Object valueObject = null;

                if (value != null) {
                    valueObject = dtoHandler.getValue(fClass, value);
                }
                wM.invoke(obj, valueObject);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }


}





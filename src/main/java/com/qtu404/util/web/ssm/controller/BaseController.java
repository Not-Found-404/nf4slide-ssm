package com.qtu404.util.web.ssm.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qtu404.util.web.Result;
import com.qtu404.util.web.dto.DtoTransform;
import com.qtu404.util.web.ssm.service.BaseService;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class BaseController<T> {
    protected abstract BaseService<T> getBaseService();

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 通过id得到实体json数据
     * @param id       实体id
     * @param response
     */
    @RequestMapping("/fetchById")
    public void fetchByIdJSON(int id, HttpServletResponse response) {
        PrintWriter out = null;
        ObjectMapper ob = null;
        T t = getBaseService().fetchById(id);
        try {
            out = response.getWriter();
            ob = new ObjectMapper();

            String jsonStr = ob.writeValueAsString(t);
            out.write(jsonStr);

            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 得到实体所有对象的json字符串
     *
     * @param response
     */
    @RequestMapping("/findAll")
    public void findAll(HttpServletResponse response) {
        PrintWriter out = null;
        ObjectMapper ob = null;
        List<T> tList = getBaseService().findAll();
        try {
            ob = new ObjectMapper();
            out = response.getWriter();

            String jsonStr = ob.writeValueAsString(tList);
            out.write(jsonStr);

            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加一个实体
     *
     * @param response
     */
    @RequestMapping("save")
    public void saveJSON(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = null;
        ObjectMapper ob = null;
        String jsonStr = null;
        Result result = new Result();
        T t = getDtoObject(request);
        try {
            T rst = getBaseService().save(t);
            ob = new ObjectMapper();
            out = response.getWriter();

            if (rst == null) {
                result.setResult("save success");
                result.setCode(200);
            } else {
                result.setResult("save fail");
                result.setCode(500);
            }

            writeResult(response, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改一个实体
     *
     * @param t
     * @param response
     */
    @RequestMapping("modify")
    public void modifyJSON(T t, HttpServletResponse response) {
        PrintWriter out = null;
        ObjectMapper ob = null;
        String jsonStr = null;
        Result result = new Result();

        try {
            int rst = getBaseService().modify(t);
            ob = new ObjectMapper();
            out = response.getWriter();

            if (rst == 1) {
                result.setResult("modify success");
                result.setCode(200);
            } else {
                result.setResult("modify fail");
                result.setCode(500);
            }

            jsonStr = ob.writeValueAsString(result);
            out.write(jsonStr);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除一个实体
     *
     * @param t
     * @param response
     */
    @RequestMapping("delete")
    public void deleteJSON(T t, HttpServletResponse response) {
        PrintWriter out = null;
        ObjectMapper ob = null;
        String jsonStr = null;
        Result result = new Result();

        try {
            int rst = getBaseService().delete(t);
            ob = new ObjectMapper();
            out = response.getWriter();

            if (rst == 1) {
                result.setResult("delete success");
                result.setCode(200);
            } else {
                result.setResult("delete fail");
                result.setCode(500);
            }

            jsonStr = ob.writeValueAsString(result);
            out.write(jsonStr);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置返回json的hander
     */
    protected void setJSONTypeResponeHander(HttpServletResponse response) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
    }

    /**
     * 转换dto
     *
     * @param request
     * @return
     */
    protected T getDtoObject(HttpServletRequest request) {
        Class<T> tClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        DtoTransform dtoTransform = new DtoTransform();

        Map map = getParameterMap(request);
        T t = (T) dtoTransform.dtoTransform(tClass, map);

        return t;
    }

    /**
     * 从request中获得参数Map，并返回可读的Map
     *
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map getParameterMap(HttpServletRequest request) {
        // 参数Map
        Map properties = request.getParameterMap();
        // 返回值Map
        Map returnMap = new HashMap();
        Iterator entries = properties.entrySet().iterator();
        Map.Entry entry;
        String name = "";
        String value = "";
        while (entries.hasNext()) {
            entry = (Map.Entry) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if (null == valueObj) {
                value = "";
            } else if (valueObj instanceof String[]) {
                String[] values = (String[]) valueObj;
                for (int i = 0; i < values.length; i++) {
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length() - 1);
            } else {
                value = valueObj.toString();
            }
            returnMap.put(name, value);
        }
        return returnMap;
    }

    /**
     * 从response写回json字符串
     *
     * @param response
     * @param result
     */
    protected void writeResult(HttpServletResponse response, Object result) {
        //设置返回头
        setJSONTypeResponeHander(response);
        String rst = null;
        try {
            rst = objectMapper.writeValueAsString(result);
            PrintWriter out = response.getWriter();
            out.write(rst);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
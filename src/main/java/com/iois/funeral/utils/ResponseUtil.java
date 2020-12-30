package com.iois.funeral.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: hesheng
 * @create: 2020-12-2020/12/30
 * @description:
 **/
public class ResponseUtil {
    /**
    * @Description: 封装API调用返回值
    * @Param: [code, msg]
    * @Return: java.util.Map<java.lang.String,java.lang.Object>
    * @Author: hesheng
    * @Date: 2020/12/30
    */
    public static Map<String,Object> response(int code, String msg){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("code", code);
        map.put("msg", msg);
        return map;
    }

    /**
     * @Description: 封装API调用返回值
     * @Param: [code, msg, data]
     * @Return: java.util.Map<java.lang.String,java.lang.Object>
     * @Author: hesheng
     * @Date: 2020/12/30
     */
    public static Map<String,Object> response(int code,String msg,Object data){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("code", code);
        map.put("msg", msg);
        map.put("data",data);
        return map;
    }
}

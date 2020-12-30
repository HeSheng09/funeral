package com.iois.funeral.controller;

import com.alibaba.fastjson.JSON;
import com.iois.funeral.entity.Dead;
import com.iois.funeral.entity.Person;
import com.iois.funeral.entity.User;
import com.iois.funeral.service.UserService;
import com.iois.funeral.utils.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import static com.iois.funeral.utils.ResponseUtil.response;

/**
 * @author: hesheng
 * @create: 2020-12-2020/12/30
 * @description:
 **/
@RestController
@RequestMapping("/route")
public class RouteController {
    @Resource
    private UserService userService;

    @RequestMapping("/check_dead_stage")
    public Map<String,Object> checkDeadStage(@RequestParam(name = "uid",defaultValue = "000000000000000000")String uid){
        Map<String,Object> res=new HashMap<String,Object>();
        User user=userService.getOneUserById(uid);

        if(user!=null){
            if(user.getDead()!=null){
                Map<String,Object> map=new HashMap<>();
                map.put("stage",user.getDead().getStage());
                res= response(200,"ok",map);
            }else {
                res=response(500,"failed: no dead");
            }
        }else{
            res=response(500, "failed: no such user");
        }
//        try{
//            URL url=new URL("https://run.mocky.io/v3/96443514-5a92-428b-899d-7bfcc256693d");
//            URLConnection urlConnection=url.openConnection();
//            HttpURLConnection connection = null;
//            if(urlConnection instanceof HttpURLConnection)
//            {
//                connection = (HttpURLConnection) urlConnection;
//                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                StringBuilder urlString = new StringBuilder();
//                String current;
//                while((current = in.readLine()) != null)
//                {
//                    urlString.append(current);
//                }
//                Person person=JSON.parseObject(urlString.toString(), Person.class);
//                System.out.println(person);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        res=response(200, "ok");
        return res;
    }

    /**
     * @Description: 封装API调用返回值
     * @Param: [code, msg]
     * @Return: java.util.Map<java.lang.String,java.lang.Object>
     * @Author: hesheng
     * @Date: 2020/12/30
     */
//    public Map<String,Object> response(int code,String msg){
//        Map<String,Object> map=new HashMap<String,Object>();
//        map.put("code", code);
//        map.put("msg", msg);
//        return map;
//    }

    /**
     * @Description: 封装API调用返回值
     * @Param: [code, msg, data]
     * @Return: java.util.Map<java.lang.String,java.lang.Object>
     * @Author: hesheng
     * @Date: 2020/12/30
     */
//    public Map<String,Object> response(int code,String msg,Object data){
//        Map<String,Object> map=new HashMap<String,Object>();
//        map.put("code", code);
//        map.put("msg", msg);
//        map.put("data",data);
//        return map;
//    }
}

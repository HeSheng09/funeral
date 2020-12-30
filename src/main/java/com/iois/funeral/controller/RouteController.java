package com.iois.funeral.controller;

import com.alibaba.fastjson.JSON;
import com.iois.funeral.entity.Dead;
import com.iois.funeral.entity.Person;
import com.iois.funeral.entity.User;
import com.iois.funeral.service.RouteService;
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
    @Resource
    private RouteService routeService;

    /**
    * @Description: 获取死者后事处理状态
    * @Param: [uid]
    * @Return: java.util.Map<java.lang.String,java.lang.Object>
    * @Author: hesheng
    * @Date: 2020/12/30
    */ 
    @RequestMapping("/check_dead_stage")
    public Map<String,Object> checkDeadStage(@RequestParam(name = "uid",defaultValue = "000000000000000000")String uid){
        Map<String,Object> res=new HashMap<String,Object>();
        User user=userService.getOneUserById(uid);

        if(user!=null){
            if(user.getDead()!=null){
                int stage=routeService.updateDeadStage(user);
                Map<String,Object> map=new HashMap<>();
                map.put("stage",stage);
                res= response(200,"ok",map);
            }else {
                res=response(500,"failed: no dead");
            }
        }else{
            res=response(500, "failed: no such user");
        }
        return res;
    }

}

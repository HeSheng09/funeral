package com.iois.funeral.controller;

import com.iois.funeral.entity.Dead;
import com.iois.funeral.entity.User;
import com.iois.funeral.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

import static com.iois.funeral.utils.ResponseUtil.response;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    /**
    * @Description: 登录
    * @Param: [uid, password]
    * @Return: java.util.Map<java.lang.String,java.lang.Object>
    * @Author: hesheng
    * @Date: 2020/12/30
    */ 
    @RequestMapping("/login")
    public Map<String,Object> login(@RequestParam(name = "uid",defaultValue = "000000000000000000")String uid,
                                    @RequestParam(name = "password",defaultValue = "invalid_name")String password){
        Map<String,Object> res=new HashMap<String,Object>();
        User user=userService.getOneUserById(uid);
        if(user==null){
            res=response(400,"failed: login");
        }else{
            if(user.getPassword().equals(password)){
                res=response(200, "ok");
            }else {
                res=response(400,"failed: login");
            }
        }
        return res;
    }

    /**
    * @Description: 根据用户uid查询相关信息
    * @Param: [uid]
    * @Return: java.util.Map<java.lang.String,java.lang.Object>
    * @Author: hesheng
    * @Date: 2020/12/30
    */
    @RequestMapping("/get_one_user")
    public Map<String,Object> getOneUser(@RequestParam(name = "uid",defaultValue = "000000000000000000")String uid){
        Map<String,Object> res=new HashMap<String,Object>();
        if("000000000000000000".equals(uid)||uid.length()!=18){
            res=response(500, "failed: invalid uid.");
        }else{
            try{
                User user=userService.getOneUserById(uid);
                res=response(200,"ok",user);
            }catch (Exception e){
                e.printStackTrace();
                res=response(500,"failed: unknown server error");
            }
        }
        return res;
    }

    /**
    * @Description: 新建一个用户
    * @Param: [uid, name, password, dead_id]
    * @Return: java.util.Map<java.lang.String,java.lang.Object>
    * @Author: hesheng
    * @Date: 2020/12/30
    */
    @RequestMapping("/add_one_user")
    public Map<String,Object> addOneUser(@RequestParam(name = "uid", defaultValue = "000000000000000000") String uid,
                          @RequestParam(name = "name", defaultValue = "invalid_name") String name,
                          @RequestParam(name = "password", defaultValue = "invalid_password") String password,
                          @RequestParam(name = "dead_id", defaultValue = "000000000000000000") String dead_id) {
        Map<String,Object> map=new HashMap<String,Object>();
        Dead dead=new Dead(dead_id, 0);
        User user = new User(uid, name, password, dead);
//        System.out.println(user.toString());

        if(user.validation()){
            if(userService.addOneUser(user)){
                map=response(200, "succeeded: add one user.");
            }else{
                map=response(500, "failed: add one user.");
            }
        }else{
            map=response(500, "failed: add one user.");
        }
        return map;
    }

    /**
    * @Description: 新建一个User（未关联死者信息）
    * @Param: [uid, name, password]
    * @Return: java.util.Map<java.lang.String,java.lang.Object>
    * @Author: hesheng
    * @Date: 2020/12/30
    */ 
    @RequestMapping("/add_user_only")
    public Map<String,Object> addUserOnly(@RequestParam(name = "uid",defaultValue = "000000000000000000")String uid,
                                          @RequestParam(name = "name",defaultValue = "invalid_name")String name,
                                          @RequestParam(name="password",defaultValue = "000000000000000000")String password){
        User user=new User(uid,name,password);
        Map<String,Object> res=new HashMap<>();
        if(user.validation()){
            if(userService.addOnlyUser(user)){
                res=response(200,"ok");
            }else{
                res=response(500,"failed: add only user");
            }
        }else{
            res=response(500,"failed: invalid params");
        }
        return res;
    }

    /**
    * @Description: 关联死者信息到用户
    * @Param: [uid, dead_id]
    * @Return: java.util.Map<java.lang.String,java.lang.Object>
    * @Author: hesheng
    * @Date: 2020/12/30
    */ 
    @RequestMapping("/attach_dead_to_user")
    public Map<String,Object> attachDead2User(@RequestParam(name = "uid",defaultValue = "000000000000000000")String uid,
                                              @RequestParam(name = "dead_id",defaultValue = "000000000000000000")String dead_id){
        Map<String,Object> res=new HashMap<>();
        User user=userService.getOneUserById(uid);
        if(user!=null){
            Dead dead=new Dead(dead_id, 0);
            if(dead.validation()){
                user.setDead(dead);
                if(userService.attachDead2User(user)){
                    res=response(200,"ok");
                }else {
                    res=response(500,"failed: attach dead to user");
                }
            }else {
                res=response(500,"failed: invalid dead uid");
            }
        }else {
            res=response(500,"failed: no such user");
        }
        return res;
    }

}

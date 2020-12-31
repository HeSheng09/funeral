package com.iois.funeral.service.Impl;

import com.alibaba.fastjson.JSON;
import com.iois.funeral.dao.UserDao;
import com.iois.funeral.entity.Dead;
import com.iois.funeral.entity.Person;
import com.iois.funeral.entity.RelationUserDead;
import com.iois.funeral.entity.User;
import com.iois.funeral.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public User getOneUserById(String uid) {
        User user = userDao.getUserById(uid);

        if (user != null) {
            List<RelationUserDead> relations = userDao.getRelByUserId(uid);
            if (!relations.isEmpty()) {
                //暂时不考虑一位用户同多位死者相关联的情况
                Dead dead = userDao.getDeadById(relations.get(0).getDead_id());
                user.setDead(dead);
            }
        }

        return user;
    }

    @Override
    public boolean addOnlyUser(User user) {
        if (this.getOneUserById(user.getUid()) != null) {
            // 用户已经存在
            return false;
        }
        try {
            userDao.addOneUser(user);
        } catch (Exception e) {
            try {
                userDao.dropOneUser(user.getUid());
            } catch (Exception ignored) {
            }
            return false;
        }
        return true;
    }

    @Override
    public boolean attachDead2User(User user) {
        User inDbUser = this.getOneUserById(user.getUid());
        if (inDbUser == null) {
            // 用户不存在
            return false;
        } else if(inDbUser.getDead()!=null && inDbUser.getDead().getStage() > 0){
            // 暂时不考虑一位用户同多位死者相关联的情况,已经关联一名死者不能再关联死者
            return false;
        }

        // check relation
        // if ok: stage++
        // else: return false
        if(checkRelation(user)){
            user.getDead().setStage(1);
        }else {
            return false;
        }
        // location stage
        // <expression>

        try {
            userDao.addOneDead(user.getDead());
            userDao.addOneRel(new RelationUserDead(user.getUid(), user.getDead().getUid()));
        } catch (Exception e) {
            try {
                userDao.dropOneRel(new RelationUserDead(user.getUid(), user.getDead().getUid()));
                userDao.dropOneDead(user.getDead().getUid());
            } catch (Exception ignored) {
            }
            return false;
        }
        return true;
    }

    @Override
    public boolean addOneUser(User user) {
        if (this.getOneUserById(user.getUid()) != null) {
            // 暂时不考虑一位用户同多位死者相关连的情况。如果该用户已经存在，则不能新增用户。
            return false;
        }
        try {
            userDao.addOneUser(user);
            userDao.addOneDead(user.getDead());
            userDao.addOneRel(new RelationUserDead(user.getUid(), user.getDead().getUid()));
        } catch (Exception e) {
            try {
                userDao.dropOneRel(new RelationUserDead(user.getUid(), user.getDead().getUid()));
                userDao.dropOneDead(user.getDead().getUid());
                userDao.dropOneUser(user.getUid());
            } catch (Exception ignored) {
            }
            return false;
        }
        return true;
    }

    private boolean checkRelation(User user){
        String url="https://run.mocky.io/v3/96443514-5a92-428b-899d-7bfcc256693d";
        // get user information
        Person userInfo=getPersonInformation(user.getUid(),url);
        // get dead information
        Person deadInfo=getPersonInformation(user.getDead().getUid(),url);
        // check relation
        //<expression>


        return false;
    }
    
    private Person getPersonInformation(String uid, String url_address){
        Person person = null;
        try{
            URL url=new URL(url_address+"?uid="+uid);
            URLConnection urlConnection=url.openConnection();
            HttpURLConnection connection = null;
            if(urlConnection instanceof HttpURLConnection)
            {
                connection = (HttpURLConnection) urlConnection;
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder urlString = new StringBuilder();
                String current;
                while((current = in.readLine()) != null)
                {
                    urlString.append(current);
                }
                person=JSON.parseObject(urlString.toString(),Person.class);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return person;
    }
}

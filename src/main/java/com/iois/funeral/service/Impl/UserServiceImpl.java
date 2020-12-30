package com.iois.funeral.service.Impl;

import com.iois.funeral.dao.UserDao;
import com.iois.funeral.entity.Dead;
import com.iois.funeral.entity.RelationUserDead;
import com.iois.funeral.entity.User;
import com.iois.funeral.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public User getOneUserById(String uid) {
        User user=userDao.getUserById(uid);

        if(user!=null){
            List<RelationUserDead> relations=userDao.getRelByUserId(uid);
            if(!relations.isEmpty()){
                //暂时不考虑一位用户同多位死者相关联的情况
                Dead dead=userDao.getDeadById(relations.get(0).getDead_id());
                user.setDead(dead);
            }
        }

        return user;
    }

    @Override
    public boolean addOnlyUser(User user) {
        if(this.getOneUserById(user.getUid())!=null){
            // 用户已经存在
            return false;
        }
        try{
            userDao.addOneUser(user);
        }catch (Exception e){
            try{
                userDao.dropOneUser(user.getUid());
            }catch (Exception ignored){}
            return false;
        }
        return true;
    }

    @Override
    public boolean attachDead2User(User user) {
        user=this.getOneUserById(user.getUid());
        if(user==null){
            // 用户不存在
            return false;
        }else if(user.getDead()!=null){
            // 暂时不考虑一位用户同多位死者相关联的情况,已经关联一名死者
            return false;
        }
        try{
            userDao.addOneDead(user.getDead());
            userDao.addOneRel(new RelationUserDead(user.getUid(),user.getDead().getUid()));
        }catch (Exception e){
            try{
                userDao.dropOneRel(new RelationUserDead(user.getUid(),user.getDead().getUid()));
                userDao.dropOneDead(user.getDead().getUid());
            }catch (Exception ignored){}
            return false;
        }
        return true;
    }

    @Override
    public boolean addOneUser(User user) {
        if(this.getOneUserById(user.getUid())!=null){
            // 暂时不考虑一位用户同多位死者相关连的情况。如果该用户已经存在，则不能新增用户。
            return false;
        }
        try{
            userDao.addOneUser(user);
            userDao.addOneDead(user.getDead());
            userDao.addOneRel(new RelationUserDead(user.getUid(),user.getDead().getUid()));
        }catch (Exception e){
            try{
                userDao.dropOneRel(new RelationUserDead(user.getUid(),user.getDead().getUid()));
                userDao.dropOneDead(user.getDead().getUid());
                userDao.dropOneUser(user.getUid());
            }catch (Exception ignored){}
            return false;
        }
        return true;
    }
}

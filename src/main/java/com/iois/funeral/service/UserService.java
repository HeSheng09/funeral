package com.iois.funeral.service;

import com.iois.funeral.entity.User;

public interface UserService {
    /**
    * @Description: 新增一个用户
    * @Param: [user]
    * @Return: boolean
    * @Author: hesheng
    * @Date: 2020/12/30
    */ 
    public boolean addOneUser(User user);
    
    /**
    * @Description: 根据用户uid查询一个用户详细信息
    * @Param: [uid]
    * @Return: com.iois.funeral.entity.User
    * @Author: hesheng
    * @Date: 2020/12/30
    */ 
    public User getOneUserById(String uid);
    
    /**
    * @Description: 新增一个用户（未关联死者信息）
    * @Param: [user]
    * @Return: boolean
    * @Author: hesheng
    * @Date: 2020/12/30
    */ 
    public boolean addOnlyUser(User user);

    /**
    * @Description: 关联死者信息到用户
    * @Param: [user]
    * @Return: boolean
    * @Author: hesheng
    * @Date: 2020/12/30
    */ 
    public boolean attachDead2User(User user);

}

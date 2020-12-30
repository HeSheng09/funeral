package com.iois.funeral.dao;

import com.iois.funeral.entity.Dead;
import com.iois.funeral.entity.RelationUserDead;
import com.iois.funeral.entity.User;

import java.util.List;
import java.util.Map;

public interface UserDao {

    /**
     * @Description: 添加一个用户到数据库
     * @Param: [user]
     * @Return: void
     * @Author: hesheng
     * @Date: 2020/12/30
     */
    public void addOneUser(User user);

    /**
     * @Description: 插入一条死者信息
     * @Param: [dead]
     * @Return: void
     * @Author: hesheng
     * @Date: 2020/12/30
     */
    public void addOneDead(Dead dead);

    /**
     * @Description: 插入一条关系
     * @Param: [relation]
     * @Return: void
     * @Author: hesheng
     * @Date: 2020/12/30
     */
    public void addOneRel(RelationUserDead relation);

    /**
    * @Description: 根据用户uid删掉一条用户记录
    * @Param: [uid]
    * @Return: void
    * @Author: hesheng
    * @Date: 2020/12/30
    */
    public void dropOneUser(String uid);

    /**
    * @Description: 根据死者uid删掉一条死者记录
    * @Param: [uid]
    * @Return: void
    * @Author: hesheng
    * @Date: 2020/12/30
    */
    public void dropOneDead(String uid);

    /**
    * @Description: 根据用户和死者uid删掉一条对应关系
    * @Param: [relation]
    * @Return: void
    * @Author: hesheng
    * @Date: 2020/12/30
    */
    public void dropOneRel(RelationUserDead relation);

    /**
     * @Description: 根据用户uid查询其相关的死者uid
     * @Param: [user_id]
     * @Return: java.util.List<com.iois.funeral.entity.RelationUserDead>
     * @Author: hesheng
     * @Date: 2020/12/30
     */
    public List<RelationUserDead> getRelByUserId(String user_id);

    /**
     * @Description: 根据用户uid查询其详细个人信息
     * @Param: [uid]
     * @Return: com.iois.funeral.entity.User
     * @Author: hesheng
     * @Date: 2020/12/30
     */
    public User getUserById(String uid);

    /**
     * @Description: 根据死者uid查询其信息
     * @Param: [uid]
     * @Return: com.iois.funeral.entity.Dead
     * @Author: hesheng
     * @Date: 2020/12/30
     */
    public Dead getDeadById(String uid);
}

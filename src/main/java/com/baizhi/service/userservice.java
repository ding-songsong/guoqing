package com.baizhi.service;

import com.baizhi.entity.user;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface userservice {
    //用户注册
    public  void  insert(user user);
    //登录
    public  user login(user user);
    //用户登录展示所有
    public  user select(String id);
    //管理员登录展示所有
    public List<user> showAll(@Param("yeshu") Integer yeshu, @Param("number") Integer number);
    //添加用户
    public void  tianjia(user user);
    //删除用户
    public  void delete(String id);
    //修改用户
    public  void  update(user user);
    //修改查询
    public  user select1(String id);
    //搜索
    public   List<user>  sousuo(String name);
    //修改状态
    public  void  update1(user user);
    //查询所有条数
    public  Integer tiaoshu();
}

package com.baizhi.service;

import com.baizhi.dao.userdao;
import com.baizhi.entity.user;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class userserviceImpl implements userservice {
    @Autowired
    private userdao userdao;
    @Override
    @Transactional
    public void insert(user user) {
        user.setId(UUID.randomUUID().toString());
        user.setShenfen("用户");
        user.setStatus("正常");
        userdao.insert(user);
    }
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public user login(user user) {
        com.baizhi.entity.user login = userdao.login(user);
        return login;
    }
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public user select(String id) {
        user select = userdao.select(id);
        return select;
    }
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<user> showAll(@Param("yeshu") Integer yeshu, @Param("number") Integer number) {
        List<user> list = userdao.showAll(yeshu,number);
        return list;
    }
    @Override
    @Transactional
    public void tianjia(user user) {
        user.setId(UUID.randomUUID().toString());
        user.setShenfen("用户");
        userdao.tianjia(user);
    }
    @Override
    @Transactional
    public void delete(String id) {
        userdao.delete(id);
    }
    @Override
    @Transactional
    public void update(user user) {
        userdao.update(user);
    }
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public user select1(String id) {
        user user = userdao.select1(id);
        return user;
    }
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public  List<user> sousuo(String name) {
        List<user> users = userdao.sousuo(name);
        return  users;
    }
    @Override
    @Transactional
    public void update1(user user) {
        userdao.update1(user);
    }

    @Override
    public Integer tiaoshu() {
        Integer tiaoshu = userdao.tiaoshu();
        return  tiaoshu;
    }

}

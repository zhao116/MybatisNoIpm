package com.itheima.dao;

import com.itheima.domain.QueryVo;
import com.itheima.domain.User;

import java.util.List;

//用户持久曾方法
public interface IUserDao {

    //查询所有
    List<User> findAll();
    //保存用户
    void saveUser(User user);
    //更新
    void updateUser(User user);
    //删除(根据Id删除)
    void deleteUser(Integer id);
    //查询一个(根据id查所有哦)
    User findById(Integer userId);
    //模糊查询(根据名称模糊查询)
    List<User> findByName(String username);
    //查询总用户数
    int findTotal();

    //根据QueryVo中的条件查询用户
    List<User> findUserByVo(QueryVo vo);

}

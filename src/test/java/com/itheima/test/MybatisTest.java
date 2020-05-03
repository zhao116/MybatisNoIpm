package com.itheima.test;


import com.itheima.dao.IUserDao;
import com.itheima.domain.QueryVo;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * 测试mybatis的crud操作
 */
public class MybatisTest {

    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao userDao;


    @Before //用于在测试方法执行之前
    public void init()throws Exception{
        //1.读取配置文件，生成字节数入流
        in  = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取SqlSessionFactory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.获取SqlSession对象
        sqlSession = factory.openSession();
        //获取Dao代理对象
        userDao = sqlSession.getMapper(IUserDao.class);
    }
    @After ///用于在测试方法执行之后
    public void destory()throws Exception{
        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
        in.close();
    }

    /**
     * 测试查询所有
     */
    @Test
    public void testFindAll() throws Exception{

        List<User> users = userDao.findAll();
        for (User user: users) {
            System.out.println(user);
        }

    }

    /**
     * 测试保存操作
     */
    @Test
    public void testSave(){
        User user = new User();
        user.setUserName("mybatisdddddddddd_LAST");
        user.setUserAddress("太原");
        user.setUserSex("男");
        user.setUserBirthday(new Date());
        System.out.println("保存操作之前："+user);
        userDao.saveUser(user);

        System.out.println("保存操作之后："+user);

    }

    /**
     * 测试更新操作
     */
    @Test
    public void testupdate(){
        User user = new User();
        user.setUserId(48);
        user.setUserName("updat1123232312");
        user.setUserAddress("河南");
        user.setUserSex("女");
        user.setUserBirthday(new Date());

        userDao.updateUser(user);

    }

    /**
     * 测试删除操作
     */
    @Test
    public void testdelete(){

        userDao.deleteUser(45);

    }
    /**
     * 测试查询一个操作
     */
    @Test
    public void testFindOne(){

        User user = userDao.findById(42);
        System.out.println(user);
    }
    /**
     * 测试模糊查询操作
     */
    @Test
    public void testFindByName(){

        List<User> users = userDao.findByName("%王%");
//        List<User> users = userDao.findByName("王");

        for (User user:users) {
            System.out.println(user);
        }
    }

    /**
     * 测试查询总记录条数操作
     */
    @Test
    public void testFindTotal(){
        int count = userDao.findTotal();
        System.out.println(count);
    }


    /**
     * 测试使用QueryVo作为查询条件
     */
    @Test
    public void testFindByVo(){
        QueryVo vo = new QueryVo();
        User user = new User();
        user.setUserName("%王%");
        vo.setUser(user);
        List<User> users = userDao.findUserByVo(vo);
        for (User u:users) {
            System.out.println(u);
        }
    }
}

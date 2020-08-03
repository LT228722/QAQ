package com.lt;


import com.lt.dao.PersonDao;
import com.lt.pojo.Person;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;

import java.io.InputStream;
import java.util.List;

public class Test {

    private InputStream in;
    private SqlSession sqlSession;
    private PersonDao personDao;
    Person person;

    @Before//用于在测试方法执行之前执行
    public void init() throws Exception {
        //1.读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.获取SqlSession对象
        sqlSession = factory.openSession();
        //4.获取dao的代理对象
        personDao = sqlSession.getMapper(PersonDao.class);
    }

    @After//用于在测试方法执行之后执行
    public void destroy() throws Exception {
        //提交事务
        sqlSession.commit();
        //6.释放资源
        sqlSession.close();
        in.close();
    }

    //查询所有
    @org.junit.Test
    public void testFindAll() {
        //5.执行查询所有方法
        List<Person> users = personDao.findAll();
        for (Person user : users) {
            System.out.println(user);
        }

    }

    //保存（增）用户
    @org.junit.Test
    public void testSave() {
        person = new Person();
        //person.setId(3);
        person.setName("嗷嗷叫");
        person.setUsername("1004");
        person.setPassword("444");
        person.setSex("男");
        person.setAge(21);

        System.out.println("保存操作之前：" + person);
        //5.执行保存方法
        personDao.savePerson(person);

        System.out.println("保存操作之后：" + person);
    }

    //更新用户信息（改）
    @org.junit.Test
    public void upDatePersonTest() {
        person = new Person();
        person.setId(7);
        person.setName("柒");
        person.setUsername("1007");
        person.setPassword("777");
        person.setSex("女");
        person.setAge(20);
        personDao.upDatePerson(person);
    }

    //删除用户（删）
    @org.junit.Test
    public void detelePersonTest() {
        person = new Person();
        personDao.detelePerson(6);
    }

    //like模糊查询
    @org.junit.Test
    public void likePersonTest() {
        person = new Person();
        List<Person> list = personDao.likePerson(20);

        for (Person p : list
        ) {
            System.out.println(p);
        }
    }

    //聚合函数查询（总数）
    @org.junit.Test
    public void countTest() {
        person = new Person();
        int i = personDao.countPerson();
        System.out.println("总数=="+i);
    }
}
package com.lt.dao;

import com.lt.pojo.Person;

import java.util.List;

public interface PersonDao {

    //查询所有人员信息(查)
    List<Person> findAll();

    //保存用户信息(增)
    void savePerson(Person person);

    //更新用户(改)
    int upDatePerson(Person person);

    //删除用户(删)
    int detelePerson(int p);

    //模糊查询like
    List<Person> likePerson(Integer i);

    //聚合函数查询（总数）
    int countPerson();
}

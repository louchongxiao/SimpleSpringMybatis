package com.spring.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 该Java类是一个实现了FactoryBean接口的工厂类，用于创建并返回MyBatis的Mapper接口对象。其主要功能包括：
 *    1.通过构造方法接收一个mapperClass参数，用于指定要创建的Mapper接口的类。
 *    2.通过@Autowired注解的setSqlSession方法，自动注入SqlSessionFactory对象，并根据mapperClass参数
 *      配置MyBatis的配置文件，然后打开一个SqlSession。
 *    3.重写getObject方法，返回SqlSession中根据mapperClass参数获取到的Mapper接口对象。
 *    4.重写getObjectType方法，返回mapperClass参数所指定的类类型。
 * 这个工厂类的作用是通过SqlSessionFactory创建SqlSession，并根据指定的Mapper接口类，获取到该Mapper接口
 * 的实现对象，以便在Spring应用中使用。
 */
public class MyFactoryBean implements FactoryBean {

    private SqlSession sqlSession;

    private Class mapperClass;

    public MyFactoryBean(Class mapperClass) {
        this.mapperClass = mapperClass;
    }

    @Autowired
    public void setSqlSession(SqlSessionFactory sessionFactory) {
        sessionFactory.getConfiguration().addMapper(mapperClass);
        this.sqlSession = sessionFactory.openSession();
    }

    @Override
    public Object getObject() {
        return sqlSession.getMapper(mapperClass);
    }

    @Override
    public Class<?> getObjectType() {
        return mapperClass;
    }
}

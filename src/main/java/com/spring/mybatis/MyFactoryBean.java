package com.spring.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

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

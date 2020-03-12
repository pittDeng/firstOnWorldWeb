package com.dsh.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class MybatisUtils {
    public static SqlSessionFactory sqlSessionFactory;
    static {
        String resource="mybatis-config.xml";
        try(InputStream stream = Resources.getResourceAsStream(resource)){
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(stream);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public static SqlSession getSession(){
        return sqlSessionFactory.openSession();
    }
    public static SqlSession getAutoCommitSession(){
        return sqlSessionFactory.openSession(true);
    }
}

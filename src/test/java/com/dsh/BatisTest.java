package com.dsh;

import com.dsh.dao.ArticleMapper;
import com.dsh.dao.UserMapper;
import com.dsh.entity.Article;
import com.dsh.entity.CommunityUser;
import com.dsh.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.springframework.web.servlet.DispatcherServlet;
import redis.clients.jedis.Jedis;

public class BatisTest {
    @Test
    public void test1(){
        SqlSession session = MybatisUtils.getSession();
        ArticleMapper mapper = session.getMapper(ArticleMapper.class);
        Article article = mapper.getArticle(1);
        System.out.println(article);
    }
    @Test
    public void test2(){
        SqlSession session = MybatisUtils.getSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        mapper.addUser(new CommunityUser("user","hash"));
        session.commit();
    }
    public void test3(){
    }
}

package com.dsh.config;

import com.dsh.dao.UserMapper;
import com.dsh.filters.SetTheExpireTime;
import com.dsh.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class BeanConfig {
    @Bean
    public FilterRegistrationBean getFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new CsrfFilter(new HttpSessionCsrfTokenRepository()));
        filterRegistrationBean.addUrlPatterns("/**");
        filterRegistrationBean.setOrder(2);
        filterRegistrationBean.setEnabled(true);
        return filterRegistrationBean;
    }
    @Bean
    public FilterRegistrationBean setTheExpireTimeNow(){
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new SetTheExpireTime());
        filterRegistrationBean.addUrlPatterns("*.god");
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.setEnabled(true);
        return filterRegistrationBean;
    }
    @Bean
    public DriverManagerDataSource getDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/community?useSSL=true&amp;useUnicode=true&amp;charaterEncoding=UTF-8&amp;serverTimezone=Asia/Shanghai");
        dataSource.setUsername("root");
        dataSource.setPassword("68168");
        return dataSource;
    }
    @Bean
    public UserMapper getUserMapper(){
        SqlSession session = MybatisUtils.getAutoCommitSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        return mapper;
    }
}

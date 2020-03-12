package com.dsh.filters;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dsh.entity.Article;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SetTheExpireTime implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("in the filter");
        ((HttpServletResponse)response).setDateHeader("Expires",System.currentTimeMillis()+10000);
        chain.doFilter(request,response);
    }

}

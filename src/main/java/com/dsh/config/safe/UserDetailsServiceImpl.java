package com.dsh.config.safe;

import com.dsh.App;
import com.dsh.dao.UserMapper;
import com.dsh.entity.CommunityUser;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserMapper userMapper =(UserMapper) App.context.getBean("getUserMapper");
        System.out.println("userMapper: "+userMapper);
        CommunityUser user = userMapper.getUserByName(username);
        if(user==null){
            throw new UsernameNotFoundException("no username="+username+" found");
        }
        System.out.println(user.toString());
        ArrayList<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return new User(user.getUserName(),user.getPassword(),list);
    }
}

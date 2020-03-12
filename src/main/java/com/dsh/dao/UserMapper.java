package com.dsh.dao;

import com.dsh.entity.CommunityUser;

public interface UserMapper {
    void addUser(CommunityUser user);
    CommunityUser getUserByName(String username);
    String getUserNameById(int user_id);
}

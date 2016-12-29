package com.demo.service;

import com.demo.entity.User;

/**
 * Created by cumt_ on 2016/12/17.
 */
public interface UserService {
    Integer login(String userName, String passWord);

    User getUser(String userNameId);
}

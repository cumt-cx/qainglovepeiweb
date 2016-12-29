package com.demo.service.impl;

import com.demo.dao.UserDao;
import com.demo.dao.redis.RedisDao;
import com.demo.entity.User;
import com.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by cumt_ on 2016/12/17.
 */
@Service
public class UserServiceImpl implements UserService {

    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisDao redisDao;

    public Integer login(String userName, String passWord) {
        return userDao.login(userName, passWord);
    }

    public User getUser(String userNameId) {
        User user = redisDao.getUSer(userNameId);
        if (user == null) {
            user = userDao.findByName("user" + userNameId);
            redisDao.putUser(user);
            user.setReadFrom("user从数据库中取出");
        } else {
            user.setReadFrom("user从redis缓存中取出,2分钟后失效");
        }
        return user;
    }
}

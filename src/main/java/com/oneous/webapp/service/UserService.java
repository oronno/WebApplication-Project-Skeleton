package com.oneous.webapp.service;

import com.oneous.webapp.persistance.dao.UserDao;
import com.oneous.webapp.persistance.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Abdullah Al Mamun Oronno
 */
@Service
@Transactional
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDao userDao;

    public User findUserByUsername(String username) {
        return userDao.findUserByUsername(username);
    }

    public User findUserByEmail(String email) {
        return userDao.findUserByEmail(email);
    }

    public List<User> findAllUsers() {
        return userDao.findAllUsers();
    }

    public void saveUser(User user) {
        userDao.save(user);
    }
}

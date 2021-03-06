package com.canal.controller;

import com.canal.dao.UserDao;
import com.canal.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ThinkPad on 2021/3/2.
 */
@RestController
public class UserController {
    @Autowired
    private UserDao userDao;

    @GetMapping("/user")
    public List<User> findAllUser(){
        return userDao.findAll();
    }
}

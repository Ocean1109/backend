package com.example.backend;

import com.example.backend.entity.User;
import com.example.backend.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class BackendApplicationTests {

    @Autowired
    UserMapper userMapper;
    @Test
    void contextLoads() {
        User user = new User();
        user.setUserAccount("2018211863");
        user.setUserPassword("88888888");
        user.setUserRole("ROLE_admin");
        Date date = new Date();
        user.setGmtCreate(date);
        user.setGmtModified(date);
        userMapper.insert(user);
    }

}

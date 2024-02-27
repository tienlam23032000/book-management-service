package com.example.bookmanagement.base;


import com.example.bookmanagement.BookManagementApplication;
import com.example.bookmanagement.domain.User;
import com.example.bookmanagement.model.UserDTO;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.UUID;

@ContextConfiguration(classes= BookManagementApplication.class)
@SpringBootTest
public class BaseTest {

    public User getMockData() {
        return new User(UUID.randomUUID().toString(), "Ha", false);
    }

    public UserDTO getMockDataDTO() {
        return new UserDTO(UUID.randomUUID().toString(), "Ha");
    }
}

package com.example.bookmanagement.repository;

import com.example.bookmanagement.BookManagementApplication;
import com.example.bookmanagement.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.UUID;

@ContextConfiguration(classes= BookManagementApplication.class)
@DataJpaTest // nó sẽ tự động roll back khi kết thúc 1 test case (test method)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
    // @Mock, @InJectedMock, @Autowired, @Spy
    User user;


    @Autowired
    UserRepository userRepository;

    @BeforeEach
    public void setup() {
        user = new User(UUID.randomUUID().toString(), "Ha", false);
    }

    @Test
    public void testSaveSuccess() {
        String id = user.getId();
        User userInserted = userRepository.save(user);

        //Assertion
        //C1:
        Assertions.assertNotNull(userInserted);

        //C2:
        User userDb = userRepository.findById(id).orElseThrow();
        Assertions.assertEquals(user.getId(), userDb.getId());
        Assertions.assertEquals(user.getName(), userDb.getName());
        Assertions.assertEquals(user.isDeleted(), userDb.isDeleted());

    }
}

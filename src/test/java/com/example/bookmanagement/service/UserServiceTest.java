package com.example.bookmanagement.service;

import com.example.bookmanagement.base.BaseTest;
import com.example.bookmanagement.domain.User;
import com.example.bookmanagement.exception.DuplicateIdException;
import com.example.bookmanagement.model.UserDTO;
import com.example.bookmanagement.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserServiceTest extends BaseTest {

    @MockBean
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Test
    public void testCreateSuccess() {
        User user = getMockData();
        when(userRepository.findById(any())).thenReturn(Optional.empty());
        when(userRepository.save(user)).thenReturn(user);

        // call method test
        UserDTO dto = getMockDataDTO();
        dto.setId(user.getId());
        UserDTO userInserted = userService.create(dto);

        // assert
        Assertions.assertEquals(dto.getId(), userInserted.getId());
        Assertions.assertEquals(dto.getName(), userInserted.getName());

    }

    @Test
    public void testCreateFailByDuplicateId() {
        User user = getMockData();
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        // call method test
        UserDTO dto = getMockDataDTO();
        dto.setId(user.getId());

        // assert
        DuplicateIdException ex =
                Assertions.assertThrows(DuplicateIdException.class, () -> userService.create(dto));
        Assertions.assertEquals(ex.getMessage(), "Id " + user.getId() + " duplicate");
    }
}

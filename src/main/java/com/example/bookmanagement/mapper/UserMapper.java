package com.example.bookmanagement.mapper;

import com.example.bookmanagement.domain.User;
import com.example.bookmanagement.model.UserDTO;
import com.example.bookmanagement.model.UserPartialDTO;
import com.example.bookmanagement.utils.UUIDUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserMapper {

    public static User mapDTOtoEntity(UserDTO dto) {
        // validate id
        UUIDUtils.validateUUID(dto.getId());
        return new User(dto.getId(), dto.getName());
    }

    public static UserDTO mapEntityToDTO(User user) {
        return new UserDTO(user.getId(), user.getName());
    }

    public static User mapPatch( User originalUser, UserPartialDTO dto) {
        User userUpdated = new User();
        userUpdated.setId(originalUser.getId());

        if(dto.getName() == null) { // cả cục optional null
            userUpdated.setName(originalUser.getName());
        } else if (dto.getName().isPresent()) {
            userUpdated.setName(dto.getName().get());
        } else {
            userUpdated.setName(null);
        }

        return userUpdated;
    }

    public static List<UserDTO> mapEntitiesToDTOs(List<User> usersOrigin) {
        List<UserDTO> users = new ArrayList<>();
        for(User u : usersOrigin) {
            users.add(mapEntityToDTO(u));
        }
        return users;
    }

}

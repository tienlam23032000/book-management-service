package com.example.bookmanagement.service;

import com.example.bookmanagement.domain.User;
import com.example.bookmanagement.exception.DuplicateIdException;
import com.example.bookmanagement.exception.NotFoundException;
import com.example.bookmanagement.mapper.UserMapper;
import com.example.bookmanagement.model.*;
import com.example.bookmanagement.repository.UserRepository;
import com.example.bookmanagement.repository.UserRepositoryCustom;
import com.example.bookmanagement.utils.PaginationUtils;
import com.example.bookmanagement.utils.UUIDUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final UserRepositoryCustom userRepositoryCustom;

    public UserService(UserRepository userRepository, UserRepositoryCustom userRepositoryCustom) {
        this.userRepository = userRepository;
        this.userRepositoryCustom = userRepositoryCustom;
    }

    public UserDTO create(UserDTO request) {
        Optional<User> userOptional = userRepository.findById(request.getId());
        if(userOptional.isPresent()) {
            throw new DuplicateIdException("Id " + request.getId() + " duplicate");
        }
        User userRequest = UserMapper.mapDTOtoEntity(request);
        userRequest.setDeleted(false);
        User user = userRepository.save(userRequest);
        return UserMapper.mapEntityToDTO(user);
    }

    public UserDTO getById(String id) {
        UUIDUtils.validateUUID(id);
        User user = userRepository.findById(id).orElseThrow(() ->
                new NotFoundException("User with id " + id + " not found."));
        return UserMapper.mapEntityToDTO(user);
    }

    public UserDTO update(String id, UserDTO dto) {
        UUIDUtils.validateUUID(id);
        User originUser = userRepository.findById(id).orElseThrow(() ->
                new NotFoundException("User with id " + id + " not found."));
        dto.setId(id);
        User user = userRepository.save(UserMapper.mapDTOtoEntity(dto));
        UserDTO response = UserMapper.mapEntityToDTO(user);
        response.setId(id);
        return response;
    }

    public UserDTO updatePartial(String id, UserPartialDTO dto) {
        UUIDUtils.validateUUID(id);
        User originUser = userRepository.findById(id).orElseThrow(() ->
                new NotFoundException("User with id " + id + " not found."));
        dto.setId(id);
        User user = userRepository.save(UserMapper.mapPatch(originUser, dto));
        return UserMapper.mapEntityToDTO(user);
    }

    public void delete(String id) {
        UUIDUtils.validateUUID(id);
        User originUser = userRepository.findById(id).orElseThrow(() ->
                new NotFoundException("User with id " + id + " not found."));
        originUser.setDeleted(true);
        userRepository.save(originUser);
    }


    public UserListDTO getAll(UserFilterParam filterParam) {
        PaginationUtils.validatePageAndSize(filterParam.getPage(), filterParam.getSize());
        int totalPage = (int) Math
                .round((double) userRepository.getTotalData() / filterParam.getSize());
        Href href = new Href();
        if(filterParam.getPage() > 1) {
            href.setPrevious(ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/api/v1/users")
                    .queryParam("page", filterParam.getPage() - 1)
                    .queryParam("size", filterParam.getSize())
                    .toUriString());
        }
        if(filterParam.getPage() != totalPage) {
            href.setNext(ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/api/v1/users")
                    .queryParam("page", filterParam.getPage() + 1)
                    .queryParam("size", filterParam.getSize())
                    .toUriString());
        }
        List<UserDTO> userDTOS = UserMapper
                .mapEntitiesToDTOs(userRepositoryCustom.findAll(filterParam));
        href.setSize(String.valueOf(userDTOS.size()));
        return new UserListDTO(userDTOS, href);
    }

}

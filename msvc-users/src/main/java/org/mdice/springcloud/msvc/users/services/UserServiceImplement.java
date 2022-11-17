package org.mdice.springcloud.msvc.users.services;

import org.mdice.springcloud.msvc.users.mapper.UserInDTOToUser;
import org.mdice.springcloud.msvc.users.persistences.entities.User;
import org.mdice.springcloud.msvc.users.persistences.repositories.UserRepository;
import org.mdice.springcloud.msvc.users.services.DTO.UserInDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;

    private final UserInDTOToUser mapper;


    public UserService(UserRepository repository, UserInDTOToUser mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public User createUser(UserInDTO userInDTO){
        User user = mapper.map(userInDTO);
        return this.repository.save(user);
    }


    public List<User> findAll() {
        return this.repository.findAll();
    }


    public User getById(Long id) {
        Optional<User> byId = this.repository.findById(id);
        if (byId.isEmpty()) {

        }
    }

}

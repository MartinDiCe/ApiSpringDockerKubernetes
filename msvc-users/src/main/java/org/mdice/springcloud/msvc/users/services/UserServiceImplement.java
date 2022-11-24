package org.mdice.springcloud.msvc.users.services;

import org.mdice.springcloud.msvc.users.exceptions.ToDoExceptions;
import org.mdice.springcloud.msvc.users.mapper.UserInDTOToUser;
import org.mdice.springcloud.msvc.users.persistences.entities.Status;
import org.mdice.springcloud.msvc.users.persistences.entities.User;
import org.mdice.springcloud.msvc.users.persistences.repositories.UserRepository;
import org.mdice.springcloud.msvc.users.services.DTO.UserInDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplement implements UserService {

    private final UserRepository repository;

    private final UserInDTOToUser mapper;


    public UserServiceImplement(UserRepository repository, UserInDTOToUser mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    @Transactional(readOnly = true)
    public List<User> listUsers() {

        return (List<User>) repository.findAll();

    }


    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByIdUser(Long id) {

        Optional<User> optionalUser = this.repository.findById(id);

        if (optionalUser.isEmpty()){
            throw new ToDoExceptions("id not found", HttpStatus.NOT_FOUND);
        }

        return repository.findById(id);

    }


    @Override
    @Transactional
    public User saveUser(UserInDTO userInDTO) {

        User user = mapper.map(userInDTO);

        return repository.save(user);

    }

    @Override
    @Transactional
    public void deleteUser(Long id) {

        Optional<User> optionalUser = this.repository.findById(id);

        if (optionalUser.isEmpty()){
            throw new ToDoExceptions("id not found", HttpStatus.NOT_FOUND);
        }

        this.repository.deleteById(id);

    }


    @Override
    @Transactional
    public User updateUser(Long id, UserInDTO userInDTO) {

        Optional<User> optionalUser = this.repository.findById(id);

        if (optionalUser.isEmpty()){
            throw new ToDoExceptions("id not found", HttpStatus.NOT_FOUND);
        }

        User user = optionalUser.get();
        user.setPassword(userInDTO.getPassword());
        user.setEmail(userInDTO.getEmail());
        user.setUpdateDate(LocalDateTime.now());

        repository.save(user);

        return user;

    }


    @Override
    @Transactional
    public void activateUser(Long id) {

        Optional<User> optionalUser = this.repository.findById(id);

        if (optionalUser.isEmpty()){
            throw new ToDoExceptions("id not found", HttpStatus.NOT_FOUND);
        }

        this.repository.activateUser(id);

    }

    @Override
    @Transactional
    public void unActivateUser(Long id) {

        Optional<User> optionalUser = this.repository.findById(id);

        if (optionalUser.isEmpty()){
            throw new ToDoExceptions("id not found", HttpStatus.NOT_FOUND);
        }

        this.repository.unActivateUser(id);

    }


    @Override
    @Transactional(readOnly = true)
    public List<User> findAllByStatus(Status status) {

        return this.repository.findAllByStatus(status);

    }
}

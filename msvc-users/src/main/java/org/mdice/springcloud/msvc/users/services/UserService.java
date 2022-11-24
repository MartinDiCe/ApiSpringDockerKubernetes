package org.mdice.springcloud.msvc.users.services;

import org.mdice.springcloud.msvc.users.persistences.entities.Status;
import org.mdice.springcloud.msvc.users.persistences.entities.User;
import org.mdice.springcloud.msvc.users.services.DTO.UserInDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> listUsers();

    Optional<User> findByIdUser(Long id);

    User saveUser(UserInDTO userInDTO);

    void deleteUser(Long id);

    User updateUser(Long id, UserInDTO userInDTO);

    void activateUser(Long id);

    void unActivateUser(Long id);

    List<User> findAllByStatus(Status status);

}

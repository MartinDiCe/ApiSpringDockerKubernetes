package org.mdice.springcloud.msvc.users.services.DTO;

import org.mdice.springcloud.msvc.users.persistences.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> usersList();

    Optional<User> userById(Long id);

    User userSave(UserInDTO userInDTO);

    void userDelete(Long id);

    void userUpdate(Long id, String password, String email);

    void userActivate(Long id);

    void userUnActivate(Long id);

    List<User> findAllByStatus(Boolean status);

}

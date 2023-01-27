package org.mdice.springcloud.msvc.users.mapper;

import org.mdice.springcloud.msvc.users.persistences.entities.Status;
import org.mdice.springcloud.msvc.users.persistences.entities.User;
import org.mdice.springcloud.msvc.users.services.DTO.UserInDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
public class UserInDTOToUser implements IMapper<UserInDTO, User>{
    @Override
    public User map(UserInDTO in) {

        User user = new User();

        user.setUsername(in.getUsername());
        user.setEmail(in.getEmail());
        user.setPassword(in.getPassword());
        user.setCreateDate(LocalDateTime.now());
        user.setStatus(Status.DISABLED);
        user.setUpdateDate(null);

        return user;

    }
}

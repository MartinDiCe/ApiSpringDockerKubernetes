package org.mdice.springcloud.msvc.courses.mapper;

import org.mdice.springcloud.msvc.courses.persistences.models.Status;
import org.mdice.springcloud.msvc.courses.persistences.models.User;
import org.mdice.springcloud.msvc.courses.persistences.models.UserInDTO;
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

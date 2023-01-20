package org.mdice.springcloud.msvc.users.validator.validations;

import com.github.cliftonlabs.json_simple.JsonObject;
import org.mdice.springcloud.msvc.users.exceptions.ToDoExceptions;
import org.mdice.springcloud.msvc.users.persistences.entities.User;
import org.mdice.springcloud.msvc.users.services.DTO.UserInDTO;
import org.mdice.springcloud.msvc.users.validator.ChainValidatorWithRepository;
import org.springframework.http.HttpStatus;

import java.util.Optional;

public class WithoutChanges extends ChainValidatorWithRepository {

    @Override
    public void validation(UserInDTO userInDTO, JsonObject message) {
        if(this.service.findByUsername(userInDTO.getUsername()).isPresent()){
            Optional<User> userRecived = service.findByUsername(userInDTO.getUsername());
            if (userRecived.isEmpty()) {
                throw new ToDoExceptions("", HttpStatus.BAD_REQUEST);
            }
            User user = userRecived.get();
            if (userInDTO.getUsername().toString() == user.getUsername().toString()){
                if (userInDTO.getPassword().toString() == user.getPassword().toString()){
                    if (userInDTO.getEmail().toString() == user.getEmail().toString()){
                        throw new ToDoExceptions("without changes" , HttpStatus.BAD_REQUEST);
                    }
                }
            }
        }
        this.next.validation(userInDTO , message);
    }
}

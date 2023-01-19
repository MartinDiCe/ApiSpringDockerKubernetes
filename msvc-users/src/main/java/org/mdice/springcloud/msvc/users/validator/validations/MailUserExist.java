package org.mdice.springcloud.msvc.users.validator.validations;

import org.mdice.springcloud.msvc.users.persistences.repositories.UserRepository;
import org.mdice.springcloud.msvc.users.services.DTO.UserInDTO;
import org.mdice.springcloud.msvc.users.validator.ChainValidatorWithRepository;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class MailUserExist extends ChainValidatorWithRepository {

    @Override
    public void validation(UserInDTO userInDTO, Map message){
        if(this.service.findByUsername(userInDTO.getUsername()).isPresent()){
            message.put("name", "Username already exists");
        }
        this.next.validation(userInDTO,message);

    }
}

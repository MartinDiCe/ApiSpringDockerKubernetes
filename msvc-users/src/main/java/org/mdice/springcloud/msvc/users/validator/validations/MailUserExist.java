package org.mdice.springcloud.msvc.users.validator.validations;

import org.mdice.springcloud.msvc.users.services.DTO.UserInDTO;
import org.mdice.springcloud.msvc.users.services.UserService;
import org.mdice.springcloud.msvc.users.validator.ChainValidator;

import java.util.Map;

public class MailUserExist extends ChainValidatorWithRepository {

    @Override
    public void validation(UserInDTO userInDTO, Map message){
        if(service.findByUsername(userInDTO.getUsername()).isPresent()){
            message.put("name:", "Username already exists");
        }
        this.next.validation(userInDTO,message);
    }
}

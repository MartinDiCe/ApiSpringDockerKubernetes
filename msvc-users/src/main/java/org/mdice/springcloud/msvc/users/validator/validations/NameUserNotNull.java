package org.mdice.springcloud.msvc.users.validator.validations;

import org.mdice.springcloud.msvc.users.services.DTO.UserInDTO;
import org.mdice.springcloud.msvc.users.validator.ChainValidator;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class NameUserNotNull extends ChainValidator {
    @Override

    public void validation(UserInDTO userInDTO, Map message){
        if(userInDTO.getUsername().length() == 0  || userInDTO.getUsername() == null){
            message.put("name:", "the name field is empty,");
        }
        this.next.validation(userInDTO,message);

    }

}

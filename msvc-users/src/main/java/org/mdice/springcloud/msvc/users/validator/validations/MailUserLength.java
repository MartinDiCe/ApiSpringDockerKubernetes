package org.mdice.springcloud.msvc.users.validator.validations;

import org.mdice.springcloud.msvc.users.services.DTO.UserInDTO;
import org.mdice.springcloud.msvc.users.validator.ChainValidator;

import java.util.Map;

public class MailUserLength extends ChainValidator {
    public void validation(UserInDTO userInDTO, Map message){
        if(userInDTO.getEmail().length() >= 30){
            message.put("mail:","maximum of 30 characters");
        }
        this.next.validation(userInDTO, message);
    }
}

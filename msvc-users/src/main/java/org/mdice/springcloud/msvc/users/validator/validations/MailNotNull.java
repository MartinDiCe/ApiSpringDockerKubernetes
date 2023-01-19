package org.mdice.springcloud.msvc.users.validator.validations;

import org.mdice.springcloud.msvc.users.services.DTO.UserInDTO;
import org.mdice.springcloud.msvc.users.validator.ChainValidator;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class MailNotNull extends ChainValidator {
        @Override

        public void validation(UserInDTO userInDTO, Map message){
            if(userInDTO.getEmail().length() == 0  || userInDTO.getEmail() == null){
                message.put("mail", "the mail field is empty");
            }
            this.next.validation(userInDTO,message);

        }

}
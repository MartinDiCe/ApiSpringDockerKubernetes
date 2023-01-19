package org.mdice.springcloud.msvc.users.validator.validations;

import com.github.cliftonlabs.json_simple.JsonObject;
import org.mdice.springcloud.msvc.users.services.DTO.UserInDTO;
import org.mdice.springcloud.msvc.users.validator.ChainValidator;

public class MailNotNull extends ChainValidator {
        @Override

        public void validation(UserInDTO userInDTO, JsonObject message){
            if(userInDTO.getEmail().length() == 0  || userInDTO.getEmail() == null){
                message.put("mail", "the mail field is empty");
            }
            this.next.validation(userInDTO,message);

        }

}
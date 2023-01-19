package org.mdice.springcloud.msvc.users.validator.validations;

import com.github.cliftonlabs.json_simple.JsonObject;
import org.mdice.springcloud.msvc.users.services.DTO.UserInDTO;
import org.mdice.springcloud.msvc.users.validator.ChainValidator;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class NameUserLength extends ChainValidator {

        @Override

        public void validation(UserInDTO userInDTO, JsonObject message){
            if(userInDTO.getUsername().length() >= 30  ){
                message.put("name", "maximum of 30 characters");
            }
            this.next.validation(userInDTO,message);
        }

}

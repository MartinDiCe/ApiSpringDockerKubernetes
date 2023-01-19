package org.mdice.springcloud.msvc.users.validator.validations;

import com.github.cliftonlabs.json_simple.JsonObject;
import org.mdice.springcloud.msvc.users.services.DTO.UserInDTO;
import org.mdice.springcloud.msvc.users.validator.ChainValidator;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class NameUserNotNull extends ChainValidator {
    @Override

    public void validation(UserInDTO userInDTO, JsonObject message){
        if(userInDTO.getUsername().length() == 0  || userInDTO.getUsername() == null){
            message.put("name", "the name field is empty");
        }
        this.next.validation(userInDTO,message);

    }

}

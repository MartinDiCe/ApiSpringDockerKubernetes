package org.mdice.springcloud.msvc.users.validator.validations;

import com.github.cliftonlabs.json_simple.JsonObject;
import org.mdice.springcloud.msvc.users.services.DTO.UserInDTO;
import org.mdice.springcloud.msvc.users.validator.ChainValidator;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class PasswordIsNull extends ChainValidator {

    @Override
    public void validation(UserInDTO userInDTO, JsonObject message) {
        if (userInDTO.getPassword().length() == 0 || userInDTO.getPassword() == null){
            message.put("password","the password is empity");
        }
        next.validation(userInDTO,message);

    }
}

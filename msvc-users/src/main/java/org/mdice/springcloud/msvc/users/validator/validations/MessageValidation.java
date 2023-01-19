package org.mdice.springcloud.msvc.users.validator.validations;

import com.github.cliftonlabs.json_simple.JsonObject;
import org.mdice.springcloud.msvc.users.exceptions.ToDoExceptions;
import org.mdice.springcloud.msvc.users.services.DTO.UserInDTO;
import org.mdice.springcloud.msvc.users.validator.ChainValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

public class MessageValidation extends ChainValidator {

    @Override
    public void validation(UserInDTO userInDTO, JsonObject message) {
        if(message.size() > 0) {
            System.out.println(message);
            throw new ToDoExceptions(message.toJson(), HttpStatus.BAD_REQUEST );
        }
    }
}

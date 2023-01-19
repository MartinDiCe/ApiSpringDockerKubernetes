package org.mdice.springcloud.msvc.users.validator.validations;

import org.mdice.springcloud.msvc.users.exceptions.ToDoExceptions;
import org.mdice.springcloud.msvc.users.services.DTO.UserInDTO;
import org.mdice.springcloud.msvc.users.validator.ChainValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.Map;

public class MessageValidation extends ChainValidator {

    @Override
    public void validation(UserInDTO userInDTO, Map message) {
        if(message.size() > 0) {
            throw new ToDoExceptions(Collections.synchronizedMap(message).toString(), HttpStatus.NOT_ACCEPTABLE );
        }
    }
}

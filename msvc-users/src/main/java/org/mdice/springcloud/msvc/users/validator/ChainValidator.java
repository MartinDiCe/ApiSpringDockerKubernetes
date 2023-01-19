package org.mdice.springcloud.msvc.users.validator;

import com.github.cliftonlabs.json_simple.JsonObject;
import org.mdice.springcloud.msvc.users.services.DTO.UserInDTO;
import org.springframework.http.ResponseEntity;

import java.util.Map;

abstract public class ChainValidator {
    public ChainValidator next;

    public void setNext(ChainValidator chain){this.next = chain;}

    public void validation(UserInDTO userInDTO){}

    public abstract void validation(UserInDTO userInDTO, JsonObject message);
}

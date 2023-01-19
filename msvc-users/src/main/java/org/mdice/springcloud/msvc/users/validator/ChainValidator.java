package org.mdice.springcloud.msvc.users.validator;

import org.mdice.springcloud.msvc.users.services.DTO.UserInDTO;

import java.util.Map;

abstract public class ChainValidator {
    public ChainValidator next;

    public void setNext(ChainValidator chain){this.next = chain;}

    public void validation(UserInDTO userInDTO){}

    public abstract void validation(UserInDTO userInDTO, Map message);
}

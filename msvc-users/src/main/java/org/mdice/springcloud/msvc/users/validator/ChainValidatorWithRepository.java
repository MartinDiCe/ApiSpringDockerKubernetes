package org.mdice.springcloud.msvc.users.validator;


import org.mdice.springcloud.msvc.users.persistences.repositories.UserRepository;
import org.mdice.springcloud.msvc.users.services.UserService;
import org.mdice.springcloud.msvc.users.validator.ChainValidator;



abstract public class ChainValidatorWithRepository extends ChainValidator{
    public UserService service;

    public void setService(UserService service) {
        this.service = service;
    }
}



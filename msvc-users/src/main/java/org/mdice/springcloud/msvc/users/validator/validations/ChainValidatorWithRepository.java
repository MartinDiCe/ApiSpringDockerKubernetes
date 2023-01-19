package org.mdice.springcloud.msvc.users.validator.validations;


import org.mdice.springcloud.msvc.users.persistences.repositories.UserRepository;
import org.mdice.springcloud.msvc.users.validator.ChainValidator;



abstract public class ChainValidatorWithRepository extends ChainValidator{
    public UserRepository service;

    public void setService(UserRepository service) {
        this.service = service;
    }
}



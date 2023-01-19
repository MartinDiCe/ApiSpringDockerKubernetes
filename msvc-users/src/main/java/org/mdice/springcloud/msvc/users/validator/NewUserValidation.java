package org.mdice.springcloud.msvc.users.validator;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.github.cliftonlabs.json_simple.JsonObject;
import org.mdice.springcloud.msvc.users.persistences.repositories.UserRepository;
import org.mdice.springcloud.msvc.users.services.DTO.UserInDTO;
import org.mdice.springcloud.msvc.users.services.UserService;
import org.mdice.springcloud.msvc.users.services.UserServiceImplement;
import org.mdice.springcloud.msvc.users.validator.validations.*;

import java.util.HashMap;
import java.util.Map;

public class NewUserValidation {

    JsonObject message = new JsonObject();
    private NameUserNotNull nameUserNotNull = new NameUserNotNull();
    private NameUserExist nameUserExist = new NameUserExist();
    private NameUserLength nameUserLength = new NameUserLength();
    private MailNotNull mailNotNull = new MailNotNull();
    private MailUserExist mailUserExist = new MailUserExist();
    private MailUserLength mailUserLength = new MailUserLength();
    private PasswordIsNull passwordIsNull = new PasswordIsNull();
    private MessageValidation messageValidation = new MessageValidation();

    public void validate (UserInDTO userInDTO , UserService service){
        nameUserNotNull.setNext(nameUserExist);
        nameUserExist.setNext(nameUserLength);
        nameUserLength.setNext(mailNotNull);
        mailNotNull.setNext(mailUserExist);
        mailUserExist.setNext(mailUserLength);
        mailUserLength.setNext(passwordIsNull);
        passwordIsNull.setNext(messageValidation);

        nameUserExist.setService(service);
        mailUserExist.setService(service);

        nameUserNotNull.validation(userInDTO , message);
    }
}
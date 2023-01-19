package org.mdice.springcloud.msvc.users.validator;

import org.mdice.springcloud.msvc.users.validator.validations.*;

public class NewUserValidation {
    private NameUserNotNull nameUserNotNull = new NameUserNotNull();
    private NameUserExist nameUserExist = new NameUserExist();
    private NameUserLength nameUserLength = new NameUserLength();
    private MailNotNull mailNotNull = new MailNotNull();
    private MailUserExist mailUserExist = new MailUserExist();
    private MailUserLength mailUserLength = new MailUserLength();
    private PasswordIsNull passwordIsNull = new PasswordIsNull();
    private MessageValidation messageValidation  = new MessageValidation();




}

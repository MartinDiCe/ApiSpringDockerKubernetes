package org.mdice.springcloud.msvc.users.persistence.repositories;

import org.mdice.springcloud.msvc.users.persistence.entities.User;
import org.springframework.data.repository.CrudRepository;

//va long por que la llave primaria es Long (ID de User), además no necesitamos la anotacion @Repository porque por defecto es repository para spring CrudRepository
public interface UserRepository extends CrudRepository <User, Long> {
}

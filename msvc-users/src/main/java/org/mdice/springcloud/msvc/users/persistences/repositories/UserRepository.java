package org.mdice.springcloud.msvc.users.persistences.repositories;

import org.mdice.springcloud.msvc.users.persistences.entities.Status;
import org.mdice.springcloud.msvc.users.persistences.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


//va long por que la llave primaria es Long (ID de User), además no necesitamos la anotacion @Repository porque por defecto es repository para spring CrudRepository
@Repository
public interface UserRepository extends JpaRepository <User, Long> {

    public List<User> findAllByStatus(Status status);


    @Modifying
    @Query(value = "UPDATE users SET users.status = 0 , users.update_Date = CURRENT_TIMESTAMP WHERE id=:id", nativeQuery = true)
    public void activateUser(@Param("id") Long id);


    @Modifying
    @Query(value = "UPDATE users SET users.status = 1 , users.update_Date = CURRENT_TIMESTAMP WHERE id=:id", nativeQuery = true)
    public void unActivateUser(@Param("id") Long id);


}

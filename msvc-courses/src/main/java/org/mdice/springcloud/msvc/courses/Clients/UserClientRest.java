package org.mdice.springcloud.msvc.courses.Clients;

import org.mdice.springcloud.msvc.courses.persistences.models.User;
import org.mdice.springcloud.msvc.courses.persistences.models.UserInDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;


@FeignClient(name="msvc-users", url="localhost:8001/userApi")
public interface UserClientRest {

    @GetMapping("/username/{username}")
    Optional<User> getByUsername(@PathVariable(name = "username") String username);

    @GetMapping("/{id}")
    Optional<User> getById(@PathVariable(name = "id") Long id);

    @PostMapping("/")
    User create(@RequestBody UserInDTO userInDTO);

}

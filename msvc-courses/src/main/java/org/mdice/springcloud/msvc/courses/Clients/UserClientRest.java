package org.mdice.springcloud.msvc.courses.Clients;

import org.mdice.springcloud.msvc.courses.persistences.models.User;
import org.mdice.springcloud.msvc.courses.persistences.models.UserInDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@FeignClient(name="msvc-users", url="localhost:8001/userApi")
public interface UserClientRest {

    @GetMapping("/username/{username}")
    Optional<User> getByUsername(@PathVariable(name = "username") String username);

    @GetMapping("/{id}")
    Optional<User> getById(@PathVariable(name = "id") Long id);

    @PostMapping("/")
    User create(@RequestBody UserInDTO userInDTO);

    @GetMapping("/users-course")
    List<User> getUsersByCourse(@RequestParam Iterable<Long> ids);

}

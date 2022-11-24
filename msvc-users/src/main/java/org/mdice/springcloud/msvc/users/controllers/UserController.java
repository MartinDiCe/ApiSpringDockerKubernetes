package org.mdice.springcloud.msvc.users.controllers;

import org.mdice.springcloud.msvc.users.persistences.entities.Status;
import org.mdice.springcloud.msvc.users.persistences.entities.User;
import org.mdice.springcloud.msvc.users.services.DTO.UserInDTO;
import org.mdice.springcloud.msvc.users.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/userApi")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {

        this.service = service;

    }


    @GetMapping
    public List<User> listAll() {

        return service.listUsers();

    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") Long id) {
        Optional<User> userOptional = service.findByIdUser(id);
        if (userOptional.isPresent()){
            return ResponseEntity.ok().body(userOptional.get());
        }
        return ResponseEntity.notFound().build();
    }


    @PostMapping
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody UserInDTO userInDTO){
        User user = this.service.saveUser(userInDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }


    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody UserInDTO userInDTO){
        User user = this.service.updateUser(id, userInDTO);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        this.service.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body("User with id: "+id +", was successfully deleted");
    }


    @PatchMapping("user-activate/{id}")
    public ResponseEntity<?> activate(@PathVariable("id") Long id, UserInDTO userInDTO){
        this.service.activateUser(id);
        return ResponseEntity.status(HttpStatus.OK).body("User with id: " +id +", was successfully enabled");
    }


    @PatchMapping("user-unactivate/{id}")
    public ResponseEntity<?> unActivate(@PathVariable("id") Long id, UserInDTO userInDTO){
        this.service.unActivateUser(id);
        return ResponseEntity.status(HttpStatus.OK).body("User with id: " +id +", was successfully disabled");
    }


    @GetMapping("/list-all-by-status/{status}")
    public List<User> findAllByStatus(@PathVariable("status") Status status){
        return this.service.findAllByStatus(status);
    }

}

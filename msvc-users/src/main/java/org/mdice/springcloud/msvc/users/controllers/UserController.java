package org.mdice.springcloud.msvc.users.controllers;

import org.mdice.springcloud.msvc.users.persistences.entities.Status;
import org.mdice.springcloud.msvc.users.persistences.entities.User;
import org.mdice.springcloud.msvc.users.services.DTO.UserInDTO;
import org.mdice.springcloud.msvc.users.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import javax.validation.Valid;
import java.util.*;


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

        return ResponseEntity.noContent().build();

    }

    @GetMapping("/username/{username}")
    public ResponseEntity<?> getByUsername(@PathVariable(name = "username") String username) {

        Optional<User> userOptional = service.findByUsername(username);

        if (userOptional.isEmpty()){

            return ResponseEntity.noContent().build();

        }

        return ResponseEntity.ok().body(userOptional.get());

    }

    @PostMapping
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@Valid @RequestBody UserInDTO userInDTO, BindingResult result){

        ResponseEntity<Map<String, String>> errors = validate(result);
        if (errors != null) return errors;

        Optional<User> optionalUser = service.findByUsername(userInDTO.getUsername());

        if (optionalUser.isPresent()) {

            User userDB = optionalUser.get();

            if (userInDTO.getUsername().equalsIgnoreCase(userDB.getUsername())) {

                return ResponseEntity.badRequest().body(Collections.singletonMap("Username: ", "Username already exists"));

            }

            if (service.findByUsername(userInDTO.getUsername()).isPresent()) {

                return ResponseEntity.badRequest().body(Collections.singletonMap("Error: ", "Username already exists"));

            }
        }

        Optional<User> optionalEmail = service.findByEmail(userInDTO.getEmail());

        if (optionalEmail.isPresent()) {

            User userEmail = optionalEmail.get();

            if (userInDTO.getEmail().equalsIgnoreCase(userEmail.getEmail())) {

                return ResponseEntity.badRequest().body(Collections.singletonMap("Error: ", "Another user with email " + userInDTO.getEmail() + " already exists"));

            }

            if (service.findByEmail(userInDTO.getEmail()).isPresent()) {

                return ResponseEntity.badRequest().body(Collections.singletonMap("Error: ", "Another user with email " + userInDTO.getEmail() + " already exists"));

            }
        }

            User user = this.service.saveUser(userInDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body(user);

    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody UserInDTO userInDTO, BindingResult result, @PathVariable Long id){

        ResponseEntity<Map<String, String>> errors = validate(result);

        if (errors != null) return errors;

        Optional<User> optionalUser = service.findByIdUser(id);

        if (optionalUser.isPresent()) {

            User userDB = optionalUser.get();

            if (userInDTO.getUsername().equalsIgnoreCase(userDB.getUsername())) {

                return ResponseEntity.badRequest().body(Collections.singletonMap("Error: ", "Username already exists"));

            }

            if (service.findByUsername(userInDTO.getUsername()).isPresent()) {

                return ResponseEntity.badRequest().body(Collections.singletonMap("Error: ", "Username already exists"));

            }

            if (userInDTO.getEmail().equalsIgnoreCase(userDB.getEmail())) {

                return ResponseEntity.badRequest().body(Collections.singletonMap("Error: ", "Another user with email " + userInDTO.getEmail() + " already exists"));

            }

            if (service.findByEmail(userInDTO.getEmail()).isPresent()) {

                return ResponseEntity.badRequest().body(Collections.singletonMap("Error: ", "Another user with email " + userInDTO.getEmail() + " already exists"));

            }
        }

        User user = this.service.updateUser(id, userInDTO);

        return ResponseEntity.status(HttpStatus.OK).body(user);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){

        this.service.deleteUser(id);

        return ResponseEntity.status(HttpStatus.OK).body("User with id: "+id +", was successfully deleted");

    }

    @PatchMapping("user-activate/{id}")
    public ResponseEntity<?> activate(@PathVariable("id") Long id){

        Optional<User> oId = service.findByIdUser(id);

        if (oId.isEmpty()){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Id not found");

        }

        this.service.activateUser(id);

        return ResponseEntity.status(HttpStatus.OK).body("User with id: " +id +", was successfully enabled");

    }


    @PatchMapping("user-inactivate/{id}")
    public ResponseEntity<?> inactivate(@PathVariable("id") Long id){

        Optional<User> oId = service.findByIdUser(id);

        if (oId.isEmpty()){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Id not found");

        }

        this.service.inactivateUser(id);

        return ResponseEntity.status(HttpStatus.OK).body("User with id: " +id +", was successfully disabled");

    }

    @GetMapping("/list-all-by-status/{status}")
    public List<User> findAllByStatus(@PathVariable("status") Status status){

        return this.service.findAllByStatus(status);

    }

    private ResponseEntity<Map<String,String>> validate(BindingResult result) {

        if (result.hasErrors()){

            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));

            return ResponseEntity.badRequest().body(errors);

        }

        return null;

    }
}

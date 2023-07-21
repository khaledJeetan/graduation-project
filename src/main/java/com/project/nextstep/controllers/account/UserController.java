package com.project.nextstep.controllers.account;

import com.project.nextstep.entity.LoginRequest;
import com.project.nextstep.entity.accounts.User;
import com.project.nextstep.services.account.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(path = "/users")
//@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> retrieveAllUsers(){
        return userService.getUsers();
    }

    @GetMapping( "/{userId}")
    public ResponseEntity<User> retrieveUser(
            @PathVariable("userId") Long userId
            ){
        return ResponseEntity.ok().body(userService.getUser(userId));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> retrieveUser(
            @PathVariable("email") String email
    ){
        return ResponseEntity.ok().body(userService.getUser(email));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        return ResponseEntity.ok().body(userService.addUser(user));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId")Long userId){
        return userService.deleteUser(userId) ?
                ResponseEntity.ok().body("User Deleted successfully.") :
                ResponseEntity.internalServerError().body("Error User can't be Deleted!!.");
    }
    @DeleteMapping("/email/{email}")
    public ResponseEntity<String> deleteUser(@PathVariable("email") String email){
        return userService.deleteUser(email) ?
                ResponseEntity.ok().body("User Deleted successfully.") :
                ResponseEntity.internalServerError().body("Error User can't be Deleted!!.");
    }

    @PutMapping( "/{userId}")
    public ResponseEntity<User> updateUser(
            @PathVariable("userId") Long userId,
            @RequestBody User user){
       return ResponseEntity.ok().body(userService.updateUser(userId,user));
    }

    @PutMapping("/email/{email}")
    public ResponseEntity<User> updateUser(
            @PathVariable("email") String email,
            @RequestBody User user){
        return ResponseEntity.ok().body(userService.updateUser( email,user));
    }

    @PostMapping("/verify-email")
    public String sendVerificationEmail(@RequestBody String email){
        return userService.sendVerificationEmail(email).orElseThrow(()-> new IllegalStateException("ERROR Email NOT Sent"));
    }

    @PostMapping( "/login")
    public ResponseEntity<User> verifyUser(
            @RequestBody LoginRequest loginRequest
            ){
        User user = userService.verifyUser(loginRequest.getEmail(),loginRequest.getPassword());
        return  user!=null ? ResponseEntity.ok().body(user) : ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

}

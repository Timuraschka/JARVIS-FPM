package edu.fra.uas.user.controller;

import edu.fra.uas.token.Token;
import edu.fra.uas.token.TokenService;
import edu.fra.uas.user.model.User;
import edu.fra.uas.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UserController {


    @Autowired
    UserService userService;
    //Logger log = new Logger(UserController.class);

    @Autowired
    TokenService tokenService;

    //if (tokenService.checkIfTokenExistsAndIsValid(userToken)){
    //return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);

    @CrossOrigin
    // Mapping to Login
    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> loginUser(@RequestBody User user) {

        if (userService.loginUser(user.getEmail(), user.getPassword())){

            Token token = tokenService.createToken(userService.getUserWithEmail(user.getEmail()).getId());
            System.out.println(userService.getUserWithEmail(user.getEmail()).getId());
            System.out.println(token.getToken().toString());
            return ResponseEntity.status(HttpStatus.OK).body(token.getToken());

        }


        return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
    }

    @CrossOrigin
    // Mapping to get a user
    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUser(@RequestHeader  UUID token ) {

        if (token!=null){

            if (tokenService.checkIfTokenExistsAndIsValid(token)) {
                return ResponseEntity.status(HttpStatus.OK).body(userService.getUser(tokenService.getUserID(token).getUserID()));
            }
        }

        return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
    }

    @CrossOrigin
    // Mapping to logout
    @RequestMapping(value = "/logout", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> logout(@RequestHeader UUID token) {

        if (tokenService.checkIfTokenExistsAndIsValid(token)) {
            tokenService.deleteToken(token);
            return new ResponseEntity<String>(HttpStatus.OK);
        }
        return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
    }
   
}
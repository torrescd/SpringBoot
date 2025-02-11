package es.bit.tareasproyectoshex.controllers;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.bit.tareasproyectoshex.models.ErrorMessage;
import es.bit.tareasproyectoshex.models.User;
import es.bit.tareasproyectoshex.ports.UserService;

@RestController
public class UsersRestController {

    
    private UserService userService;

    @Autowired
    public UsersRestController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public Collection<User> getUsers() {
        return userService.findAll();
    }

    @RequestMapping(value = "/users/{userid}", method = RequestMethod.GET)
    public ResponseEntity getUser(@PathVariable Long userid) {
        User possibleUser = userService.findById(userid);
        if (possibleUser!=null) {
            return new ResponseEntity(possibleUser, HttpStatus.OK);
        }
        return new ResponseEntity(
                ErrorMessage.builder().message("User with id " + userid + " was not found").build(),
                HttpStatus.NOT_FOUND
        );
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity addUser(@Valid @RequestBody User newUser) {
        User user = userService.add(newUser);
        if (user.getUid() > 0) {
            return new ResponseEntity("{\"id\": " + user.getUid() + "}", HttpStatus.CREATED);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}

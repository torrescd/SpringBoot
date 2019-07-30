package es.bit.TareasProyectos.controller;

import es.bit.TareasProyectos.models.User;
import es.bit.TareasProyectos.services.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/rest")
public class UsersController {

    @Autowired
    UsuariosService usuariosService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity getAllUsers()
    {
        return new ResponseEntity(usuariosService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{uid}", method = RequestMethod.GET)
    public ResponseEntity getUsersById(@PathVariable Long uid)
    {
        User user = usuariosService.findById(uid);
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/users/create", method = RequestMethod.POST)
    public ResponseEntity createUser(@Valid @RequestBody User user){
        User newUser = usuariosService.add(user);
        return new ResponseEntity(newUser.getUid(), HttpStatus.OK);
    }

    @RequestMapping(value = "/users/delete/{uid}", method = RequestMethod.PUT)
    public ResponseEntity deleteById(@PathVariable Long uid)
    {
        usuariosService.delete(uid);
       return new ResponseEntity(uid, HttpStatus.OK);
    }

    @RequestMapping(value = "/users/update", method = RequestMethod.POST)
    public ResponseEntity updateUser(@Valid @RequestBody User user){

        usuariosService.delete(user.getUid());
        User newUser = usuariosService.add(user);
        return new ResponseEntity(newUser.getUid(), HttpStatus.OK);
    }



}

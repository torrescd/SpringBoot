package es.bit.TareasProyectos.controller;

import es.bit.TareasProyectos.models.User;
import es.bit.TareasProyectos.services.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    UsuariosService usuariosService;

    @RequestMapping(value = "/usuarios", method = RequestMethod.GET)
    public Collection<User> getAllUsers()
    {
        return usuariosService.findAll();
    }
    @RequestMapping(value = "/usuarios/{uid}", method = RequestMethod.GET)
    public User getUsersById(@PathVariable Long uid)
    {
        return usuariosService.findById(uid);
    }
    @RequestMapping(value = "/usuarios/{uid}", method = RequestMethod.PUT)
    public User addUser(@PathVariable Long uid)
    {
        return usuariosService.findById(uid);
    }

    @RequestMapping(value = "/usuarios/create", method = RequestMethod.POST)
    public long createUsuario(@Valid @RequestBody User user){

        User newUser = usuariosService.add(user);

        return newUser.getUid();
    }

    @RequestMapping(value = "/usuarios/delete/{uid}", method = RequestMethod.PUT)
    public void deleteById(@PathVariable Long uid)
    {
        usuariosService.delete(uid);

    }

    @RequestMapping(value = "/usuarios/create", method = RequestMethod.POST)
    public long updateUser(@Valid @RequestBody User user){

        usuariosService.delete(user.getUid());

        User newUser = usuariosService.add(user);

        return newUser.getUid();
    }




}

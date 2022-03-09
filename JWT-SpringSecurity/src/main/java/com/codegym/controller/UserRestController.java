package com.codegym.controller;

import com.codegym.entities.User;
import com.codegym.service.JwtService;
import com.codegym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/rest")
public class UserRestController {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable int id){
        User user = userService.findById(id);
        if (user != null){
            return  new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>("Not found user", HttpStatus.NO_CONTENT);
    }

    @PostMapping("/user")
    public ResponseEntity<String> createUser(@RequestBody User user){
        if (userService.add(user)){
            return new ResponseEntity<>("Created", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User existed", HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable int id){
        userService.delete(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(HttpServletRequest request, @RequestBody User user){
        String result = "";
        HttpStatus httpStatus = null;
        try {
            if (userService.checkLogin(user)){
                result = jwtService.generateTokenLogin(user.getUsername());
                httpStatus = HttpStatus.OK;
            } else {
                result = "Wrong userId and password";
                httpStatus = HttpStatus.BAD_REQUEST;
            }
        } catch (Exception ex){
            result = "Sever error";
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(result, httpStatus);
    }
}

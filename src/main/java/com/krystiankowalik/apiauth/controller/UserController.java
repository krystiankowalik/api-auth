package com.krystiankowalik.apiauth.controller;

import com.krystiankowalik.apiauth.model.Authority;
import com.krystiankowalik.apiauth.model.UserEntity;
import com.krystiankowalik.apiauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@RestController
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

    @GetMapping("tmp")
    public void tmp() {
        userService.addUser(new UserEntity("jlong", passwordEncoder.encode("spring"), true, new HashSet<>(Collections.singletonList(new Authority("ROLE,USER")))));
    }

    /*@GetMapping("tmp2")
    public UserEntity getUserByName() throws Throwable {
        return userService.getUserByName("jlong");
    }*/

}

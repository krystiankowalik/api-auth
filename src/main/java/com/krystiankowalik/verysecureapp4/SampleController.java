package com.krystiankowalik.verysecureapp4;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class SampleController {

    private final PasswordEncoder userPasswordEncoder;
    private final UserService userService;

    @Autowired
    public SampleController(PasswordEncoder userPasswordEncoder, UserService userService) {
        this.userPasswordEncoder = userPasswordEncoder;
        this.userService = userService;
    }

    @GetMapping("/start")
    public void generateUser() {
        //Account jlong = userService.getUser("jlong").get();
        //if (jlong == null) {
            val account = new Account("jlong", userPasswordEncoder.encode("spring"), true);
            userService.addUser(account);
        //}

    }
}

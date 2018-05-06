package com.krystiankowalik.verysecureapp4;

import com.krystiankowalik.verysecureapp4.model.Authority;
import com.krystiankowalik.verysecureapp4.model.UserEntity;
import com.krystiankowalik.verysecureapp4.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.HashSet;

@SpringBootApplication
public class VerySecureApp4Application {

    public static void main(String[] args) {
        SpringApplication.run(VerySecureApp4Application.class, args);
    }
}


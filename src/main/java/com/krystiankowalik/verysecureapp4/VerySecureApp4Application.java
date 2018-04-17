package com.krystiankowalik.verysecureapp4;

import com.krystiankowalik.verysecureapp4.model.UserEntity;
import com.krystiankowalik.verysecureapp4.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class VerySecureApp4Application {

    @Bean
    CommandLineRunner demo(UserRepository userRepository, @Autowired PasswordEncoder userPasswordEncoder) {
        System.out.println("Hello!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!**********************");
        System.out.println(userPasswordEncoder.encode("spring"));
        userRepository.save(new UserEntity("jlong", userPasswordEncoder.encode("spring"), true));
        System.out.println(userRepository.findAll());
        return null;
        /*return args -> {
            Stream.of("jlong,", User.withDefaultPasswordEncoder().username("jlong").password("spring").roles("USER").build().getPassword())
                    .map(tpl -> tpl.split(","))
                    .forEach(tpl -> accountRepository.save(new UserEntity(tpl[0], tpl[1], true)));
            *//*Stream.of("jlong," + userPasswordEncoder.encode("spring"))
                    .map(tpl -> tpl.split(","))
                    .forEach(tpl -> accountRepository.save(new UserEntity(tpl[0], tpl[1], true)));*//*
        };*/
    }


    public static void main(String[] args) {
        SpringApplication.run(VerySecureApp4Application.class, args);
    }
}


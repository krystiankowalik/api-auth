package com.krystiankowalik.verysecureapp4;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;

@SpringBootApplication
public class VerySecureApp4Application {

    @Bean
    CommandLineRunner demo(AccountRepository accountRepository, @Autowired PasswordEncoder userPasswordEncoder) {
        System.out.println("Hello!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!**********************");
        System.out.println(userPasswordEncoder.encode("spring"));
        accountRepository.save(new Account("jlong", userPasswordEncoder.encode("spring"), true));
        System.out.println(accountRepository.findAll());
        return null;
        /*return args -> {
            Stream.of("jlong,", User.withDefaultPasswordEncoder().username("jlong").password("spring").roles("USER").build().getPassword())
                    .map(tpl -> tpl.split(","))
                    .forEach(tpl -> accountRepository.save(new Account(tpl[0], tpl[1], true)));
            *//*Stream.of("jlong," + userPasswordEncoder.encode("spring"))
                    .map(tpl -> tpl.split(","))
                    .forEach(tpl -> accountRepository.save(new Account(tpl[0], tpl[1], true)));*//*
        };*/
    }


    public static void main(String[] args) {
        SpringApplication.run(VerySecureApp4Application.class, args);
    }
}


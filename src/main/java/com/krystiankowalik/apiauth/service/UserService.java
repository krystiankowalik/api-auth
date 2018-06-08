package com.krystiankowalik.apiauth.service;

import com.krystiankowalik.apiauth.model.UserEntity;
import com.krystiankowalik.apiauth.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    public Optional<UserEntity> getUser(String username) {
        return userRepository.findByUsername(username);
    }

}

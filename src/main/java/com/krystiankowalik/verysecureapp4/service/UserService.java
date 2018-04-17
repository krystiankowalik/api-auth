package com.krystiankowalik.verysecureapp4.service;

import com.krystiankowalik.verysecureapp4.model.UserEntity;
import com.krystiankowalik.verysecureapp4.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

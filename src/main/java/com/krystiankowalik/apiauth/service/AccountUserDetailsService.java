package com.krystiankowalik.apiauth.service;

import com.krystiankowalik.apiauth.model.CustomUserDetails;
import com.krystiankowalik.apiauth.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
class AccountUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    AccountUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("couldn't find the usename " + username + "!"));
    }
}
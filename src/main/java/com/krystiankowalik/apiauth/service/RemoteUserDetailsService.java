package com.krystiankowalik.apiauth.service;

import com.krystiankowalik.apiauth.model.CustomUserDetails;
import com.krystiankowalik.apiauth.model.UserEntity;
import com.krystiankowalik.apiauth.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;

@Service
//@Transactional
class RemoteUserDetailsService implements UserDetailsService {


    @Value("${users.server.address}")
    private String BASE_URL;

    @Value("${users.server.endpoint}")
    private String ENDPOINT;

    public UserEntity getUserByName(String username) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(BASE_URL + ENDPOINT)
                .queryParam("username", username);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<UserEntity> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                UserEntity.class);

        return response.getBody();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return new CustomUserDetails(getUserByName(username));
        } catch (Throwable throwable) {
            throw new UsernameNotFoundException("Couldn't find user:" + username);
        }
    }
}
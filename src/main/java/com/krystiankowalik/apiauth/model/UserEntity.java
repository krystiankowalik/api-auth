package com.krystiankowalik.apiauth.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class UserEntity {

    public UserEntity(String username, String password, boolean active, Set<Authority> authorities) {
        this.username = username;
        this.password = password;
        this.active = active;
        this.authorities = authorities;
    }

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username, password;
    private boolean active;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Authority> authorities;
}
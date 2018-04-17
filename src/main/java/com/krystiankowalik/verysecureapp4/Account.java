package com.krystiankowalik.verysecureapp4;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "account")
class Account {

    public Account(String username, String password, boolean active) {
        this.username = username;
        this.password = password;
        this.active = active;
    }

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 255)
    private String username, password;
    private boolean active;
}
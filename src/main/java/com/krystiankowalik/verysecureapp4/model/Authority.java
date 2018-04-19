package com.krystiankowalik.verysecureapp4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "authorities")
public class Authority implements GrantedAuthority {

    @Id @GeneratedValue
    private long id;
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private UserEntity userEntity;
    private String role;

    public Authority(Role role) {
        this.role= role.description;
    }

    @Override
    public String getAuthority() {
        return role;
    }

    public enum Role {
        USER("ROLE_USER"), ADMIN("ROLE_ADMIN");

         String description;

        Role(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }
}

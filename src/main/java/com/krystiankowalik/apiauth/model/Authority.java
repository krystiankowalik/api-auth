package com.krystiankowalik.apiauth.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Data
@EqualsAndHashCode(of = "authority")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "authorities")
public class Authority implements GrantedAuthority {

    @Id @GeneratedValue
    private long id;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private UserEntity userEntity;
    private String authority;

    public Authority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }



}

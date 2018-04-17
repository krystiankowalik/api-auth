package com.krystiankowalik.verysecureapp4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
class AccountUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Autowired
    AccountUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.findByUsername(username)
                .map(account -> new UserDetails() {
                    @Override
                    public Collection<? extends GrantedAuthority> getAuthorities() {
                        return AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_USER");
                    }

                    @Override
                    public String getPassword() {
                        System.out.println("Getting password: " + account.getPassword());
                        return account.getPassword();
                    }

                    @Override
                    public String getUsername() {
                        return account.getUsername();
                    }

                    @Override
                    public boolean isAccountNonExpired() {
                        return account.isActive();
                    }

                    @Override
                    public boolean isAccountNonLocked() {
                        return account.isActive();
                    }

                    @Override
                    public boolean isCredentialsNonExpired() {
                        return account.isActive();
                    }

                    @Override
                    public boolean isEnabled() {
                        return account.isActive();
                    }
                })
                .orElseThrow(() -> new UsernameNotFoundException("couldn't find the usename " + username + "!"));
    }
}
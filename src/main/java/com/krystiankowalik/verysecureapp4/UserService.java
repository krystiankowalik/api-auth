package com.krystiankowalik.verysecureapp4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Service
public class UserService {

    private final AccountRepository accountRepository;

    @Autowired
    public UserService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void addUser(Account account) {
        accountRepository.save(account);
    }

    public Optional<Account> getUser(String username) {
        return accountRepository.findByUsername(username);
    }
}

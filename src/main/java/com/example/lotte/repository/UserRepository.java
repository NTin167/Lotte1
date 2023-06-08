package com.example.lotte.repository;

import com.example.lotte.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByUsernameAndPassword(String username, String password);
}
package com.springBootProject.bankingApplication.repository;

import com.springBootProject.bankingApplication.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}

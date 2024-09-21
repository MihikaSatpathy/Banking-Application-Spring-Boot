package com.springBootProject.bankingApplication.service;

import com.springBootProject.bankingApplication.dto.AccountDto;
import java.util.List;

public interface AccountService {

    AccountDto createAccount(AccountDto accountDto); //return type is an obj of class AccountDto and parameter is also a object of AccountDto class
    AccountDto getAccountById(Long Id);
    AccountDto deposite(Long Id, double amount);
    AccountDto withdraw(Long Id, double amount);
    List<AccountDto> getAllAccounts();
    String deleteAccount(Long Id);

}

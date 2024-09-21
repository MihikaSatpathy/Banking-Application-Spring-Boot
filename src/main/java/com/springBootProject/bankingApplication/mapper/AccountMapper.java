package com.springBootProject.bankingApplication.mapper;

import com.springBootProject.bankingApplication.dto.AccountDto;
import com.springBootProject.bankingApplication.entity.Account;

public class AccountMapper {

    //convert account dto to Jpa entity : account
    public  static Account mapToAccount(AccountDto accountDto){
        Account account = new Account(
                accountDto.getId(),
                accountDto.getAccountHolderName(),
                accountDto.getBalance()
        );
        return account;
    }

    //convert Jpa entity ie obj of class Account to account dto
    public  static AccountDto mapToAccountDto(Account account){
        //assigning values to dto using gerID() that is in ALLArgs annotation
        AccountDto accountDto = new AccountDto(
                account.getId(),
                account.getAccountHolderName(),
                account.getBalance()
        );
        return accountDto;
    }
}

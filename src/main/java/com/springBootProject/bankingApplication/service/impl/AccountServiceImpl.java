package com.springBootProject.bankingApplication.service.impl;

import com.springBootProject.bankingApplication.dto.AccountDto;
import com.springBootProject.bankingApplication.entity.Account;
import com.springBootProject.bankingApplication.mapper.AccountMapper;
import com.springBootProject.bankingApplication.repository.AccountRepository;
import com.springBootProject.bankingApplication.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service // creates spring bean for this class
public class AccountServiceImpl implements AccountService {

    //before all inject dependency :
    public AccountRepository accountRepository;
    public AccountServiceImpl(AccountRepository accountRepository){
        this.accountRepository=accountRepository;
    }
    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        //here we will convert account dto into JPS entity and save this JPA into DB
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount=  accountRepository.save(account);//save in db
        return AccountMapper.mapToAccountDto(savedAccount);//return to response
    }
    //Here create a rest api after this step;
    //This rest Api will interact with the Create Account Method
    //Go to controller pck create there!
    @Override
    public AccountDto getAccountById(Long Id){
        Account account = accountRepository.findById(Id).orElseThrow(()->new RuntimeException("Account does not exist"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposite(Long Id, double amount){
        //1. get account table row by id,
        //2. user getter annotation's methods to get balance field value from Amount class;
        //3. update the balance
        //4. assign updated balance to Amount class's data field ->balance using set method from setter annotation
        //5. update this Account obj using accountRepository to the db

        Account account = accountRepository.findById(Id).orElseThrow(()-> new RuntimeException("Account does not exist - deposit function"));
        double total = account.getBalance()+amount;
        account.setBalance(total);
        Account saveAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(saveAccount);
    }

    @Override
    public AccountDto withdraw(Long Id, double amount) {
        Account account= accountRepository.findById(Id).orElseThrow(()->new RuntimeException("Account does not exist - withdrawal"));
        double availableBalance = account.getBalance();
        if(availableBalance > amount){
            System.out.println("Balance deducted - withdrawal successful");
            double total = availableBalance - amount;
            account.setBalance(total);
            Account saveAccount = accountRepository.save(account);
            return AccountMapper.mapToAccountDto(saveAccount);
        }else{
            System.out.println("In sufficient balance - withdrawal failed");
        }
        return null;
    }

    @Override
    public List<AccountDto> getAllAccounts(){
        List<Account> allAccounts = accountRepository.findAll();
        //now convert each account in allAccount list to Account Dto and push it in to a list
        //to do so use stream's map and then collection -> toList()
        List<AccountDto> allAccountDto = allAccounts.stream().map((account)->AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());
        return allAccountDto;
    }

    @Override
    public String deleteAccount(Long Id){
        String status="";
        try {
            Account account = accountRepository.findById(Id).orElseThrow(() -> new RuntimeException("Account does not exist - delete"));
            accountRepository.deleteById(Id);
            status="success";
        }catch (RuntimeException e) {
            System.out.println(e);
            status="error";;
        }
        return status;
    }
}

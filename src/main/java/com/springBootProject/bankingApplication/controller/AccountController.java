package com.springBootProject.bankingApplication.controller;

import com.springBootProject.bankingApplication.dto.AccountDto;
import com.springBootProject.bankingApplication.mapper.AccountMapper;
import com.springBootProject.bankingApplication.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    /*
    Steps here :
    1. annotate the class with RestController--as its for rest api
    2.Initialize the base url to which to user will hit
    here using ResultMapping -->("/api/accounts")
     3.Inject dependencies using constructor here uts Account Service
     4.ADD account REST API

     */


    //insert dependency:
    public AccountService accountService;
    public AccountController(AccountService accountService){
        this.accountService=accountService;
    }

    //Add create acct rest api  - by post req. thus use response body to send data from postman . the data received from post man is in request body thus-> @RequestBody AccountDto accountDto
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
        //requestBody will convert json into AccountDto
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }
    //Get acct Rest api -- get the id from url to mention that we use "/{variable name}" in getMapping.
    // --> then we bind the variable name with the variable used in out code for the compiler to understand here its  "Id" .TO DO SO used @PathVariable Annotation.
    @GetMapping("/{Id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long Id){
        AccountDto accountDto= accountService.getAccountById(Id);
        return ResponseEntity.ok(accountDto);
    }

    //Deposit rest api -- basically put req. to update the the balance of user
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id, @RequestBody Map<String,Double> request){
        Double amount = request.get("amount");
        AccountDto accountDto = accountService.deposite(id,amount);
        return ResponseEntity.ok(accountDto);
    }
    //withdrawal rest api
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<Object> withdraw(@PathVariable Long id, @RequestBody Map<String,Double> request){
        Double amount = request.get("amount");
        AccountDto accountDto = accountService.withdraw(id,amount);
        System.out.println("in withdrawal put req - accountDto -:\n"+accountDto);
        if(accountDto == null){
            HashMap<String,String> errorResponse = new HashMap<>();
            errorResponse.put("status","error");
            errorResponse.put("message","Insufficient balance");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
        return ResponseEntity.ok(accountDto);
    }

    //to get all accounts -- takes the default routing ie /api/account
    @GetMapping
    public ResponseEntity<List<AccountDto>> getALlAccounts(){
        List<AccountDto> allAccounts= accountService.getAllAccounts();
        return ResponseEntity.ok(allAccounts);
    }

    //Delete account rest api
    @DeleteMapping("/{Id}")
    public ResponseEntity<Object> deleteAccount(@PathVariable Long Id){
        String deleteStatus = accountService.deleteAccount(Id);
        String message = "";
        if(deleteStatus.equalsIgnoreCase("error")){
            message = "Delete Unsuccessful";
        }else{
            message = "Account with id "+Id+" deleted successfully";
        }
        Map<String,String> responseObject = new HashMap<>(); //Map is a interface cannot initiate it directly use hash map
        responseObject.put("status",deleteStatus);
        responseObject.put("message",message);
        return ResponseEntity.ok(responseObject);
    }

}

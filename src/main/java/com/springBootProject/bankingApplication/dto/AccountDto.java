package com.springBootProject.bankingApplication.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data // this created getter , setter and constructors automatically
@AllArgsConstructor
public class AccountDto {

    //to aid the interface create roes for account table / entity

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "account_holder_name")
    private String accountHolderName;
    private double balance;
}

package com.springBootProject.bankingApplication.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="accounts")
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_holder_name")
    private String accountHolderName;
    private double balance;
    //step 1: created a jpa entity for 'hibernate' to create a table automatically with these def
    //step 2: create spring data jpa repository for the account entity
    //step 3: create a service interface, which the Rest api will call
    //      3.1: use dto class so that service (that is interface) do not directly access table and its colouns from JPA entity class
    //      3.2: use interface that has method of return type dto and parameter of this dto type
    //      3.3: implement the interface in a class inside impl package .(all logics are here )
    //step 4: Create add account rest API that will create obj of the interface and use its methods to CRUD in the db (it only calles the interface class)
}

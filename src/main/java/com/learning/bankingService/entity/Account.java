package com.learning.bankingService.entity;
import org.springframework.data.annotation.Id;
import java.util.Date;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(value = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accountNumber;
    private String accountType;
    private Double accountBalance;
    private String approved;
    private Date dateOfCreation;
    private Customer customerId;
}


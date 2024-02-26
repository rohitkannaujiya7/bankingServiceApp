package com.learning.bankingService.entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import java.util.Date;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
@Document(collection = "account")
@Data
public class Account {
    @Id
    private String accountNumber;
    private String accountType;
    private Double accountBalance;
    private String approved;
    private Date dateOfCreation;
    private Long customerID;
}


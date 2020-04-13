package ca.mcgill.ecse321.eventregistration.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CreditCard {

private String accountNumber;
private Integer amount;

//CARD ID SET&GET//
public void setAccountNumber(String id) {
    this.accountNumber = id;
}
@Id
public String getAccountNumber() {
    return this.accountNumber;
}

//PAYMENT SET&GET//
public void setAmount(int payment) {
    this.amount = payment;
}

public Integer getAmount() {
    return this.amount;
} 

}
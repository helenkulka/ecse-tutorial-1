package ca.mcgill.ecse321.eventregistration.dto;

public class CreditCardDto {
    private String accountNumber;
    private Integer amount;

    public CreditCardDto(String accountNumber, Integer amount) {
        this.accountNumber = accountNumber;
        this.amount = amount;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Integer getAmount() {
        return this.amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

}
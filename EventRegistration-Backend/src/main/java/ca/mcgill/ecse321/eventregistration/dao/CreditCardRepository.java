package ca.mcgill.ecse321.eventregistration.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.eventregistration.model.*;

public interface CreditCardRepository extends CrudRepository<CreditCard, String> {
    
   CreditCard findByAccountNumber(String accountNumber);


}
package ca.mcgill.ecse321.eventregistration.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.eventregistration.model.*;

public interface CreditCardRepository extends CrudRepository<CreditCard, Integer> {
    
    CreditCard findCreditCardByCardID(Integer CardID);


}
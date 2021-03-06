package ca.mcgill.ecse321.eventregistration.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.eventregistration.model.*;


public interface TheatreRepository extends CrudRepository<Theatre, String> {

    Theatre findByTitle(String title);

}
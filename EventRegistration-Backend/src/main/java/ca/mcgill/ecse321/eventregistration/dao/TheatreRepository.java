package ca.mcgill.ecse321.eventregistration.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.eventregistration.model.*;

import java.util.List;

public interface TheatreRepository extends CrudRepository<Theatre, String> {

    List<Theatre> findAllTheatres();

}
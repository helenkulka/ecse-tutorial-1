package ca.mcgill.ecse321.eventregistration.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.eventregistration.model.*;
import java.util.List;

public interface OrganizerRepository extends CrudRepository<Organizer, String> {
    Organizer findByName(String name);
    List<Organizer> findAllOrganizers();
}
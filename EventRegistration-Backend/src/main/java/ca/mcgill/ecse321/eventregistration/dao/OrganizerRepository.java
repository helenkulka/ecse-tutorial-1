package ca.mcgill.ecse321.eventregistration.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.eventregistration.model.*;

public interface OrganizerRepository extends CrudRepository<Organizer, String> {
    Organizer findOrganizerByName(String name);
}
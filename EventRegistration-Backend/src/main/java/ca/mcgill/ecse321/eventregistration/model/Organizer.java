package ca.mcgill.ecse321.eventregistration.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class Organizer extends Person {
    private Set<Event> organizedEvents;

    public void setOrganizes(Event event) {
        this.organizedEvents.add(event);
    }

    @ManyToMany
    public Set<Event> getOrganizes() {
        return this.organizedEvents;
    }

}
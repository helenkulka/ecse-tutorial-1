package ca.mcgill.ecse321.eventregistration.dto;

import java.util.Collections;
import java.util.Set;

import java.util.List;
public class OrganizerDto extends PersonDto {

    private Set<EventDto> eventsOrganized;

    public OrganizerDto() {
    }

    @SuppressWarnings("unchecked")
    public OrganizerDto(String name) {
        this(name, Collections.EMPTY_LIST, Collections.EMPTY_SET);
    }

    public OrganizerDto(String name, List<EventDto> eventsAttended, Set<EventDto> eventsOrganized) {
        super(name,eventsAttended);
        this.eventsOrganized = eventsOrganized;
    }

    public void setEventsOrganized(Set<EventDto> eventsOrganized) {
        this.eventsOrganized = eventsOrganized;
    }

    public Set<EventDto> getEventsOrganized() {
        return this.eventsOrganized;
    }





    

}
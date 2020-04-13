package ca.mcgill.ecse321.eventregistration.model;

import javax.persistence.Entity;

@Entity
public class Theatre extends Event {
    private String theatreTitle;

    public void setTitle(String title) {
        this.theatreTitle = title;
    }
    
    public String getTitle() {
        return this.theatreTitle;
     }

} 
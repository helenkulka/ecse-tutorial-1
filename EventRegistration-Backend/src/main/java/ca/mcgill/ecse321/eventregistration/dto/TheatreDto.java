package ca.mcgill.ecse321.eventregistration.dto;

import java.sql.Date;
import java.sql.Time;

public class TheatreDto extends EventDto {

    private String theatreTitle;

    public TheatreDto(String name, String title) {
        this(name, Date.valueOf("1971-01-01"), Time.valueOf("00:00:00"), Time.valueOf("23:59:59"), title);
    }

    public TheatreDto(String name, Date date, Time startTime, Time endTime, String title) {
        super(name, date, startTime, endTime);
        this.theatreTitle = title;
    }

    public String getTheatreTitle() {
        return this.theatreTitle;
    }

    public void setTheatreTitle(String title) {
        this.theatreTitle = title;
    }

}
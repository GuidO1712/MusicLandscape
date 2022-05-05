package MusicLandscape.entities;

import MusicLandscape.Date;
import MusicLandscape.Venue;

public class Event {
    private Artist artist;
    private int attendees;
    private Date date;
    private String description;
    private Venue venue;

    public Event(){
        artist = new Artist();
        setDescription("");
    }

    public Event(Event e){
        this.artist = new Artist(e.getArtist());
        this.attendees = e.attendees;
        this.date = new Date(e.getDate());
        this.description = e.description;
        this.venue = new Venue(e.getVenue());
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        if(artist != null)
            this.artist = artist;
    }

    public int getAttendees() {
        return attendees;
    }

    public void setAttendees(int attendees) {
        if(attendees > 0 )
            this.attendees = attendees;
    }

    public Date getDate() {
        if(date != null) {
            return new Date(date);
        } else {
            return null;
        }
    }

    public void setDate(Date date) {
        if(date != null){
            this.date = new Date(date);
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if(description != null)
            this.description = description;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    @Override
    public String toString(){
        String text = "";

        if(getArtist() != null && getArtist().getName() != null){
            text += getArtist().getName();
        } else {
            text += "unknown";
        }

        text += " @ ";

        if(getVenue() != null && getVenue().getName() != null){
            text += getVenue().getName();
        } else {
            text += "unknown";
        }

        text += " on ";

        if(getDate() != null && getDate().numericString() != null){
            text += getDate().numericString();
        } else {
            text += "null";
        }

        text += "\n";

        if(getDescription() != null){
            text += getDescription();
        } else {
            text += "unknown";
        }

        text += ("\n(" + getAttendees() + " attending (" + impact() + "))");

        return text;
    }

    public int impact(){
        return attendees*2;
    }
}

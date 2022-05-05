package MusicLandscape.entities;

import java.util.Objects;

public class Track {
    private String title;
    private int duration;
    private Artist writer;
    private Artist performer;
    private int year;

    public Track(){
        setDuration(0);
        setYear(1900);
        setWriter(new Artist());
        setPerformer(new Artist());
    }

    public Track(String title){
        this();
        setTitle(title);
    }

    public Track(Track t){
        this.title = t.title;
        this.performer = new Artist(t.getPerformer());
        this.writer = new Artist(t.getWriter());
        this.duration = t.duration;
        this.year = t.year;
    }

    public String getTitle() {
        return Objects.requireNonNullElse(title, "unknown title");
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        if (duration > 0)
            this.duration = duration;
    }

    public Artist getWriter() {
        return writer;
    }

    public void setWriter(Artist writer) {
        if (writer != null)
            this.writer = writer;
    }

    public Artist getPerformer() {
        return performer;
    }

    public void setPerformer(Artist performer) {
        if(!Objects.isNull(performer)){
            this.performer = performer;
        }
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if (year >= 1900 && year <= 2999)
            this.year = year;
    }

    public boolean writerIsKnown() {
        if (writer != null && writer.getName() != null) {
            return true;
        } else {
            return false;
        }
    }

    public String getString() {
        String text = "";

        if (title != null) {
            text += String.format("%10.10s", title);
        } else {
            text += String.format("%10s", "unknown");
        }

        text += " by ";

        if(!Objects.isNull(writer) && !Objects.isNull(writer.getName())){
            text += String.format("%10.10s", writer.getName());
        } else {
            text += String.format("%10s", "unknown");
        }

        text += " performed by ";

        if(!Objects.isNull(performer) && !Objects.isNull(performer.getName())){
            text += String.format("%10.10s", performer.getName());
        } else {
            text += String.format("%10s", "unknown");
        }

        int min = duration/60;
        int sec = duration%60;
        text += (" (" + String.format("%02d", min) + ":" + String.format("%02d", sec) + ")");

        return text;
    }
}

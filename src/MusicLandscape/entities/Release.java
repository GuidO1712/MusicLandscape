package MusicLandscape.entities;

public abstract class Release {
    private Artist artist;
    private String title;
    private int year;

    public Release(){
        setArtist(new Artist());
        setYear(1900);
    }

    public Release(Release orig){
        this.artist = new Artist(orig.getArtist());
        this.title = orig.title;
        this.year = orig.year;
    }

    public Release(String title, Artist artist, int year){
        setTitle(title);
        setArtist(artist);
        setYear(year);
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if (year >= 0)
            this.year = year;
        else
            this.year = 1900;
    }

    public String toString(){
        String text = "";
        if(getTitle() != null){
            text += getTitle();
        } else {
            text += "unknown";
        }

        text += "-";

        if(getArtist() != null){
            text += getArtist();
        } else {
            text += "unknown";
        }

        text += "-";

        if(getYear() == 0){
            text += "unknown";
        } else {
            text += getYear();
        }

        text += ("-" + totalTime());

        return text;
    }

    public abstract int totalTime();
}

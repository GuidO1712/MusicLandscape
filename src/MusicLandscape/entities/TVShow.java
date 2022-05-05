package MusicLandscape.entities;

public class TVShow extends Event{
    private String name;
    private int viewers;

    public TVShow(){
        super();
    }

    public TVShow(Event e){
        super(e);
    }

    public TVShow(TVShow tvs){
        super(tvs);
        this.name = tvs.name;
        this.viewers = tvs.viewers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getViewers() {
        return viewers;
    }

    public void setViewers(int v) {
        if(v >= 0) this.viewers = v;
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

        if(getName() != null){
            text += getName();
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

        int audience = getAttendees() + getViewers();
        text += ("\n(" + audience + " attending (" + impact() + "))");

        return text;
    }

    public int impact(){
        return (getViewers()+getAttendees())*2;
    }
}

package MusicLandscape.entities;

public class Artist {
    private String name;

    /*public Artist(){

    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name != null && !name.trim().equals(""))
            this.name = name;
    }
}

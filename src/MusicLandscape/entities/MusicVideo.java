package MusicLandscape.entities;

public class MusicVideo extends Release{
    private Track track;

    public MusicVideo(){

    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    @Override
    public String toString(){
        return super.toString() + "-(Video)";
    }

    @Override
    public int totalTime() {
        if(getTrack() != null){
            return getTrack().getDuration();
        } else {
            return 0;
        }
    }
}

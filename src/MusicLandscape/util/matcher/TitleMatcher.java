package MusicLandscape.util.matcher;

import MusicLandscape.entities.Track;
import MusicLandscape.util.MyMatcher;

public class TitleMatcher extends MyMatcher<Track> {

    private String pattern;

    public TitleMatcher(String pat){
        super(pat);
    }

    @Override
    public boolean matches(Track track) {
        if(track.getTitle().startsWith(pattern)){
            return true;
        }
        return false;
    }

    @Override
    public final void setPattern(String pat) {
        if(pat != null){
            this.pattern = pat;
        }
    }

    @Override
    public String getPattern() {
        return this.pattern;
    }

    @Override
    public String toString(){
        return "title starts with (" + getPattern() + ")";
    }
}

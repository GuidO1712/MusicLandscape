package MusicLandscape.util.matcher;

import MusicLandscape.entities.Track;
import MusicLandscape.util.MyMatcher;

public class PerfomerMatcher extends MyMatcher<Track> {

    private String pattern;

    public PerfomerMatcher(String pat){
        super(pat);
    }

    @Override
    public boolean matches(Track track) {
        if(track.getPerformer().getName().startsWith(pattern)){
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
        return "performer starts with (" + getPattern() + ")";
    }
}
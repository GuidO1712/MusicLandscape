package MusicLandscape.util.matcher;

import MusicLandscape.entities.Track;
import MusicLandscape.util.MyMatcher;

public class DurationMatcher extends MyMatcher<Track> {
    private int lower;
    private int upper;

    public DurationMatcher(){
        super("0 2147483647");
        //Integer.MAX_VALUE = 2147483647
    }

    public DurationMatcher(String pat){
        super(pat);
    }

    @Override
    public boolean matches(Track track) {
        if(lower <= track.getDuration() && track.getDuration() <= upper){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void setPattern(String pat) {
        String[] parts = pat.trim().split(" ");
        try{
            int newLower = Integer.parseInt(parts[0]);
            int newUpper = Integer.parseInt(parts[1]);

            if(this.lower < newLower || this.lower == Integer.MIN_VALUE){
                this.lower = newLower;
            }

            if(newLower <= newUpper){
                if(this.upper < newUpper || this.upper == Integer.MAX_VALUE){
                    this.upper = newUpper;
                }
            }
        } catch(NumberFormatException e){
            //System.out.println("Pattern was not valid! (lower limit + space + upper limit)");
        }
    }

    @Override
    public String getPattern() {
        return lower + " " + upper;
    }

    @Override
    public String toString(){
        return "duration in range (" + getPattern() + ")";
    }
}

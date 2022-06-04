package MusicLandscape.util.matcher;

import MusicLandscape.entities.Track;
import MusicLandscape.util.MyMatcher;

public class YearMatcher extends MyMatcher<Track> {
    private int lower;
    private int upper;

    public YearMatcher(){
        super("1900 2999");
    }

    public YearMatcher(String pat){
        super(pat);
    }

    @Override
    public boolean matches(Track track) {
        if(lower <= track.getYear() && track.getYear() <= upper){
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

            if(this.lower < newLower || this.lower == 1900){
                this.lower = newLower;
            }

            if(newLower <= newUpper){
                if(this.upper < newUpper || this.upper == 2999){
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
        return "year in range (" + getPattern() + ")";
    }
}

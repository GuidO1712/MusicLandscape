package MusicLandscape.util.comparators;

import MusicLandscape.entities.Track;

import java.util.Comparator;

public class DurationComparator implements Comparator<Track> {
    public DurationComparator(){

    }

    @Override
    public int compare(Track arg0, Track arg1) {
        if(arg0.getDuration() < arg1.getDuration()){
            return -1;
        } else if(arg0.getDuration() > arg1.getDuration()){
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString(){
        return "by duration";
    }
}

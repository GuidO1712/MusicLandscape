package MusicLandscape.util.comparators;

import MusicLandscape.entities.Track;

import java.util.Comparator;

public class YearComparator implements Comparator<Track> {
    @Override
    public int compare(Track o1, Track o2) {
        return o1.getYear()-(o2.getYear());
    }

    @Override
    public String toString(){
        return "by year";
    }
}

package MusicLandscape.util.comparators;

import MusicLandscape.entities.Track;

import java.util.Comparator;

public class PerformerComparator implements Comparator<Track> {
    @Override
    public int compare(Track o1, Track o2) {
        return o1.getPerformer().compareTo(o2.getPerformer());
    }

    @Override
    public String toString(){
        return "by performer";
    }
}

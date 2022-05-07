package MusicLandscape.util.comparators;

import MusicLandscape.entities.Event;
import MusicLandscape.util.comparators.MyEventComparator;

public class MyEventDateComparator extends MyEventComparator {
    @Override
    public int compare(Event e1, Event e2) {
        if((e1 == null && e2 == null)){
            return 0;
        } else if(e1 == null && e2 != null){
            return -1;
        } else if(e1 != null && e2 == null) {
            return 1;
        } else if(e1.getDate() == null && e2.getDate() == null) {
            return 0;
        } else if(e1.getDate() == null && e2.getDate() != null){
            return -1;
        } else if(e1.getDate() != null && e2.getDate() == null){
            return 1;
        } else {
            return e1.getDate().compareTo(e2.getDate());
        }
    }
}

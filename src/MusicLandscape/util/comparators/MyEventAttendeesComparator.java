package MusicLandscape.util.comparators;

import MusicLandscape.entities.Event;

public class MyEventAttendeesComparator extends MyEventComparator{
    @Override
    public int compare(Event e1, Event e2) {
        if(e1 == null && e2 == null){
            return 0;
        } else if(e1 == null && e2 != null){
            return -1;
        } else if(e1 != null && e2 == null) {
            return 1;
        } else{
            if(e1.getAttendees() > e2.getAttendees()){
                return 1;
            } else if(e1.getAttendees() < e2.getAttendees()){
                return -1;
            } else {
                return 0;
            }
        }
    }
}

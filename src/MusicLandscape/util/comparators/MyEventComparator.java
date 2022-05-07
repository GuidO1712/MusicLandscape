package MusicLandscape.util.comparators;

import MusicLandscape.entities.Event;

public abstract class MyEventComparator {
    public MyEventComparator(){

    }

    public abstract int compare(Event e1, Event e2);
}

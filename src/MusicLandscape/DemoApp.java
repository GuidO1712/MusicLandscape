package MusicLandscape;

import MusicLandscape.entities.Album;
import MusicLandscape.entities.Artist;
import MusicLandscape.entities.Event;
import MusicLandscape.entities.Track;
import MusicLandscape.util.comparators.MyDurationComparator;
import MusicLandscape.util.comparators.MyEventAttendeesComparator;
import MusicLandscape.util.comparators.MyEventComparator;
import MusicLandscape.util.formatters.MyCSVTrackFormatter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class DemoApp {
    public static void main(String[] args) {
        Random random = new Random();

        Track[] tracks = new Track[5];
        for(int i = 0; i < tracks.length; i++){
            Track t = new Track();
            t.setTitle("New Title " + i);
            t.setPerformer(new Artist("Peter " + i));
            t.setWriter(new Artist("Writer " + i));
            t.setDuration(random.nextInt(1000));
            tracks[i] = t;
        }

        Album a = new Album();
        a.setTitle("Awesome Album");
        a.addTrack(tracks[0]);
        a.addTrack(tracks[1]);
        a.addTrack(tracks[2]);
        a.addTrack(tracks[3]);
        a.addTrack(tracks[4]);

        System.out.println(a.toString());
        System.out.println("------------------------------------------\n");

        MyCSVTrackFormatter csv = new MyCSVTrackFormatter();

        for(int i = 0; i < tracks.length; i++){
            System.out.println(csv.format(tracks[i]));
        }
        System.out.println("------------------------------------------\n");

        ArrayList<Event> events = new ArrayList<>();

        for(int i = 0; i < 8; i++){
            Event event = new Event();
            event.setAttendees(random.nextInt(15));
            event.setArtist(new Artist("Artist " + i));
            event.setDate(new Date());
            event.setDescription("My awesome Event " + random.nextInt(15));
            events.add(event);
        }

        System.out.println("Not sorted");
        for(Event eItem : events){
            System.out.println(eItem.toString());
        }

        System.out.println("------------------------------------------\n");

        MyEventAttendeesComparator myEAC = new MyEventAttendeesComparator();

        System.out.println("Sorted after attendees (Bubble sort)");

        for(int i = 1; i < events.size(); i++){
            for(int j = 0; j < events.size()-1; j++ ) {

                int res = myEAC.compare(events.get(j), events.get(j+1));

                if (res > 0) {
                    Event eTemp = events.get(j);
                    events.set(j, events.get(j + 1));
                    events.set(j + 1, eTemp);
                }
            }
        }


        for(Event e : events){
            System.out.println(e.toString());
        }
        System.out.println("------------------------------------------\n");
    }
}

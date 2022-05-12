package MusicLandscape;

import MusicLandscape.entities.*;

import java.util.Random;

public class DemoApp {
    public static void main(String[] args) {
        Event[] events = new Event[15];

        for(int i = 0; i < events.length; i++){
            Date d = new Date();
            Random random = new Random();
            d.addMonth(random.nextInt(12));
            d.addDay(random.nextInt(28));
            d.addYear(random.nextInt(60));

            if(i%3 == 0){
                Event e = new Event();
                e.setArtist(new Artist("New Artist " + i));
                e.setDescription("New Event " + i);
                e.setAttendees(random.nextInt(150));
                e.setDate(d);
                events[i] = e;
            } else if(i%3 == 1){
                Concert c = new Concert();
                c.setDescription("New Concert " + i);
                c.setArtist(new Artist("New Artist " + i));
                c.setAttendees(random.nextInt(4500));
                c.setDate(d);

                for(int j = 0; j < random.nextInt(10)+1; j++){
                    Track t = new Track();
                    t.setTitle("New Track " + j);
                    t.setDuration(random.nextInt(350));
                    c.addTrack(t);
                }

                events[i] = c;
            } else if(i%3 == 2){
                TVShow tvs = new TVShow();
                tvs.setAttendees(random.nextInt(5555));
                tvs.setDate(d);
                tvs.setDescription("New TV-Show " + i);
                tvs.setName("New TV-Name " + i);
                events[i] = tvs;
            }
        }

        for(int i = 0; i < events.length; i++){
            if(events[i] != null){
                System.out.println(events[i]);
                System.out.println("-------------------------------------------");
            }
        }
    }
}

package MusicLandscape;

import MusicLandscape.container.MyTrackContainer;
import MusicLandscape.entities.Artist;
import MusicLandscape.entities.Track;
import MusicLandscape.util.comparators.DurationComparator;
import MusicLandscape.util.formatters.ShortTrackFormatter;
import MusicLandscape.util.matcher.DurationMatcher;

import java.util.Random;

public class DemoApp {
    public static void main(String[] args) {
        MyTrackContainer container = new MyTrackContainer();
        Random random = new Random();
        Track[] tracks = new Track[5];

        for(int i = 0; i < tracks.length; i++){
            Track t = new Track();
            t.setDuration(random.nextInt(400));
            t.setWriter(new Artist("Artist " + i));
            t.setTitle("Title " + i);
            tracks[i] = t;
        }

        container.addAll(tracks);
        System.out.println(container.size());


        ShortTrackFormatter shorty = new ShortTrackFormatter();

        System.out.println("Format: " + shorty.toString());
        System.out.println("Unsorted:");

        container.reset();
        Track[] myNewList = container.selection();

        for(Track tItem : myNewList){
            System.out.println(shorty.format(tItem));
        }
        System.out.println("------------------------------------------");
        System.out.println("Sorted by duration asc:");
        DurationComparator dc = new DurationComparator();

        container.sort(dc, true);
        myNewList = container.selection();

        for(Track tItem : myNewList){
            System.out.println(shorty.format(tItem));
        }

        System.out.println("------------------------------------------");
        System.out.println("Sorted by duration dsc:");

        container.sort(dc, false);
        myNewList = container.selection();

        for(Track tItem : myNewList){
            System.out.println(shorty.format(tItem));
        }

        System.out.println("------------------------------------------");
        System.out.println("Filtered duration matcher (0 100):");

        DurationMatcher dm = new DurationMatcher();
        dm.setPattern("0 100");
        System.out.println(dm.toString());

        container.filter(dm);
        myNewList = container.selection();

        for(Track tItem : myNewList){
            System.out.println(shorty.format(tItem));
        }

        System.out.println("------------------------------------------");
        System.out.println("Container remove()");
        container.remove();
        myNewList = container.selection();

        for(Track tItem : myNewList){
            System.out.println(shorty.format(tItem));
        }
    }
}

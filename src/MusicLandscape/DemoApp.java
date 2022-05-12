package MusicLandscape;

import MusicLandscape.entities.Artist;
import MusicLandscape.entities.Track;
import MusicLandscape.util.formatters.CSVTrackFormatter;
import MusicLandscape.util.io.MyTrackCSVReader;
import MusicLandscape.util.io.MyWriter;

import java.io.*;
import java.util.Random;

public class DemoApp {
    public static void main(String[] args) throws IOException {
        BufferedReader bfr = new BufferedReader(new FileReader("mytracks.csv"));
        MyTrackCSVReader reader = new MyTrackCSVReader(bfr);

        Track t = reader.get();
        Track t1 = reader.get();
        Track t2 = reader.get();
        Track t3 = reader.get();
        Track t4 = reader.get();

        System.out.println(t.toString());
        //System.out.println(t1.toString());
        System.out.println(t2.toString());
        System.out.println(t3.toString());
        System.out.println(t4.toString());

        Track newTrack = new Track();
        newTrack.setTitle("Title");
        newTrack.setWriter(new Artist("Bob"));
        newTrack.setPerformer(new Artist("Bob II"));
        newTrack.setDuration(1000);
        newTrack.setYear(2022);

        CSVTrackFormatter formatter = new CSVTrackFormatter();

        Random random = new Random();

        MyWriter writer = new MyWriter<>(new FileWriter(new File("myLatestTracks.csv")), formatter);

        for(int i = 0; i < random.nextInt(30); i++){
            Track tItem = new Track();
            tItem.setYear(2000 + random.nextInt(22));
            tItem.setDuration(random.nextInt(1500));
            tItem.setTitle("New Title " + random.nextInt(150));
            writer.put(tItem);
        }

        writer.close();
    }
}

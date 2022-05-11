package MusicLandscape.util.io;

import MusicLandscape.entities.Artist;
import MusicLandscape.entities.Track;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.Buffer;

public class MyTrackCSVReader extends MyReader<Track> {
    private static final int TITLE = 0;
    private static final int WRITER = 1;
    private static final int PERFORMER = 2;
    private static final int DURATION = 3;
    private static final int YEAR = 4;

    public MyTrackCSVReader(BufferedReader in){
        super(in);
    }

    @Override
    public Track get() {
        try{
            Track t = new Track();
            String line = in.readLine();
            String[] parts = line.split(",");
            if(parts.length == 5) {
                t.setTitle(parts[TITLE].trim());
                t.setWriter(new Artist(parts[WRITER].trim()));
                t.setPerformer(new Artist(parts[PERFORMER].trim()));
                t.setDuration(Integer.parseInt(parts[DURATION]));
                t.setYear(Integer.parseInt(parts[YEAR]));
                return t;
            } else {
                throw new Exception(line + "Error parsing.");
            }
        } catch (IOException e){
            System.out.println("Error reading.");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}

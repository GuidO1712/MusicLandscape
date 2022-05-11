package MusicLandscape.util.formatters;

import MusicLandscape.entities.Track;
import MusicLandscape.util.MyFormatter;

public class CSVTrackFormatter implements MyFormatter<Track> {
    public CSVTrackFormatter(){

    }

    @Override
    public String header() {
        return "Title,Writer,Performer,duration,year";
    }

    @Override
    public String format(Track track) {
        String text = "";
        if(track.getTitle() != null){
            text += (track.getTitle() + ",");
        }

        if(track.getWriter() != null && track.getWriter().getName() != null){
            text += (track.getWriter().getName() + ",");
        }

        if(track.getPerformer() != null && track.getPerformer().getName() != null){
            text += (track.getPerformer().getName() + ",");
        }

        text += (track.getDuration() + ",");

        text += (track.getYear() + ";");

        return text;
    }

    @Override
    public String topSeparator() {
        return "";
    }

    @Override
    public String toString(){
        return "CSV format[Title,Writer,Performer,duration,year]";
    }
}

package MusicLandscape.util.formatters;

import MusicLandscape.entities.Track;
import MusicLandscape.util.MyFormatter;

public class LongTrackFormatter implements MyFormatter<Track> {
    @Override
    public String header() {
        String text = String.format("%-10.10s", "Title");
        text += " (min:sec)";
        text += " written by Writer";
        text += " performed by Performer";
        text += " in Year";

        return text;
    }

    @Override
    public String format(Track track) {
        String text = "";

        text += String.format("%-10.10s", track.getTitle());

        text += (" (" + String.format("%02d:%02d", track.getDuration()/60,track.getDuration()%60)+ ")");

        text += (" written by  " + track.getWriter().getName());

        text += (" performed by  " + track.getPerformer().getName());

        text += (" in " + track.getYear());

        return text;
    }

    @Override
    public String topSeparator() {
        String text = "";
        for(int i = 0; i < header().length(); i++){
            text += "-";
        }
        return text;
    }

    @Override
    public String toString(){
        return "long format [title (min:sec) written by writer performed by performer in year";
    }
}

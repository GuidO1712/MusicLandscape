package MusicLandscape.util.formatters;

import MusicLandscape.entities.Track;
import MusicLandscape.util.MyFormatter;

public class ShortTrackFormatter implements MyFormatter<Track> {
    @Override
    public String format(Track track) {
        String text = "";

        text += String.format("%-10.10s", track.getTitle());

        text += (" (" + String.format("%02d:%02d", track.getDuration()/60,track.getDuration()%60)+ ")");

        return text;
    }

    @Override
    public String header() {
        String text = String.format("%-10.10s", "Title");
        text += " (min:sec)";

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
        return "short format [Title (min:sec)]";
    }
}

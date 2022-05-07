package MusicLandscape.util.formatters;

import MusicLandscape.entities.Track;

public class MyCSVTrackFormatter extends MyTrackFormatter {
    @Override
    public String format(Track t) {
        String csv = t.getTitle() + "," + t.getPerformer() + "," + t.getWriter() + "," + t.getYear() + "," + t.getDuration() + ";";
        return csv;
    }
}

package MusicLandscape.util.formatters;

import MusicLandscape.entities.Track;
import MusicLandscape.util.MyFormatter;

public class XMLFormatter implements MyFormatter<Track> {
    public XMLFormatter(){

    }

    @Override
    public String header() {
        return "<track><title></title><writer><name></name></writer><performer><name></name></performer><duration></duration><year></year></track>";
    }

    @Override
    public String format(Track track) {
        String text = "<track hashcode=\""+ track.hashCode() + "\">" ;
        if(track.getTitle() != null){
            text += ("\n\t<title>" +track.getTitle() + "</title>");
        }

        if(track.getWriter() != null && track.getWriter().getName() != null){
            text += ("\n\t<writer>\n\t\t<name>" + track.getWriter().getName() + "</name>\n\t</writer>");
        }

        if(track.getPerformer() != null && track.getPerformer().getName() != null){
            text += ("\n\t<performer>\n\t\t<name>" + track.getPerformer().getName() + "</name>\n\t</performer>");
        }

        text += ("\n\t<duration>" + track.getDuration() + "</duration>");

        text += ("\n\t<year>" + track.getYear() + "</year>");

        text += "\n</track>";

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
        return "XML format\n<track>  \n\t<title></title>\n\t<writer></writer>\n\t<performer></performer>\n\t<duration></duration>\n\t<year></year>\n</track>";
    }
}

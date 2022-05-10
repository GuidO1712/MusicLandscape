package MusicLandscape.container;

import MusicLandscape.entities.Track;
import MusicLandscape.util.MyMatcher;

import java.util.*;

public class MyTrackContainer {
    private List<Track> selection;
    private Set<Track> tracks;

    public MyTrackContainer(){
        selection = new ArrayList<>();
        tracks = new HashSet<>();
    }

    public MyTrackContainer(Iterable<Track> t){
        if(t != null){
            tracks = new HashSet<>();
            for(Track trackItem : t){
                tracks.add(trackItem);
            }
            reset();
        }
    }

    public MyTrackContainer(Track[] t){
        if(t != null){
            addAll(t);
        }
        reset();
    }

    public boolean add(Track t){
        if(t == null){
            return false;
        } else if(tracks == null) {
            tracks = new HashSet<>();
        } else if (tracks.contains(t)){
            return false;
        }

        tracks.add(t);
        return true;
    }

    public int addAll(Track[] t){
        int counter = 0;

        if(tracks == null){
            tracks = new HashSet<>();
        }

        for (Track track : t) {
            if (track != null) {
                tracks.add(track);
                counter++;
            }
        }
        return counter;
    }

    public int filter(MyMatcher<Track> matcher){
        int counter = 0;

        if(matcher != null && selection != null){
            for(Iterator<Track> iterator = selection.iterator(); iterator.hasNext();){
                Track trackItem = iterator.next();
                if(!matcher.matches(trackItem)){
                    iterator.remove();
                    counter++;
                }
            }
        }
        return counter;
    }

    public int remove(){
        int size = 0;

        if(selection != null){
            size = selection.size();
        } else {
            selection = new ArrayList<>();
        }

        if(tracks != null){
            for(Track trackItem : selection){
                tracks.remove(trackItem);
            }
        }

        reset();

        return size;
    }

    public void reset(){
        selection = new ArrayList<>();

        selection.addAll(tracks);
    }

    public Track[] selection(){
        Track[] tList;

        if(selection != null) {
            tList = new Track[selection.size()];

            selection.toArray(tList);
        } else {
            tList = new Track[0];
        }

        return tList;
    }

    public int size(){
        return tracks.size();
    }

    public void sort(Comparator<Track> theComp, boolean asc){
        if(asc){
            selection.sort(theComp);
        } else{
            selection.sort(theComp.reversed());
        }
    }
}

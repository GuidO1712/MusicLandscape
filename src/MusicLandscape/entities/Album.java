package MusicLandscape.entities;

public class Album extends Release{

    private TrackListItem trackListHead;

    public Album(){
        super();
    }

    public Album(Album orig){
        super(orig);
        this.trackListHead = orig.trackListHead;
    }

    public Album(String title, Artist artist, int year){
        //super(title, artist, year);
        this.setTitle(title);
        this.setArtist(artist);
        this.setYear(year);
    }

    public boolean addTrack(Track t){

        if(t != null){
            if(trackListHead == null){
                trackListHead = new TrackListItem(t);
                return true;
            } else {
                TrackListItem tli = trackListHead;
                while(true){
                    if(tli.next == null){
                        tli.next = new TrackListItem(t);
                        return true;
                    }
                    tli = tli.next;
                }
            }
        } else {
            return false;
        }
    }

    public Track[] getTracks(){
        Track[] tracks = new Track[nrTracks()];
        TrackListItem tli = trackListHead;

        for(int i = 0; i < tracks.length; i++){
            tracks[i] = tli.track;
            tli = tli.next;
        }

        return tracks;
    }

    public int nrTracks(){
        int count = 1;

        TrackListItem tli = trackListHead;

        if(tli == null){
            return 0;
        } else {
            while(tli.next != null){
                count++;
                tli = tli.next;
            }
            return count;
        }
    }

    public Track removeTrack(int n){
        // Case no head exist
        if(trackListHead == null){
            return null;
        }

        // Case remove id is higher than nrTracks
        if(n > nrTracks()-1){
            return null;
        }

        //Case head is removed
        if(n == 0){
            TrackListItem tli = trackListHead;
            //Case there is a second track and this is becoming the new Head
            if(tli.next != null){
                trackListHead = tli.next;
            }
            trackListHead = null;
            return tli.track;
        } else if (n > 0) {
            TrackListItem tli = trackListHead;
            TrackListItem tliNext = trackListHead.next;

            for(int i = 0; i < n-1; i++){
                tli = tliNext;
                tliNext = tli.next;
            }

            tli.next = tliNext.next;

            return tliNext.track;

        } else {
            return null;
        }
    }

    @Override
    public String toString(){
        String text = "[";

        TrackListItem tli = trackListHead;

        for(int i = 0; i < nrTracks(); i++){
            text += ("[" + tli.track.getTitle() + "]");
            tli = tli.next;
        }

        text += "]";

        return super.toString() +  "\n" + text;
    }

    @Override
    public int totalTime() {
        TrackListItem tli = trackListHead;

        if(tli == null){
            return 0;
        }

        if(tli.next == null){
            return tli.track.getDuration();
        }

        int totalTime = 0;

        while(tli != null){
            totalTime += tli.track.getDuration();
            tli = tli.next;
        }

        return totalTime;
    }

    private class TrackListItem{
        private TrackListItem next;
        private Track track;

        public TrackListItem(Track t) {
            this.track = t;
        }
    }
}

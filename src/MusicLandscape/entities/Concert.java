package MusicLandscape.entities;

public class Concert extends Event {
    private int nextIdx;
    private Track[] setList;

    public Concert(){

    }

    public boolean addTrack(Track t){
        if(t != null){
            if(setList == null){
                setList = new Track[0];
                nextIdx = 0;
            }

            ensureCapacity(setList.length+1);

            setList[nextIdx] = t;
            nextIdx++;
            return true;
        } else {
            return false;
        }
    }

    public void ensureCapacity(int length){
        if(length > setList.length && length > 0){
            Track[] temp = setList.clone();
            setList = new Track[length];

            for(int i = 0; i < temp.length; i++){
                setList[i] = temp[i];
            }
        }
    }

    public Track[] getSetList(){
        if(setList == null) {
            setList = new Track[0];
        }

        Track[] getList = new Track[setList.length];

        for(int i = 0; i < setList.length; i++){
            getList[i] = new Track(setList[i]);
        }

        return getList;
    }

    public void setSetList(Track[] tracks){
        for(int i = 0; i < tracks.length; i++){
            if(tracks[i] != null) {
                addTrack(new Track(tracks[i]));
            }
        }
    }

    public void resetSetList(){
        setList = new Track[0];
        nextIdx = 0;
    }

    public int nrTracks(){
        if(setList != null){
            return setList.length;
        } else {
            return 0;
        }
    }

    public int duration(){
        int totalDuration = 0;
        if(setList != null){
            for(int i = 0; i < setList.length; i++){
                totalDuration += setList[i].getDuration();
            }
        }
        return totalDuration;
    }

    public int impact(){
        return getAttendees()*((duration()/60/30)+1);
    }

    @Override
    public String toString(){
        String text = (nrTracks() + " tracks played, total duration ");

        int durationInMin = duration()/60;
        int hh = durationInMin/60;
        int mm = durationInMin%60;

        text += (String.format("%02d", hh) + ":" + String.format("%02d", mm) + ".");

        return super.toString() + "\n" + text;
    }
}

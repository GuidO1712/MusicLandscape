package MusicLandscape.util.io;

import MusicLandscape.util.MyFormatter;

import java.io.FileWriter;
import java.io.IOException;

public class MyWriter<T> {
    protected FileWriter out;
    private MyFormatter<T> theFormat;

    public MyWriter(FileWriter file, MyFormatter<T> theFormat){
        if(file == null){
            throw new IllegalArgumentException("expected non-null FileWriter");
        } else if(theFormat  == null){
            throw new IllegalArgumentException("expected non-null MyFormatter");
        } else {
            this.out = file;
            this.theFormat = theFormat;
        }
    }

    public void close() {
        try{
            out.close();
        } catch (IOException e){

        }
    }

    public boolean put(T t){
        try{
            if(t != null){
                out.write(theFormat.format(t) + "\n");
                return true;
            }
            return false;
        } catch (IOException e){
            return false;
        }
    }
}

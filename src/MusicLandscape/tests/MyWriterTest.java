// **************************************************
//		
//       git.rev = 234
//  git.revision = fdd4980be270473bdd7e8206afeda65ab6e4c3a4
//         stage = ES06
//
// ***************************************************
package MusicLandscape.tests;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static org.testng.Assert.*;

import org.testng.annotations.Test;

import MusicLandscape.entities.Artist;
import MusicLandscape.entities.Track;
import MusicLandscape.util.formatters.CSVTrackFormatter;
import MusicLandscape.util.io.MyWriter;

/**
 * 
 * @author TeM
 * @version 234
 * @Stage ES06
 *
 */
public class MyWriterTest {

  @Test(description="test if IllegalArgumentException with text \"expected non-null FileWriter\" is thrown", expectedExceptions = { IllegalArgumentException.class }, expectedExceptionsMessageRegExp = "expected non-null FileWriter")
  public void MyWriterNullFile() {
    MyWriter<Track> myW= new MyWriter<Track>(null, null);
  }
  @Test(description="test if IllegalArgumentException with text \"expected non-null MyFormatter\" is thrown", expectedExceptions = { IllegalArgumentException.class }, expectedExceptionsMessageRegExp = "expected non-null MyFormatter")
  public void MyWriterNullFormatter() throws IOException {
	  MyWriter<Track> myW= new MyWriter<Track>(new FileWriter("test.csv"), null);
  }
  @Test(description="test if an instance of MyWriter<Track> can be instantiated successfully")
  public void MyWriterSuccess() throws IOException {
	  MyWriter<Track> myW= new MyWriter<Track>(new FileWriter("test.csv"), new CSVTrackFormatter());
  }

  @Test(description="checks if the put method is called without error when using a valid object and closes the file (adds 2 lines). Reads the first line of the file afterwards to check if put was successfull")
  public void put() throws IOException {
	  MyWriter<Track> myW= new MyWriter<Track>(new FileWriter("test.csv"), new CSVTrackFormatter());
	  	
	  Track newTrack = new Track("Time");
		newTrack.setWriter(new Artist("Roger Waters"));
		newTrack.setPerformer(new Artist("Pink Floyd"));
		newTrack.setDuration(424);
		newTrack.setYear(1973);
		
		myW.put(newTrack);
		myW.put(newTrack);
		myW.close();
		
		FileReader fr = new FileReader("test.csv");
	    BufferedReader br = new BufferedReader(fr);

	    String line1 = br.readLine();
 
	    assertEquals(line1, "Time, Roger Waters, Pink Floyd, 424, 1973");
  }
}

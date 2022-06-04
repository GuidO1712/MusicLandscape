// **************************************************
//		
//       git.rev = 232
//  git.revision = 5758f042c648661b29a7471f428d9556f8ed5e72
//         stage = LW
//
// ***************************************************
package MusicLandscape.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.io.PrintWriter;

import org.testng.annotations.Test;

import MusicLandscape.entities.Artist;
import MusicLandscape.entities.Track;
import MusicLandscape.util.io.MyTrackCSVReader;
/**
 * 
 * @author TeM
 * @version 232
 * @Stage LW
 *
 */
public class MyTrackCSVReaderTest {

  @Test(description="test if the IllegalArgumentException with the custom message is thrown", expectedExceptions = { IllegalArgumentException.class }, expectedExceptionsMessageRegExp = "expected non-null ReaderObject")
  public void MyTrackCSVReaderNull() {
		MyTrackCSVReader test= new MyTrackCSVReader(null);
  }
  
  @Test(description="tests MyTrackCSVReader with existing file  ")
  public void MyTrackCSVReader() throws FileNotFoundException {
	  BufferedReader br;

		br = new BufferedReader(
					new FileReader("mytracks.csv"));
		MyTrackCSVReader test= new MyTrackCSVReader(br);
		
}
  @Test(description="tries to get the first element in the given csv, is depending on a successful constructor" )
  public void getExisting() throws FileNotFoundException {
	  BufferedReader br;

		br = new BufferedReader(
					new FileReader("mytracks.csv"));
		MyTrackCSVReader test= new MyTrackCSVReader(br);
		
		Track newTrack = new Track("Time");
		newTrack.setWriter(new Artist("Roger Waters"));
		newTrack.setPerformer(new Artist("Pink Floyd"));
		newTrack.setDuration(424);
		newTrack.setYear(1973);
		
		Track got=test.get();
		
		
		assertEquals(got.getTitle(), newTrack.getTitle(), "title should be the same");
		assertEquals(got.getWriter().getName(), newTrack.getWriter().getName(), "Writer name should be the same");
		assertEquals(got.getPerformer().getName(), newTrack.getPerformer().getName(), "Performer name should be the same");
		assertEquals(got.getDuration(), newTrack.getDuration(), "duration should be the same");
		assertEquals(got.getYear(), newTrack.getYear(), "year should be the same");

  }
  
  @Test(description="tries to get an element after the last in the given csv, is depending on a successful constructor" )
  public void getNonExisting() throws FileNotFoundException {
	  BufferedReader br;

		br = new BufferedReader(
					new FileReader("mytracks.csv"));
		MyTrackCSVReader test= new MyTrackCSVReader(br);
		Track got=test.get();
		 got=test.get();
		 got=test.get();
		 got=test.get();
		 got=test.get();
		 got=test.get();
		 assertNull(got, "in case there is no line any more, null should be returned");
  }
  @Test(description="tries to get an element after the last in the given csv, is depending on a successful constructor" )
  public void getParsingError() throws FileNotFoundException {

		 
		 BufferedReader br;

		br = new BufferedReader(
					new FileReader("mytracks.csv"));
		MyTrackCSVReader test= new MyTrackCSVReader(br);
		Track got=test.get();
		
	  	System.out.println("awaiting message: Error parsing.");
	  	//output redirection
		PrintStream stdout = System.out;
		ByteArrayOutputStream sysOutContent = new ByteArrayOutputStream();
		 System.setOut(new PrintStream(sysOutContent));
		 try{
				// reset stdin in BinaryStdIn through reflection
				  java.lang.reflect.Field out = System.class.getDeclaredField("out");
				  out.setAccessible(true);
				  //out.set(null, new PrintWriter(sysOutContent));
				}catch(Exception e){
					e.printStackTrace();
				}
		  got=test.get();
		  //compare output
		  String text=",,,,Error parsing.";
		  
		  String toTest=sysOutContent.toString().replaceAll("(\r\n|\r|\n)", "");
		  assertEquals(toTest, text, "text should be\",,,,\" from the line + error message");	  
		  
		  //reset output
		  System.setOut(stdout);
		  try{
				// reset stdin in BinaryStdIn through reflection
				  java.lang.reflect.Field out = System.class.getDeclaredField("out");
				  out.setAccessible(true);
				  //out.set(null, new PrintWriter(stdout));
				}catch(Exception e){
					e.printStackTrace();
				}

		  //check return is null
		 assertNull(got, "in case the line causes any parsing exception (e.g. no values between coma), null should be returned");
		 
  }
  
  }


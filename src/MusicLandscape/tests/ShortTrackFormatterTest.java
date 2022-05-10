// **************************************************
//		
//       git.rev = 234
//  git.revision = fdd4980be270473bdd7e8206afeda65ab6e4c3a4
//         stage = ES05
//
// ***************************************************
package MusicLandscape.tests;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import MusicLandscape.entities.*;
import MusicLandscape.util.*;
import MusicLandscape.util.formatters.*;

/**
 * 
 * @author TeM
 * @version 234
 * @Stage ES05
 *
 */
public class ShortTrackFormatterTest {
	ShortTrackFormatter form;
	@BeforeMethod
	public void init(){
		form= new ShortTrackFormatter();
	}


		@Test(description="checks if ShortTrackFormatter implements correct interface")
		public void checkInterface(){
			assertTrue(new ShortTrackFormatter() instanceof MyFormatter, "the class should implement the interface ");
		}

  @Test(description="checks format of a track with a long title using format()")
  public void formatLongTrack() {
		  //"title","performer","writer","year","duration"
		  Track myTrack= new Track("Ain't No Sunshine");
		  myTrack.setPerformer(new Artist("me first and the gimme gimmes"));
		  myTrack.setWriter(new Artist("super cool writer"));
		  myTrack.setYear(2015);
		  myTrack.setDuration(360);
		  
		  String result= form.format(myTrack);
		  System.out.println(result);
		  assertEquals(result, "Ain't No S (06:00)", "Strings should be equal");

  }
  
  @Test(description="formats a track with a short title using format()")
  public void formatShortTrack() {
		  //"title","performer","writer","year","duration"
		  Track myTrack= new Track("Daylight");
		  myTrack.setPerformer(new Artist("Party Girls"));
		  myTrack.setWriter(new Artist("super cool writer"));
		  myTrack.setYear(2015);
		  myTrack.setDuration(123);
		  
		  String result= form.format(myTrack);
		  System.out.println(result);
		  assertEquals(result, "Daylight   (02:03)", "Strings should be equal");

  }

  @Test(description="checks return value of header()")
  public void header() {
	  assertEquals(form.header(),"Title      (min:sec)", "header() should return another value" );
  }

  @Test(description="checks return value of toString()")
  public void testtoString() {
	  assertEquals(form.toString(),"short format [Title (min:sec)]", "toString() should return another value" );
  }

  @Test(description="checks return value of topSeparator()")
  public void topSeparator() {
	  assertEquals(form.topSeparator(),"--------------------", "topSeparator() should return another value" );
  }
}

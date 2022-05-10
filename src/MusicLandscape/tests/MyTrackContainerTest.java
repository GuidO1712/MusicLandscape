// **************************************************
//		
//       git.rev = 234
//  git.revision = fdd4980be270473bdd7e8206afeda65ab6e4c3a4
//         stage = ES05
//
// ***************************************************
package MusicLandscape.tests;

import static org.testng.Assert.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.testng.annotations.Test;

import MusicLandscape.container.*;
import MusicLandscape.entities.Artist;
import MusicLandscape.entities.Track;
import MusicLandscape.util.comparators.DurationComparator;
import MusicLandscape.util.matcher.TitleMatcher;

/**
 * 
 * @author TeM
 * @version 234
 * @Stage ES05
 *
 */
public class MyTrackContainerTest {

	
	/**
	   * tries to get a private field of the given object
	   * @param myObject Object of which the field should be reached
	   * @param fieldName String which defines the field to be retrieved
	   * @return new Field object if found, fails if NoSuchFieldException
	   */
		private <T> Field getPrivateField(T myObject, String fieldName, boolean isBase){
			Field privateStringField;
			try {
				privateStringField = (isBase)?myObject.getClass().getDeclaredField(fieldName):myObject.getClass().getSuperclass().getDeclaredField(fieldName);
				privateStringField.setAccessible(true);
				return privateStringField;
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				fail("member \""+fieldName+"\" not found");
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	
  @Test(description="checks if initial datatypes are correct")
  public void MyTrackContainer() {
    MyTrackContainer container= new MyTrackContainer();
    //check set
    
    try {
		assertEquals(getPrivateField(container, "tracks", true).get(container) instanceof Set, true, "Collection for tracks should be of type Set" );
	} catch (IllegalArgumentException | IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
    try {
		assertEquals(getPrivateField(container, "selection", true).get(container) instanceof List, true, "Collection for selection should be of type List" );
	} catch (IllegalArgumentException | IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
  }

  @Test(description="checks if initial length of tracks and selection is correct with a List<Track> of 3 tracks")
  public void MyTrackContainerIterableTrack() {

	  MyTrackContainer container= new MyTrackContainer(getIterable());
	  
	    try {
	    	Set tracks=(Set) getPrivateField(container, "tracks", true).get(container);
			assertEquals(tracks.size(), 3, "tracks should hold 3 Tracks" );
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    try {
	    	List selection=(List) getPrivateField(container, "selection", true).get(container);
			assertEquals( selection.size(), 3, "selection should hold 3 Tracks" );
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  }

  @Test(description="checks if initial lenght of tracks and selection is correct with a Track[] of 3 tracks")
  public void MyTrackContainerTrack() {
	  MyTrackContainer container= new MyTrackContainer(getArray());
	  
	    try {
	    	Set tracks=(Set) getPrivateField(container, "tracks", true).get(container);
			assertEquals(tracks.size(), 3, "tracks should hold 3 Tracks" );
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    try {
	    	List selection=(List) getPrivateField(container, "selection", true).get(container);
			assertEquals( selection.size(), 3, "selection should hold 3 Tracks" );
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  }

  @Test(description="test to add the same track twice as well as a null track")
  public void add() {
		 Track t=new Track("Speak To Me");
		  t.setDuration(73);
		  t.setWriter(new Artist("X Writer"));
		  t.setYear(1995);
		  
		  Set<Track> tracks = new HashSet<Track>();
		  
		  
		  MyTrackContainer container= new MyTrackContainer();
		  
		  
		  assertTrue(container.add(t), "track should have been added to tracks");
		  //check length of tracks
		  try {
			  tracks=(Set<Track>) getPrivateField(container, "tracks", true).get(container);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  assertEquals(tracks.size(), 1, "track should have been added to tracks");
		  assertEquals(container.selection().length,0 , "track should not have been added to selection");
		  
		  
		  //add the same a second time
		  assertFalse(container.add(t), "track should not have been added to tracks");
		  //check length of tracks
		  try {
			  tracks=(Set<Track>) getPrivateField(container, "tracks", true).get(container);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  assertEquals(tracks.size(), 1, "track should not have been added to tracks, so size should stay the same");
		  
		  //add null
		  //add the same a second time
		  assertFalse(container.add(null), "null should not have been added to tracks");
		  //check length of tracks
		  try {
			  tracks=(Set<Track>) getPrivateField(container, "tracks", true).get(container);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  assertEquals(tracks.size(), 1, "track should not have been added to tracks, so size should stay the same");
  }

  @Test(description="test to add an array with 3 track to the tracks")
  public void addAll() {
	  Set<Track> tracks = new HashSet<Track>();
	  MyTrackContainer container= new MyTrackContainer();
	  assertEquals(container.addAll(getArray()),3,"3 tracks should have been added to tracks");
	  try {
		  tracks=(Set<Track>) getPrivateField(container, "tracks", true).get(container);
	} catch (IllegalArgumentException | IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  assertEquals(tracks.size(), 3, "3 tracks should  have been added to tracks");
	  assertEquals(container.selection().length,0 , "track should not have been added to selection");
	  
  }

  @Test(description="test filter method using the title matcher with pattern \"t\" or \"Spe\" ")
  public void filter() {
	  MyTrackContainer container= new MyTrackContainer(getIterable());
	  assertEquals(container.filter(new TitleMatcher("T")),1, "one Track should have been removed from selection");
	  assertEquals(container.filter(new TitleMatcher("Spe")),2, "2 Tracks should have been removed from selection");
  }

  @Test(description="check remove() for complete selection (all available Tracks)")
  public void removeAll() {
	  MyTrackContainer container= new MyTrackContainer(getIterable());
	  assertEquals(container.remove(), 3, "all three initial Tracks should have been removed");
  }
  
  @Test(description="check remove() for filtered selection (all available Tracks)", dependsOnMethods = { "filter" })
  public void remove() {
	  MyTrackContainer container= new MyTrackContainer(getIterable());
	  container.filter(new TitleMatcher("T"));
	  assertEquals(container.remove(), 2, "the two Tracks should have been removed");
	  assertEquals(container.selection()[0].getTitle(), "Speak To Me", "only track in list should be Speak To Me");
  }

  @Test(description="check reset() after adding new Tracks", dependsOnMethods = { "add" })
  public void reset() {
	  MyTrackContainer container= new MyTrackContainer(getIterable());
	  container.add(new Track("superduper"));
	  container.reset();
	  assertEquals(container.selection().length, 4, "selection should select all elements of track after reset");
  }

  @Test(description="checks return value of selection() with an empty selection")
  public void selectionInit() {
	  MyTrackContainer container= new MyTrackContainer();
	  Track[] retVal= container.selection();
	  assertEquals(retVal.length==0, true, "length of returned array should be 0");
	  
  }
  
  @Test(description="checks return value of selection() with an empty selection")
  public void selection() {
	  Track[] given=getArray();
	  MyTrackContainer container= new MyTrackContainer(given);
	  Track[] retVal= container.selection();
	  assertEquals(retVal.length==3, true, "length of returned array should be 3");
  }

  @Test(description="check size() after removing Track from selection to make sure it returns size of tracks not of selection", dependsOnMethods = { "remove" })
  public void size() {
	  List<Track> given=getIterable();
	  MyTrackContainer container= new MyTrackContainer(given);
	  container.filter(new TitleMatcher("t"));
	  container.remove();
	  assertEquals(container.size(), 3, "size of tracks should still be 3");
  }

  @Test(description="sorts the selection by a given Comparator (by duration in this case)")
  public void sort() {
	  List<Track> given=getIterable();
	  MyTrackContainer container= new MyTrackContainer(given);
	  
	  container.sort(new DurationComparator(), true);
	  List<Track> retVal= Arrays.asList(container.selection());
	  
	  Collections.sort(given, new DurationComparator());
	  
	  for(int i=0; i<given.size(); i++){
		  assertEquals(retVal.get(i), given.get(i), "the Tracks are not equal, list not sorted as expected");
	  }
	  
	  
  }
  
  private Track[] getArray(){
	  	Track[] ta = new Track[0];
		return getIterable().toArray(ta);
  }
  
  private List<Track> getIterable(){

	  List<Track> mylist= new ArrayList<Track>();
	 Track t=new Track("Speak To Me");
	  t.setDuration(73);
	  t.setWriter(new Artist("X Writer"));
	  t.setYear(1995);
	  mylist.add(t);
	  
	  t=new Track("Time");
	  t.setDuration(1624);
	  t.setWriter(new Artist("Another Writer"));
	  t.setYear(2013);
	  mylist.add(t);
	  

	   t= new Track("The great Gig In The Sky");
	  t.setDuration(288);
	  t.setWriter(new Artist("Pink Floyd"));
	  t.setYear(1973);
	  mylist.add(t);
	  

	  
	  return mylist; 
  }
}

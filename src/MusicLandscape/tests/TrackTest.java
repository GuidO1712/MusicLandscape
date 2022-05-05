// **************************************************
//		
//       git.rev = 234
//  git.revision = fdd4980be270473bdd7e8206afeda65ab6e4c3a4
//         stage = ES02
//
// ***************************************************
package MusicLandscape.tests;
import static org.testng.Assert.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import MusicLandscape.entities.*;

/**
 * 
 * @author TeM
 * @version 234
 * @Stage ES02
 *
 */
public class TrackTest {
	Track toTest;
	
	@BeforeMethod
	private void init(){
		toTest=new Track();
	}
	
  /**
   * tries to get a private field of the given object
   * @param myObject Object of which the field should be reached
   * @param fieldName String which defines the field to be retrieved
   * @return new Field object if found, fails if NoSuchFieldException
   */
	private <T> Field getPrivateField(T myObject, String fieldName){
		Field privateStringField;
		try {
			privateStringField = myObject.getClass().getDeclaredField(fieldName);
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
	
  
  
  
  /**************** ES 02 ***********************/
  @Test
  public void Track() {
    Track toTest= new Track();
    String message="";
	List<Object[]> myList= new ArrayList<Object[]>();
	
	myList.add(new Object[]{"s", "title",null});
	myList.add(new Object[]{"i", "duration",0});
	myList.add(new Object[]{"nn","writer","unknown"});
	myList.add(new Object[]{"nn","performer","unknown"});
	//myList.add(new Object[]{"i", "year",0});
	
	
	for (Object[] li : myList){
		message=String.format("initial value for %s should be %s\n",li[1], li[2]);
		try {
			switch (li[0].toString()) {
			case "i":case "s":
				assertEquals(getPrivateField(toTest, li[1].toString()).get(toTest),li[2],message);
				break;
			case "nn":
				assertNotNull(getPrivateField(toTest, li[1].toString()).get(toTest),message);
				break;
			}
			
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
	}
	
	
	//check year extra cause of wrong init values
	int year;
	message=String.format("initial value for year is allowed with 0 or 1900");
	try {
		year = (int)getPrivateField(toTest, "year").get(toTest);
		assertEquals((year==0||year==1900),true,message);
	} catch (IllegalArgumentException | IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	

    
  }

	  @Test(dataProvider="copyTrack")
	  public void TrackTrack(String title, Artist w, Artist p, int d, int y) {
			
		  	List<Object[]> myList= new ArrayList<Object[]>();
			
			//werte in copyTrack setzen
			Track copyTrack= new Track();
			
			myList.add(new Object[]{"title",title});
			myList.add(new Object[]{"duration",d});
			myList.add(new Object[]{"writer",w});
			myList.add(new Object[]{"performer",p});
			myList.add(new Object[]{"year",y});
			
			for (Object[] li : myList){
				try {
					getPrivateField(copyTrack, li[0].toString()).set(copyTrack,li[1]);
									
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
			
			//copy constructor aufrufen
			toTest=new Track(copyTrack);
			
			//copy check
			assertEquals(toTest.getDuration(), copyTrack.getDuration(),"copy duration failed");
			assertEquals(toTest.getYear(), copyTrack.getYear(),"copy year failed");
			assertEquals(toTest.getTitle(), copyTrack.getTitle(),"copy title failed");
			assertNotEquals(toTest.getWriter(), copyTrack.getWriter(),"copy writer failed - need to copy object");
			assertNotEquals(toTest.getPerformer(), copyTrack.getPerformer(),"copy performer failed - need to copy object");
			
	  }
	  @DataProvider(name = "copyTrack")
	  public static Object[][] copyTrack() {
	      return new Object[][] { 
	    		  //title, writer, performer, dur, year
	    		  {null, mock(Artist.class), mock(Artist.class), 0, 1900},
	    		  {"song", mock(Artist.class), mock(Artist.class), 123, 2015},
	    		  {"short song", mock(Artist.class), mock(Artist.class), 1230, 2000},
	    		  {"very long songtitle", mock(Artist.class), mock(Artist.class), 1230, 1890}
	   		  };
	  }
  @Test(dataProvider="trackTitle")
  public void TrackString(String title) {
	  List<Object[]> myList= new ArrayList<Object[]>();
		
		//werte in copyTrack setzen
		Track copyTrack= new Track();
		
		myList.add(new Object[]{"title",title});
		myList.add(new Object[]{"duration",0});
		myList.add(new Object[]{"writer",mock(Artist.class)});
		myList.add(new Object[]{"performer",mock(Artist.class)});
		myList.add(new Object[]{"year",0});
		
		for (Object[] li : myList){
			try {
				getPrivateField(copyTrack, li[0].toString()).set(copyTrack,li[1]);
								
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}
		
		//copy constructor aufrufen
		toTest=new Track(copyTrack);
		
		//copy check
		assertEquals(toTest.getDuration(), copyTrack.getDuration(),"copy duration failed");
		assertEquals(toTest.getYear(), copyTrack.getYear(),"copy year failed");
		assertEquals(toTest.getTitle(), copyTrack.getTitle(),"copy title failed");
		assertNotEquals(toTest.getWriter(), copyTrack.getWriter(),"copy writer failed - need to copy object");
		assertNotEquals(toTest.getPerformer(), copyTrack.getPerformer(),"copy performer failed - need to copy object");
	
  }
  
  @DataProvider(name = "trackTitle")
  public static Object[][] trackTitle() {
      return new Object[][] { 
    		  //title
    		  {null},
    		  {"song"},
    		  {"short song"},
    		  {"very long songtitle"}
   		  };
  }
  
  
  
  
  
  
    
  
  

 
}

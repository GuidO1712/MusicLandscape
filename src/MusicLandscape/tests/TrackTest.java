// **************************************************
//		
//       git.rev = 234
//  git.revision = fdd4980be270473bdd7e8206afeda65ab6e4c3a4
//         stage = ES01
//
// ***************************************************
package MusicLandscape.tests;

import MusicLandscape.entities.Artist;
import MusicLandscape.entities.Track;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

/**
 * 
 * @author TeM
 * @version 234
 * @Stage ES01
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
	
	/**
	 * checks all initial values 
	 * 	-> 0 for numbers, 
	 * 	null for String, 
	 * 	any non-null value for reference members
	 */
	@Test
	public void checkInit(){
		String message="";
		List<Object[]> myList= new ArrayList<Object[]>();
		
		myList.add(new Object[]{"s", "title",null});
		myList.add(new Object[]{"i", "duration",0});
		myList.add(new Object[]{"nn","writer","not null"});
		myList.add(new Object[]{"nn","performer","not null"});
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

	/**
	 * sets the duration via reflection
	 * gets the result of getDuration
	 * @param in value for the field
	 * @param out ignored for this method
	 */
  @Test(dataProvider="duration")
  public void getDuration(int in, int out) {

	  //reflect private field name
	  Field f= getPrivateField(toTest, "duration");
	  try {
		f.set(toTest, in);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  assertEquals(toTest.getDuration(),in,"value should be "+in);
  }
  /**
	 * sets the duration via setDuration
	 * gets the actual value via reflection
	 * @param in value for the field
	 * @param out value awaited after setting in value
	 */
  @Test(dataProvider="duration")
  public void setDuration(int in, int out) {
	  Field f= getPrivateField(toTest, "duration");
	  toTest.setDuration(in);
	  try {
	    assertEquals(f.get(toTest),out, "value should be "+out);
	  } catch (Exception e) {} 
  }
  
  /**
   * Dataprovider for duration
   * @return all values for duration to be checked
   */
  @DataProvider(name = "duration")
  private static Object[][] duration() {
      return new Object[][] { { -1,0 }, { 0, 0 }, {15,15 }};
  }

  /**
   * checks if getPerformer returns the same object 
   * as set manually before 
   * (initial value is checked in another method)
   */
  @Test
  public void getPerformer() {
	  Artist myPerf= mock(Artist.class);
	  //reflect field

	  Field f= getPrivateField(toTest, "performer");
	  
	  try {
		f.set(toTest, myPerf);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  assertEquals(toTest.getPerformer(), myPerf);
  }
  @Test
  public void setPerformer() {
	  Artist in= mock(Artist.class);
	  Field f= getPrivateField(toTest, "performer");
	  toTest.setPerformer(in);
	  try {
	    assertEquals(f.get(toTest),in);
	  } catch (Exception e) {} 
  }
  @Test
  public void setPerformerNull() {
	  
	  Field f= getPrivateField(toTest, "performer");
	  toTest.setPerformer(null);
	  try {
		Object old= f.get(toTest);
		toTest.setPerformer(null);
		//check value not null
	    assertNotNull(f.get(toTest), "performer should not be null\n");
	    //check value is not a newly created object
	    assertEquals(f.get(toTest), old, "performer should not change to another object\n");
	  } catch (Exception e) {} 
  }
  
  

  
 /**
  * checks if getTitle works properly
  * @param in value reflected to title
  * @param out value awaited from getTitle
  */
  @Test(dataProvider="title")
  public void getTitle(String in, String out) {
	//reflect private field name
	  Field f= getPrivateField(toTest, "title");
	  try {
		f.set(toTest, in);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  assertEquals(toTest.getTitle(),out);
  }
  /**
   * checks if setTitle works properly
   * @param in value to set
   * @param out ignored in this case
   */
  @Test(dataProvider="title")
  public void setTitle(String in, String out) {
	  Field f= getPrivateField(toTest, "title");
	  toTest.setTitle(in);
	  try {
	    assertEquals(f.get(toTest),in);
	  } catch (Exception e) {} 
  }
  @DataProvider(name = "title")
  private static Object[][] title() {
      return new Object[][] { { null,"unknown title" }, { "Let's do party", "Let's do party" }, {"In Memory","In Memory" }};
  }

  @Test
  public void getWriter() {
	  Artist myPerf= mock(Artist.class);
	  //reflect field

	  Field f= getPrivateField(toTest, "writer");
	  
	  try {
		f.set(toTest, myPerf);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  assertEquals(toTest.getWriter(), myPerf);
  }
  @Test
  public void setWriter() {
	  Artist in= mock(Artist.class);
	  Field f= getPrivateField(toTest, "writer");
	  toTest.setWriter(in);
	  try {
	    assertEquals(f.get(toTest),in, "field should be set to handed over object");
	  } catch (Exception e) {} 
  }
  @Test
  public void setWriterNull() {
	  
	  Field f= getPrivateField(toTest, "writer");
	  toTest.setWriter(null);
	  try {
		Object old= f.get(toTest);
		toTest.setWriter(null);
		//check value not null
	    assertNotNull(f.get(toTest), "writer should not be null\n");
	    //check value is not a newly created object
	    assertEquals(f.get(toTest), old, "writer should not change to another object\n");
	  } catch (Exception e) {} 
  }
  
  @Test(dataProvider="year")
  public void getYear(int in, int out) {
	//reflect private field name
	  Field f= getPrivateField(toTest, "year");
	  try {
		f.set(toTest, in);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  assertEquals(toTest.getYear(),in,"value should be "+in);
  }
  
  @Test(dataProvider="year")
  public void setYear(int in, int out) {
	  Field f= getPrivateField(toTest, "year");
	  toTest.setYear(in);
	  
	  if(out==0){
		  int year;
			String message=String.format("value for year is allowed with 0 or 1900 in this case");
			try {
				year = (int)f.get(toTest);
				assertEquals((year==0||year==1900),true,message);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  }
	  else{
		  try {
			    assertEquals(f.get(toTest),out, "value should be "+out);
			  } catch (Exception e) {}   
	  }
	  
  }
  @DataProvider(name = "year")
  private static Object[][] year() {
      return new Object[][] { { 1899,0}, { 1901, 1901 }, 
    		  {3000,0 }, {2999,2999},{2014, 2014}, {-50, 0}};
  }
  
  @Test(dataProvider="writerKnown")
  public void writerIsKnown(Artist mock, String name, boolean out) {
	  Field f= getPrivateField(toTest, "writer");
	  
	  if (mock!=null)
		  when(mock.getName()).thenReturn(name);
	  
	  try {
		  
		  f.set(toTest, mock);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  assertEquals(toTest.writerIsKnown(),out);
	  
	  
  }
  @DataProvider(name = "writerKnown")
  public static Object[][] writerKnown() {
      return new Object[][] { { null,null, false}, { mock(Artist.class),null,  false }, 
    		  {mock(Artist.class),"Kiss",true }};
  }
  
  @Test(dataProvider="getString")
  public void getString(String title, Artist w, String wName, Artist p, String pName, int d, String out) {
		List<Object[]> myList= new ArrayList<Object[]>();
		
		myList.add(new Object[]{"title",title});
		myList.add(new Object[]{"duration",d});
		myList.add(new Object[]{"writer",w});
		myList.add(new Object[]{"performer",p});
		
		if (w!=null)
			  when(w.getName()).thenReturn(wName);
		if (p!=null)
			  when(p.getName()).thenReturn(pName);
		
		for (Object[] li : myList){
			try {
				getPrivateField(toTest, li[0].toString()).set(toTest,li[1]);
								
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}
		assertEquals(toTest.getString(), out);
  }
  

  
  
  @DataProvider(name = "getString")
  private static Object[][] string() {
      return new Object[][] { 
    		  //title, writer, name, performer, name, dur, ret
    		  {null, null, null, null, null, 0, "   unknown by    unknown performed by    unknown (00:00)"},
    		  {"song", mock(Artist.class), null, mock(Artist.class), null, 123, "      song by    unknown performed by    unknown (02:03)"},
    		  {"short song", mock(Artist.class), "writer", mock(Artist.class), "performer", 1230, "short song by     writer performed by  performer (20:30)"},
    		  {"very long songtitle", mock(Artist.class), "long writer", mock(Artist.class), "long performer", 1230, "very long  by long write performed by long perfo (20:30)"}
   		  };
  }
  
  
  
  
  
  
  
  
    
  
  

 
}

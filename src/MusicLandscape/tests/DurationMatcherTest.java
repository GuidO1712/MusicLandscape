// **************************************************
//		
//       git.rev = 234
//  git.revision = fdd4980be270473bdd7e8206afeda65ab6e4c3a4
//         stage = ES05
//
// ***************************************************
package MusicLandscape.tests;
import java.lang.reflect.Field;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import MusicLandscape.entities.*;
import MusicLandscape.util.*;
import MusicLandscape.util.matcher.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

/**
 * 
 * 
 * @author TeM
 * @version 234
 * @Stage ES05
 *
 */
public class DurationMatcherTest {
	DurationMatcher mat;
	
	@BeforeMethod
	private void init(){
		mat=new DurationMatcher();
	}

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
	
	
  @Test(description="tests the cosntructor which takes a pattern as String with value \"2 5\"")
  public void DurationMatcherString() {
    mat= new DurationMatcher("2 5");
    try {
		assertEquals(getPrivateField(mat, "lower", true).get(mat), 2,"initial value of lower boundary should be 0");
	} catch (IllegalArgumentException | IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  try {
		assertEquals(getPrivateField(mat, "upper", true).get(mat),5,"initial value of upper boundary should be 5");
	} catch (IllegalArgumentException | IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }

  @Test(description="tests the constructor of DurationMatcher without arguments")
  public void DurationMatcher() {
	  //check inheritance
	  assertTrue(mat instanceof MyMatcher, "DurationMatcher should be derived from MyMatcher<T>");
	  //check initial lower/ upper boundaries
	  try {
		assertEquals(getPrivateField(mat, "lower", true).get(mat), 0,"initial value of lower boundary should be 0");
	} catch (IllegalArgumentException | IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  try {
		assertEquals(getPrivateField(mat, "upper", true).get(mat), Integer.MAX_VALUE,"initial value of upper boundary should be"+Integer.MAX_VALUE);
	} catch (IllegalArgumentException | IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
  }

  @Test(description="test the return value of getPattern()")
  public void getPattern() {
    assertEquals(mat.getPattern(), "0 "+Integer.MAX_VALUE, "string should be <lower> <upper>");
  }

  @Test(dataProvider="matchpatterns", description="requires setPattern works! checks if matching is done correctly. first argument: druations of track, second: pattern to match, third: awaited result")
  public void matches(int trackDuration, String pattern, boolean result) {
    Track t= new Track();
    t.setDuration(trackDuration);
    
    mat.setPattern(pattern);
    
    assertEquals(mat.matches(t), result, "given duration should "+((result)?"":"not")+" match the given pattern");
  }

  @Test(dataProvider="patterns", description="sets the pattern to the given String, variables u and l are the awaited values of the Matcher after using setPattern(in)" )
  public void setPattern(String in, int l, int u) {
	  mat.setPattern(in);
	  try {
		assertEquals(getPrivateField(mat, "lower", true).get(mat), l,"initial value of lower boundary should be"+l);
	} catch (IllegalArgumentException | IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  try {
		assertEquals(getPrivateField(mat, "upper", true).get(mat), u,"initial value of upper boundary should be"+u);
	} catch (IllegalArgumentException | IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
  }
  
  
  @DataProvider(name = "matchpatterns")
  private static Object[][] matchpatterns() {
      return new Object[][] { 
    		  //trackduration, pattern, result
    		  {0, "0 0",true},
    		  {120, "0 0",false}, 
    		  {120, "0 300", true }, 
    		  };
      }
  
  
  @DataProvider(name = "patterns")
  private static Object[][] duration() {
      return new Object[][] { 
    		  {"1 200", 1, 200},
    		  { "incorr string",0,Integer.MAX_VALUE }, 
    		  { "15 2", 15,Integer.MAX_VALUE }, 
    		  {"15 15",15,15 }
    		  };
      }
  

  @Test
  public void testtoString() {
    assertEquals(mat.toString(), "duration in range (0 2147483647)", "return value is not as specified");
  }
}

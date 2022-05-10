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

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import MusicLandscape.entities.Track;
import MusicLandscape.util.matcher.TitleMatcher;


/**
 * 
 * @author TeM
 * @version 234
 * @Stage ES05
 *
 */
public class TitleMatcherTest {
	TitleMatcher mat;
	
	@BeforeMethod
	private void init(){
		mat=new TitleMatcher("my");
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
  @Test(description="checks initial value of TitleMatcher with constructor TitleMatcher (\"my\")")
  public void TitleMatcher() {
	  try {
		assertEquals(getPrivateField(mat, "pattern", true).get(mat), "my", "initial pattern value should be \"my\"");
	} catch (IllegalArgumentException | IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }

  @Test(description="checks return value of getPattern()")
  public void getPattern() {
    assertEquals(mat.getPattern(), "my", "return value not as expected");
  }

  @Test(dataProvider="matchpatterns", description="requires setPattern is working! checks match results, arguments: 1.=tracktitle, 2.=pattern, 3.=expected result")
  public void matches(String title, String pattern, boolean result) {
    Track t= new Track(title);
    mat.setPattern(pattern);
    assertEquals(mat.matches(t), result, "given title does"+((result)?"":"not")+" start with the given pattern");
  }

  @DataProvider(name = "matchpatterns")
  private static Object[][] matchpatterns() {
      return new Object[][] { 
    		  //title, pattern, result
    		  {"my oh my", "my",true},
    		  {"music is my life", "my",false}, 
    		  {"any title", "", true }, 
    		  };
      }
  
  @Test(description="tries to set a null pattern")
  public void setPatternNull() {
    mat.setPattern(null);
    try {
		assertEquals(getPrivateField(mat, "pattern", true).get(mat), "my", "pattern shouldn't change as null is not valid");
	} catch (IllegalArgumentException | IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  
  @Test(description="tries to set a valid pattern - music")
  public void setPatternAny() {
	  mat.setPattern("music");
	    try {
			assertEquals(getPrivateField(mat, "pattern", true).get(mat), "music", "pattern should have changed");
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  }

  @Test
  public void testtoString() {
	  assertEquals(mat.toString(), "title starts with (my)", "return value not as expected");
  }
}

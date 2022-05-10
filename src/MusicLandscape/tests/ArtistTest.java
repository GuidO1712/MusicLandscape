// **************************************************
// 
//       git.rev = 234
//  git.revision = fdd4980be270473bdd7e8206afeda65ab6e4c3a4
//         stage = ES05
//
// ***************************************************



package MusicLandscape.tests;

import java.lang.reflect.Field;

import org.mockito.internal.util.reflection.Whitebox;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import MusicLandscape.entities.*;

import static org.mockito.Mockito.mock;
import static org.testng.Assert.*;

/**
 * 
 * @author TeM
 * @version 234
 * @Stage ES05
 *
 */
public class ArtistTest {

	
	
	
	
	
	
	
	

		
	 
	

	
	/**************** ES 05 ***********************/
	@Test(description="checks if Artist implements interface")
	public void checkInterface(){
		assertTrue(new Artist() instanceof Comparable, "the class should implement the interface ");
	}
	
	
	  @Test(dataProvider="name", description="checks if comparison is done lexicographically")
	  public void compareTo(String t1, String t2, int res) {

		  Artist left= new Artist(t1);
		  Artist right= new Artist(t2);
		  int result = left.compareTo(right);
		  
		  if(res>0){
			  assertTrue(result>0, "first should be bigger than second");  
		  }else if(res==0){
			  assertTrue(result==0, "both should be equal");
		  }else if(res<0){
			  assertTrue(result<0, "first should be smaller than second");
		  }
	  }
	  
	  @DataProvider(name = "name")
		private static Object[][] title() {
			return new Object[][] {
					{ "same", "same", 0}, 
					{"same", "other", 4}, 
					{"other", "same", -4} 
					};
		}
	
	
}

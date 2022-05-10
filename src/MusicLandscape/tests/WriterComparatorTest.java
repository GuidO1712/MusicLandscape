// **************************************************
//		
//       git.rev = 234
//  git.revision = fdd4980be270473bdd7e8206afeda65ab6e4c3a4
//         stage = ES05
//
// ***************************************************
package MusicLandscape.tests;
import java.util.Comparator;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import MusicLandscape.entities.*;
import MusicLandscape.util.comparators.*;


/***
 * 
 * @author TeM
 * @version 234
 * @Stage ES05
 *
 */
public class WriterComparatorTest {
	
	@Test(description="checks if the interface java.util.Comparator has been used")
	public void checkType(){
		 WriterComparator com= new  WriterComparator();
		assertTrue((com instanceof Comparator), "you should implement generic interface Comparator<Track>");
		
	}
	  @Test(dataProvider="name")
	  public void compare(String t1, String t2, int res) {

		  WriterComparator com= new WriterComparator();
		  
		  Track left= new Track();
		  left.setWriter(new Artist(t1));
		  Track right=new Track();
		  right.setWriter(new Artist(t2));
		  
		  int result= com.compare(left, right);
		  
		  if(res>0){
			  assertTrue(result>0, "first should be bigger than second");  
		  }else if(res==0){
			  assertTrue(result==0, "both should be equal");
		  }else if(res<0){
			  assertTrue(result<0, "first should be smaller than second");
		  }
	  }

  @Test(description="checks return value of toString")
  public void testtoString() {
	  WriterComparator com= new WriterComparator();
	  assertEquals(com.toString(),"by writer", "toString() should return another value" );
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

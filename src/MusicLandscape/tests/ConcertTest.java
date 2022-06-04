// **************************************************
//		
//       git.rev = 232
//  git.revision = 5758f042c648661b29a7471f428d9556f8ed5e72
//         stage = LW
//
// ***************************************************
package MusicLandscape.tests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import MusicLandscape.Date;
import MusicLandscape.Venue;
import MusicLandscape.entities.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

/**
 * 
 * @author TeM
 * @version 232
 * @Stage LW
 *
 */
public class ConcertTest {
	Concert toTest;

	Track[]baseList; 
	

	@BeforeMethod
	private void init() {
		toTest = new Concert();
		baseList=baseTestList();
	}

	private Track[] baseTestList(){
		Track[] basList=new Track[10];
		for (int i=0; i<basList.length; i++){
			basList[i]= new Track();
			basList[i].setDuration(i*100);
				
		}
		return basList;
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
		  private boolean equals(Track a, Track b) {
			  boolean isEqual=false;
			    //check for self-comparison
			   // if ( a == b ) return true;
			    
			    //check each field
				isEqual=(a.getTitle().equals(b.getTitle())) &&
						(a.getDuration()==b.getDuration()) &&
					    (!a.getWriter().equals(b.getWriter())) && //writer is a deep copy
					    (!a.getPerformer().equals(b.getPerformer())) && //performer is a deep copy
					    (a.getYear()==b.getYear()) ;
			    return isEqual;
		  }
			 private void setbaseList(){
				 try {
					  Field f = getPrivateField(toTest, "setList",true);
					  f.set(toTest, baseList);
					  f = getPrivateField(toTest, "nextIdx",true);
					  f.set(toTest, baseList.length);
					} catch (IllegalArgumentException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			 }
				

  
}



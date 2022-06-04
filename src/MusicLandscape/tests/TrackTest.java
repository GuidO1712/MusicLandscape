// **************************************************
//		
//       git.rev = 232
//  git.revision = 5758f042c648661b29a7471f428d9556f8ed5e72
//         stage = LW
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
 * @version 232
 * @Stage LW
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
	
  
  
  
  
  
  
  
  
    
  
  

 
}

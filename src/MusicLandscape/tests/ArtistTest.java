// **************************************************
// 
//       git.rev = 234
//  git.revision = fdd4980be270473bdd7e8206afeda65ab6e4c3a4
//         stage = ES01
//
// ***************************************************



package MusicLandscape.tests;

import MusicLandscape.entities.Artist;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Field;

import static org.testng.Assert.*;
import static org.testng.Assert.assertEquals;

/**
 * 
 * @author TeM
 * @version 234
 * @Stage ES01
 *
 */
public class ArtistTest {

	
	
	@Test
	/**
	 * checks if initial value of name is null
	 */
	public void checkInit() {
		Artist toTest = new Artist();
		// reflect private field name

		try {
			Field privateStringField = Artist.class.getDeclaredField("name");
			privateStringField.setAccessible(true);
			assertEquals(privateStringField.get(toTest), null,
					"initial value should be null");
		} catch (Exception e) {
		}
	}
	
	
	
	
	
	@Test(dataProvider = "names")
	public void getName(String in, String out) {
		Artist toTest = new Artist();
		// reflect private field name

		try {
			Field privateStringField = Artist.class.getDeclaredField("name");
			privateStringField.setAccessible(true);
			privateStringField.set(toTest, in);
		} catch (Exception e) {
		}

		assertEquals(toTest.getName(), in);
	}
	
	@Test(dataProvider = "names")
	public void setName(String in, String out) {
		Artist toTest = new Artist();
		toTest.setName(in);
		try {
			Field privateStringField = Artist.class.getDeclaredField("name");
			privateStringField.setAccessible(true);

			assertEquals(privateStringField.get(toTest), out);
		} catch (Exception e) {
		}
	}
	@DataProvider(name = "names")
	private static Object[][] names() {
		return new Object[][] { { null, null }, { "kiss", "kiss" },
				{ "", null }, { "  ", null },
				{ "Jon Bon Jovi", "Jon Bon Jovi" } };
	}
	
	
	

		
	 
	

	
}

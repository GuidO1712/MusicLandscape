// **************************************************
//		
//       git.rev = 234
//  git.revision = fdd4980be270473bdd7e8206afeda65ab6e4c3a4
//         stage = ES02
//
// ***************************************************
package MusicLandscape.tests;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNotSame;
import static org.testng.Assert.fail;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import MusicLandscape.Date;
import MusicLandscape.Venue;
import MusicLandscape.entities.*;


/**
 * 
 * @author TeM
 * @version 234
 * @Stage ES02
 *
 */
public class EventTest {
	Event toTest;

	
	@BeforeMethod
	private void init() {
		toTest = new Event();
	}

	/**
	 * tries to get a private field of the given object
	 * 
	 * @param myObject
	 *            Object of which the field should be reached
	 * @param fieldName
	 *            String which defines the field to be retrieved
	 * @return new Field object if found, fails if NoSuchFieldException
	 */
	private <T> Field getPrivateField(T myObject, String fieldName) {
		Field privateStringField;
		try {
			privateStringField = myObject.getClass()
					.getDeclaredField(fieldName);
			privateStringField.setAccessible(true);
			return privateStringField;
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			fail("member \"" + fieldName + "\" not found");
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Test
	public void Event() {
		// artist !=null, venue=null, date null, attendees 0, description ""
		String message = "";
		List<Object[]> myList = new ArrayList<Object[]>();

		myList.add(new Object[] { "nn", "artist", "not null" });
		myList.add(new Object[] { "s", "venue", null });
		myList.add(new Object[] { "s", "date", null });
		myList.add(new Object[] { "i", "attendees", 0 });
		myList.add(new Object[] { "s", "description", "" });

		for (Object[] li : myList) {
			message = String.format("initial value for %s should be %s\n",
					li[1], li[2]);
			try {
				switch (li[0].toString()) {
				case "i":
				case "s":
					assertEquals(
							getPrivateField(toTest, li[1].toString()).get(
									toTest), li[2], message);
					break;
				case "nn":
					assertNotNull(getPrivateField(toTest, li[1].toString())
							.get(toTest), message);
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

	}

	@Test
	public void getArtist() {
		// reflect private field name
		Field f = getPrivateField(toTest, "artist");

		Artist in = mock(Artist.class);
		try {
			f.set(toTest, in);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(toTest.getArtist(), in, "value should be " + in);
	}

	@Test(dataProvider="artist")
	public void setArtist(Artist in, Artist out) {

		// set null
		toTest.setArtist(in);
		// reflect private field name
		Field f = getPrivateField(toTest, "artist");
		try {
			if (in == null) {
				assertNotNull(f.get(toTest));
			} else {
				assertEquals(f.get(toTest), in,
						"field should be set to handed over object");
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@DataProvider(name = "artist")
	private static Object[][] artist() {
		Artist d = mock(Artist.class);
		return new Object[][] { { null, null }, { d, d } };
	}

	@Test(dataProvider = "attendees")
	public void getAttendees(int in, int out) {

		Field f = getPrivateField(toTest, "attendees");

		try {
			f.set(toTest, in);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(toTest.getAttendees(), in, "value should be " + in);
	}

	@Test(dataProvider = "attendees")
	public void setAttendees(int in, int out) {

		toTest.setAttendees(in);
		// reflect private field name
		Field f = getPrivateField(toTest, "attendees");
		try {
			assertEquals(f.get(toTest), out, "field should be set to" + out);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@DataProvider(name = "attendees")
	private static Object[][] attend() {
		return new Object[][] { { 0, 0 }, { 15, 15 }, { -15, 0 } };
	}

	@Test(dataProvider = "date")
	public void getDate(Date in, Date out) {
		Field f = getPrivateField(toTest, "date");

		try {
			f.set(toTest, in);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (in == null) {
			assertEquals(toTest.getDate(), in, "value should be " + in);
		} else {
			assertNotSame(toTest.getDate(), in, "value should be a new object");
		}

	}

	@Test(dataProvider = "date")
	public void setDate(Date in, Date out) {

		// set any artist -> no copy!
		toTest.setDate(in);
		// reflect private field name
		Field f = getPrivateField(toTest, "date");
		try {
			if (in == null) {
				assertEquals(f.get(toTest), in, "field should be set to null");
			} else {
				assertNotSame(f.get(toTest), in, "field should be a new object");
			}

		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@DataProvider(name = "date")
	private static Object[][] date() {
		Date d = mock(Date.class);
		return new Object[][] { { null, null }, { d, d } };
	}

	@Test(dataProvider = "description")
	public void getDescription(String in, String out) {

		Field f = getPrivateField(toTest, "description");

		try {
			f.set(toTest, in);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(toTest.getDescription(), in, "value should be " + in);
	}

	@Test(dataProvider = "description")
	public void setDescription(String in, String out) {
		toTest.setDescription(in);
		// reflect private field name
		Field f = getPrivateField(toTest, "description");
		try {
			assertEquals(f.get(toTest), out, "field should be set to" + out);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@DataProvider(name = "description")
	private static Object[][] description() {
		Date d = mock(Date.class);
		return new Object[][] { { null, "" }, { "", "" },
				{ "test description", "test description" } };
	}

	@Test(dataProvider = "venue")
	public void getVenue(Venue in, Venue out) {
		Field f = getPrivateField(toTest, "venue");

		try {
			f.set(toTest, in);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(toTest.getVenue(), in, "value should be " + out);

	}

	@Test(dataProvider = "venue")
	public void setVenue(Venue in, Venue out) {
		toTest.setVenue(in);
		// reflect private field name
		Field f = getPrivateField(toTest, "venue");
		try {

			assertEquals(f.get(toTest), out, "field should be set to " + out);

		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@DataProvider(name = "venue")
	private static Object[][] venue() {
		Venue d = mock(Venue.class);
		return new Object[][] { { null, null }, { d, d } };
	}

	

	
}

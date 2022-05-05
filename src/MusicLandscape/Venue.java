// **************************************************
//		
//       git.rev = 234
//  git.revision = fdd4980be270473bdd7e8206afeda65ab6e4c3a4
//         stage = ES02
//
// ***************************************************
package MusicLandscape;

/**
 * 
 * @author TeM
 * @version 234
 * @Stage ES02
 * @ProgrammingProblem.Category simple entity classes
 * @ProgrammingProblem.Introduced ExerciseSheet02 (provided)
 */
public class Venue {
	/**
	 * 
	 * the name of this venue
	 */
	private String name;
	/**
	 * the capacity of this venue
	 * 
	 * the capacity is the (non-negative) maximum number of attendees allowed
	 * for this venue.
	 * 
	 */
	private int capacity;

	/**
	 * get the capacity of this venue
	 * 
	 * @return the capacity
	 * 
	 */
	public int getCapacity() {
		return capacity;
	}
	/**
	 * creates a default venue
	 * 
	 *  a default venue has the name "unknown" (without quotes) and capacity 0.
	 *  
	 */
	public Venue(){
		
	}

	/**
	 * set the capacity of this venue
	 * 
	 * the capacity must be a non-negative number. all other values are ignored,
	 * the object remains unchanged.
	 * 
	 * @param capacity
	 *            to set
	 * 
	 */
	public void setCapacity(int capacity) {
		if (capacity < 0)
			return;
		this.capacity = capacity;
	}

	/**
	 * get the name of this venue
	 * 
	 * @return the name
	 */

	public String getName() {
		return name;
	}

	/**
	 * set the name of this venue
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**************** ES 03 ***********************/

	

}

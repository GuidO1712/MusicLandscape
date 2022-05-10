// **************************************************
//		
//       git.rev = 234
//  git.revision = fdd4980be270473bdd7e8206afeda65ab6e4c3a4
//         stage = ES05
//
// ***************************************************
package MusicLandscape.util;

/**
 * This class represents the concept of (tabular) formatting of objects.<br>
 * 
 * Implementing classes provide methods to create a formatted table of records,
 * including column headers and a top separator (between headers and data) with
 * correct column width.<br>
 * 
 * It is intended to be used with lists (or arrays) of objects that need to be
 * displayed in tabular format. One would typically use a formatter of this kind
 * to print the header and separator (if desired) followed by the individual
 * records line-by-line.
 * 
 * 
 * @author TeM, JS
 * @version 234
 * @Stage ES05
 * @ProgrammingProblem.Category generic interface
 * @ProgrammingProblem.Introduced ExerciseSheet05
 * 
 */
public interface MyFormatter<T> {

	/**
	 * Get the headers for the table as a single string.<br>
	 * 
	 * Contains the names for all columns separated by the correct number of
	 * blanks.
	 * 
	 * 
	 * @return the header string.
	 */
	public abstract String header();

	/**
	 * Creates a String representation for an object..
	 * 
	 * @param t
	 *            the object to be formatted
	 * @return the formatted representing the object
	 */
	public abstract String format(T t);

	/**
	 * A line of text to be used between header and data.
	 * 
	 * 
	 * @return the separator.
	 */
	public abstract String topSeparator();

}

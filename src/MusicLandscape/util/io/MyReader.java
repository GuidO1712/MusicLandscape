// **************************************************
//		
//       git.rev = ${gitrev}
//  git.revision = ${gitrevision}
//         stage = ${stage}
//
// ***************************************************
package MusicLandscape.util.io;

import java.io.BufferedReader;

/**
 * Abstract class for creating Objects from data stored in files (or more
 * specifically a BufferedReaders).<br>
 * The only method that subclasses must implement is T get() which returns an
 * object representing the next record in the underlying stream.<br>
 * Subclasses are used to read or load data stored in textfiles (like *.csv or
 * similiar).
 * 
 * 
 * 
 * @author TeM, JS
 * @version ${gitrev}
 * @Stage ${stage}
 *
 * @param <T>
 *            the type of the objects that can be loaded
 * 
 * 
 * @ProgrammingProblem.Category generic abstract class
 * @ProgrammingProblem.Category importing data from file
 * 
 * @ProgrammingProblem.Introduced ExerciseSheet06
 * 
 */
public abstract class MyReader<T> {

	/**
	 * the underlying stream from which data is read
	 */
	protected BufferedReader in;

	/**
	 * constructs a MyReader from a Buffered Reader.<br>
	 * the underlying stream cannot be null. In case a null object is passed to
	 * this constructor an IllegalArgumentException including 
	 * a custom message "expected non-null ReaderObject" is thrown.
	 * 
	 * @param in
	 *            the underlying stream
	 * 
	 * 
	 * @ProgrammingProblem.Aspect throwing standard exceptions
	 */
	public MyReader(BufferedReader in) {
		if (in == null)
			throw new IllegalArgumentException("expected non-null ReaderObject");
		this.in = in;
	}

	/**
	 * 
	 * Gets the next object from the underlying stream.<br>
	 * 
	 * Reads the next record and creates an object with the respective values
	 * set. This method handles ALL IOExceptions that might occur and returns
	 * null objects in such situations.
	 * 
	 * @return the next record as an object with all values set
	 */
	public abstract T get();
}

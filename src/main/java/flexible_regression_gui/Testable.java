package src.main.java.flexible_regression_gui;

import java.lang.reflect.*;
import java.util.List;

/**
 * Interface for working with the Regression Test Case Builder.
 * Any class that wants to be testable with it must implement this interface.
 * 
 * Any class that implements this interface must be a Java Bean.
 * (Read about them here: https://www.javatpoint.com/java-bean)
 * 
 * Any class that implements this interface must work with reflection.
 * 
 * @author Scott Straguzzi
 * @version 2020.11.17
 */
public interface Testable extends Cloneable {
	/**
	 * Method used for writing XML test sequences. Meant to be called to validate a state.
	 * 
	 * @return true if the state is valid, false otherwise
	 */
	public boolean validate();
	
	/**
	 * Method used for gaining access to the class's other methods for testing purposes. Meant to force implementation of reflection
	 * 
	 * @return a plain old java array of methods
	 */
	public List<Method> listMethods();
	
	/**
	 * Must implement clone, easily done in one line:
	 * 
	 * super.clone();
	 * 
	 * @return a copy of an object where changes in one will not reflect in the second
	 */
	public Object clone();

}

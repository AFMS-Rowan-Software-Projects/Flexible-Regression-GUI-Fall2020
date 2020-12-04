package test_case_builder;

import java.util.*; // List and related classes

import java.beans.*; // XML encoder and decoder
import java.io.*;    // Reads / writers for above
import java.lang.reflect.*;


/**
 * Controller for MVC project
 * 
 * Uses reflection to find / use methods of classes that implement Testable.
 * 
 * Any class that implements testable can be used with this.
 * 
 * The basic workflow with TestSequence is to list of all of the Testable object's methods, and 
 * 
 * TODO encoder stuff. This has the imports for it, I'm just not sure where to put it between this class and a theoretical viewer class.
 * Everything implements the JavaBean API though so an encoder method would be like 10 lines, and most of them would be a try/catch
 * 
 * @author Scott Straguzzi
 *
 */
public class TestSequence {
	private List<TestStep> testSequence; // A list of TestSteps, to be executed in order
	private Testable baseCase; // I'd somehow like to make this final, but serialization / deserialization may mess with that. Test that out later.
	// private Testable testCase; // A clone of baseCase that will be modified when the testSequence is executed.
	
	
	/**
	 * Blank constructor so TestSequence adheres to the Java Bean API. Do not call this method, everything is initialized to null
	 */
	public TestSequence() {
		testSequence = null;
		baseCase = null;
		// testCase = null;
	}
	
	/**
	 * Standard constructor. The testObj is the object that the test sequence applies to. It should come in a state that is testable (i.e already initialized).
	 * 
	 * 
	 * @param testObj
	 */
	public TestSequence(Testable testObj) {
		testSequence = new LinkedList<TestStep>();
		baseCase = testObj;
		// testCase = baseCase; // May need to look into a Clone() method or something similar so that changes to testCase are not reflected in base case
	}
	
	/**
	 * Adds a method object to the test sequence
	 * 
	 * These steps are then executed in the order they are added to the testCase when TestSequence.execute() is called
	 * 
	 * @param step is the name of a method that is to be execueted by the testCase
	 * @param parameters is an array of the parameters. The type is object for so validation both when adding it to the test sequence and when eventually executing it is a must!
	 */
	public void addStep(String method, Class[] paramTypes, Object[] parameters) {
		Method calls = null;
		try {
			Class testClass = baseCase.getClass();
			calls = testClass.getDeclaredMethod(method, paramTypes);
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		testSequence.add(new TestStep(baseCase, calls, parameters));
	}
	
	/**
	 * Runs all the test steps on testCase and returns it's new state.
	 * 
	 * User can add several steps to the test sequence and returns them all
	 * 
	 * Runs the test sequence on a clone of the original object so the original is always stored.
	 * 
	 * @return a clone of the baseCase with all the methods in the test sequence executed on it.
	 */
	public Testable execute() {
		Testable testCase = (Testable) baseCase.clone();
		for(TestStep step : testSequence) { // Tries to execute each step in the testSequence on testObject
			try {
				step.getMethod().invoke(testCase, step.getParams());
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return testCase;
	}
	
	/**
	 * @return list of methods as a String, one method per line
	 * 
	 * First token in a line is the return type
	 * Second token is the parameter name, ending in a "("
	 * After that, the parameter types (if there are any parameters) are listed, separated by commas
	 * A line is always closed with a ")"
	 */
	public String showMethods() {
		String output = "";
		
		for(Method method : baseCase.listMethods()) {
			output = output + method.getReturnType() + " " + method.getName() + "("; // public type method(
			
			Class[] parameters = method.getParameterTypes();
			if(parameters.length > 0) {
				output = output + parameters[0].getName();
				for(int i = 1; i < parameters.length; i++) {
					output = output + ", " + parameters[i].getName();
				}
			}
			
			output = output + ")\n"; 
		}
		
		output = output.trim();
		
		return output;
	}
	
	// GETTERS AND SETTERS TO ADHERE TO JAVA BEAN API
	
	public void setTestSequence(List<TestStep> testSequence) {
		this.testSequence = testSequence;
	}
	
	public List<TestStep> getTestSequence() {
		return testSequence;
	}
	
	public Testable getBaseCase() {
		return baseCase;
	}

	public void setBaseCase(Testable baseCase) {
		this.baseCase = baseCase;
	}

//	public Testable getTestCase() {
//		return testCase;
//	}
//
//	public void setTestCase(Testable testCase) {
//		this.testCase = testCase;
//	}
	
	
}

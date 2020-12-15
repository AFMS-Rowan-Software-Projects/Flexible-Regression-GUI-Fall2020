package src.main.java.flexible_regression_gui;

import java.util.*; // List and related classes

import java.beans.*; // XML encoder and decoder
import java.io.*;    // Reads / writers for above
import java.lang.reflect.*;
import java.nio.file.*;


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
 * Everything implements the JavaBean API though so an encoder method would be like 10 lines, and most of them would be a try/catch.
 * 
 * @author Scott Straguzzi
 *
 */
public class TestSequence {
	private List<TestStep> testSequence; // A list of TestSteps, to be executed in order
	private Testable baseCase; // I'd somehow like to make this final, but serialization / deserialization may mess with that. Test that out later.
	
	private String fileName; // File path used for serialization / deserialization
	
	
	/**
	 * Blank constructor so TestSequence adheres to the Java Bean API. Do not call this method, everything is initialized to null
	 */
	public TestSequence() {
		testSequence = null;
		baseCase = null;
		fileName = null;
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
		
		fileName =  baseCase.getClass().getName() + ".xml"; // SHOULD be the src/main/resources folder in the maven project...
										 // ALSO need to figure out how to make this work on Linux since that's a windows file path...
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
	 * No parameter removes the last step added. Does nothing if there are no steps stored
	 */
	public void removeStep() {
		if(testSequence.size() > 0) {
			testSequence.remove(testSequence.size()-1);
		}
	}
	
	/**
	 * Removes the step at the specified index
	 * @param step to be removed's index
	 * 
	 * Maybe need some sort of showSteps() method to make it easier to know what step to remove?
	 */
	public void removeStep(int step) {
		if(step < testSequence.size() && step > -1) {
			testSequence.remove(step);
		}
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
	
	// ENCODING AND DECODING METHODS
	/**
	 * Encodes to an XML File. The filename can be called by the static method decode() to be retrieved and edited some more.
	 * 
	 * Filename defaults to the Class Name of the Testable object + .xml (e.g. MultTestStep.xml)
	 * 
	 * Maybe add another that uses a different filename as a parameter? Or just do setFileName before calling this /shrug
	 */
	public void encode() {
		try {
			FileOutputStream output = new FileOutputStream(fileName);
			XMLEncoder encoder = new XMLEncoder(output);
			encoder.writeObject(this);
		    encoder.close();
		} catch (FileNotFoundException e){
			System.err.println("Error writing to file " + fileName);
			e.printStackTrace();
		}
	}
	
	/**
	 * Imports an XML file of a test sequence. Static method so it can be used to create TestSequences from just a file
	 * @param fileName typically in the form Testable.getClass.getName() + ".xml" (e.g. MultTestStep.xml)
	 * @return a new TestSequence object that can be used for adding steps or running
	 */
	public static TestSequence decode(String fileName) {
		try {
			FileInputStream input = new FileInputStream(fileName);
			XMLDecoder decoder = new XMLDecoder(input);
			
			TestSequence readSequence = (TestSequence) decoder.readObject();
			decoder.close();
			
			return readSequence;
		} catch (FileNotFoundException e){
			System.err.println("Error writing to file " + fileName);
			e.printStackTrace();
			return null;
		}
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
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getFileName() {
		return fileName;
	}
}

package test_case_builder;

import java.lang.reflect.*;
import java.util.List;
import java.util.LinkedList;

/**
 * A testing class that uses the TestStep interface to work with the TestSequence controller
 * Implements the JavaBean API
 * 
 * Goal of this class is to be able to find and add its methods to TestSequence
 * 
 * @author Scott Straguzzi
 */
public class MultTestStep implements Testable {
	private int left;
	private int right;
	private int result;
	
	/**
	 * Blank constructor to work with XMLEncoder/XMLDecoder as a javabean
	 */
	public MultTestStep() {
		// Intentionally left blank for java bean
	}
	
	/**
	 * Standard constructor for use
	 * 
	 * @param left input of the multiplication
	 * @param right input of the multiplication
	 * @param result expected from the operation left * right
	 */
	public MultTestStep(int left, int right, int result) {
		this.left = left;
		this.right = right;
		this.result = result;
	}
	
	// GETTERS AND SETTERS BLOCK
	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}
	
	public void setLeft(Integer left) {
		this.left = left;
	}

	public int getRight() {
		return right;
	}

	public void setRight(int right) {
		this.right = right;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	// Implemented methods from the TestStep interface
	@Override
	/**
	 * Returns true if the multiplication saved is valid and false if it is not
	 * 
	 * 2 * 2 = 4 returns true
	 * 2 * 3 = 4 returns false
	 * 
	 * Really just a proof of concept....
	 */
	public boolean validate() {
		return (left * right) == result;
	}

	@Override
	/**
	 * Uses reflection to get a list of all methods available to this object, and returns the list
	 * 
	 * Goal of the method is for a user to be able to see a list of available methods to use via reflection
	 */
	public List<Method> listMethods() {
		List<Method> methods = new LinkedList<Method>();
		
		Class thisClass = this.getClass();
		
		for(Method method : thisClass.getMethods()) {
			methods.add(method);
		}
		
		return methods;
	}
	
	/**
	 * Implementation of the clone method.
	 */
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String toString() {
		return left + " * " + right + " = " + result;
	}

}

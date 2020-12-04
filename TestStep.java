package test_case_builder;

import java.lang.reflect.Method;

/**
 * Class for making a step object that can be executed by the TestSequence
 * 
 * Stores a method call for later
 * Includes the type the method works on, the method itself, and the parameters the method takes
 * 
 * @author Scott Straguzzi
 *
 */
public class TestStep {
	Testable testObject;
	Method method;
	Object[] params;
	
	public TestStep() {
		// TODO Auto-generated constructor stub
	}
	
	public TestStep(Testable testObject, Method method, Object[] params) {
		this.testObject = testObject; // Mostly kept around for accessing its type...
		this.method = method;
		this.params = params;
	}
	
	public TestStep(Testable testObject, String methodName, Object[] params) {
		this.testObject = testObject;
		
		// Makes an array of Class[] from params to use for declaring the method object
		Class[] paramTypes = new Class[params.length];
		for(int i = 0; i < params.length; i++) {
			paramTypes[i] = params[i].getClass();
		}
		
		try {
			this.method = testObject.getClass().getDeclaredMethod(methodName, paramTypes);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			System.err.println("NoSuchMethodException " + e);
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			System.err.println("SecurityException " + e);
			e.printStackTrace();
		}
		
		this.params = params;
	}
	
	public Testable getTestObject() {
		return testObject;
	}

	public void setTestObject(Testable testObject) {
		this.testObject = testObject;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public Object[] getParams() {
		return params;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}

}

package test_case_builder;

/**
 * A sample view for this project
 * 
 * This class prints a list of available methods to use on testObj, picks a method, makes an array of its parameters, and adds it as a test step
 * 
 * The controller then executes the step(s) and produces a new testObj. The end shows printing the validate method for both of these.
 * 
 * The current one should print true then false then true again
 * 
 * In theory, you could change the first line in main to any object that implements the Testable interface and have it run similarly, making this pretty flexible
 * 
 * @author scott
 *
 */
public class TestSequenceViewer {
	
	public static void main(String[] args) {
		Testable testObj = new MultTestStep(3, 4, 12);
		
		TestSequence sequence = new TestSequence(testObj);
		
		System.out.println(sequence.showMethods());
		
		// Try running the setLeft function on the Testable object using reflection
		// For the full GUI, you'd use the method Class.getDeclaredMethod

		Object[] newLeftParams = new Object[1]; // Creates a new parameters object for setLeft()
		Class[] newLeftTypes = new Class[1];
		newLeftParams[0] = 4; // Populates the parameter object with whatever's needed
		newLeftTypes[0] = newLeftParams[0].getClass(); // Populates as an Integer instead of an int :/ not sure how to fix that easily
		sequence.addStep("setLeft", newLeftTypes, newLeftParams); // Adds it to the sequence
		
		Testable testObj2 = sequence.execute(); // Executes the steps in the test sequence to produce an invalid test object
		

		
		
		System.out.println("testObj should be valid: " + testObj + " " + testObj.validate());
		System.out.println("testObj2 should be invalid: " + testObj2 + " " + testObj2.validate());
	}

}

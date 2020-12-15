package src.test.java.flexible_regression_gui;

import src.main.java.flexible_regression_gui.MultBean;

/**
 * A tester class to make sure the file structure for the maven project is set up properly
 * 
 * Hopefully, MultBeanTester can find MultBean in the equivalent package in the main/java folder
 * 
 * @author scott
 *
 */
public class MultBeanTester {
	
	public static void main(String[] args) {
		System.out.println("Hello World!");
		MultBean bean = new MultBean();
		
		System.out.println(bean);
	}
}

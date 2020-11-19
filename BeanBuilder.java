package gui;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.beans.XMLEncoder;
import java.beans.XMLDecoder;

public class BeanBuilder {
	private MultBean bean;
	private AssertEquals goalProduct;
	
	private String beanPath;
	private String resultPath;
	
	public BeanBuilder(int beanLeft, int beanRight, int shouldEqual) {
		bean = new MultBean();
		bean.setLeft(beanLeft);
		bean.setRight(beanRight);
		
		goalProduct = new AssertEquals();
		goalProduct.setResult(shouldEqual);
		
		this.beanPath = "bean.xml"; 
		this.resultPath = "result.xml";
	}
	
	public BeanBuilder(int beanLeft, int beanRight, int shouldEqual, String beanPath, String resultPath) {
		bean = new MultBean();
		bean.setLeft(beanLeft);
		bean.setRight(beanRight);
		
		goalProduct = new AssertEquals();
		goalProduct.setResult(shouldEqual);
		
		this.beanPath = beanPath;
		this.resultPath = resultPath;
	}
	
	public BeanBuilder(String beanPath, String resultPath) {
		bean = new MultBean();
		goalProduct = new AssertEquals();
		
		try {
			FileInputStream input = new FileInputStream(beanPath);
		    XMLDecoder decoder = new XMLDecoder(input);
		    bean = (MultBean) decoder.readObject();
		    decoder.close();
		} catch(Exception e) {
			System.err.println("Could not open file " + beanPath);
			e.printStackTrace();
		}
		
		try {
			FileInputStream input = new FileInputStream(resultPath);
		    XMLDecoder decoder = new XMLDecoder(input);
		    goalProduct = (AssertEquals) decoder.readObject();
		    decoder.close();
		} catch(Exception e) {
			System.err.println("Could not open file " + resultPath);
			e.printStackTrace();
		}
	}
	
	public void writeBeans() {
		// TODO tomorrow, writes both objects to given files
		try {
			FileOutputStream output = new FileOutputStream(beanPath);
			XMLEncoder encoder = new XMLEncoder(output);
			encoder.writeObject(bean);
		    encoder.close();
		} catch (FileNotFoundException e){
			System.err.println("Error writing to file " + beanPath);
			e.printStackTrace();
		}
		
		try {
		    FileOutputStream output = new FileOutputStream(resultPath);
		    XMLEncoder encoder = new XMLEncoder(output);
			encoder.writeObject(goalProduct);
		    encoder.close();
		} catch (FileNotFoundException e) {
			System.err.println("Error writing to file " + resultPath);
			e.printStackTrace();
		}
		// TODO tomorrow, make new Driver class for interacting with BeanBuilder, goal is to make this the Controller in MVC
	}
	
	public MultBean getBean() {
		return bean;
	}
	
	public AssertEquals getGoalProduct() {
		return goalProduct;
	}
	
	public String getBeanPath() {
		return beanPath;
	}
	
	public void setBeanPath(String newPath) {
		beanPath = newPath;
	}
	
	public String getResultPath() {
		return resultPath;
	}
	
	public void setResultPath(String newPath) {
		resultPath = newPath;
	}
	
	public boolean assertEqual() {
		return goalProduct.assertEqual(bean);
	}
	
	public String toString() {
		return bean + " should be equal to " + goalProduct;
	}
	
	
	/**
	 * Old method for testing without GUI integration
	 */
	public static void main(String args[]) {
		MultBean madeBean = new MultBean();
		MultBean readBean = new MultBean();
		
		AssertEquals result = new AssertEquals();
		AssertEquals readResult = new AssertEquals();
		
		madeBean.setLeft(7); // Left value for the MultBean
		madeBean.setRight(25); // Right value for the MultBean
		
		result.setResult(175); // The intended value of the product of the MultBean
		
		System.out.println("The declared multiplication object is: " + madeBean + ", the intended output is " + result + "\n");
		
		try {
			FileOutputStream output = new FileOutputStream("MultBean.xml");
			XMLEncoder encoder = new XMLEncoder(output);
			encoder.writeObject(madeBean);
			encoder.writeObject(result);
		    encoder.close();
		    System.out.println("MultBean written");
		    
		    output = new FileOutputStream("ResultBean.xml");
		    encoder = new XMLEncoder(output);
			encoder.writeObject(result);
		    encoder.close();
		    System.out.println("Result written");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("Error writing the bean");
			e.printStackTrace();
		}
		
		try {
			FileInputStream input = new FileInputStream("MultBean.xml");
		    XMLDecoder decoder = new XMLDecoder(input);
		    readBean = (MultBean) decoder.readObject();
		    decoder.close();
		    System.out.println("MultBean read");
		    
		    input = new FileInputStream("ResultBean.xml");
		    decoder = new XMLDecoder(input);
		    readResult = (AssertEquals) decoder.readObject();
		    decoder.close();
		    System.out.println("Result read");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("Error reading the bean");
			e.printStackTrace();
		}
		
		System.out.println("\nThe MultBean that was read is: " + readBean);
		System.out.println("The Result that was read is: " + readResult);
		
		System.out.println("\nThe MultBean that was read matches the result: " + readResult.assertEqual(readBean));
	}
}


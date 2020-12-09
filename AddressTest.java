/*
 * @author Alex Caruso	
 * @version 10.20.2020
 */

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class AddressTest {
	private static final String FILENAME = "AddressBean.xml";
	private static String name = "James T. Kirk";
	private static String email = "tribbleTroubles@aol.com";
	private static String address = "55 Enterprise Drive";
	private static String nickname = "Jim";
	
	public static void main(String[] args) {
		AddressBean madeBean = new AddressBean();
		AddressBean readBean = new AddressBean();
		
		madeBean.setName(name);
		madeBean.setEmail(email);
		madeBean.setAddress(address);
		
		AddToAddressBook addressBook = new AddToAddressBook(nickname, madeBean);
		addressBook.run();
		
		VerifyAddress result = new VerifyAddress(name,address,nickname);
		VerifyAddress readResult = new VerifyAddress();
		//encode the bean
		try {
			XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(
					new FileOutputStream(FILENAME)));
			encoder.writeObject(madeBean);
			encoder.close();
			
			encoder = new XMLEncoder(new BufferedOutputStream(
					new FileOutputStream("ResultBean.xml")));
			encoder.writeObject(result);
			encoder.close();
			
			System.out.println("Bean written");
		} catch (FileNotFoundException e) {
			System.err.println("Error writing to the bean");
			e.printStackTrace();
		}
		
		//decode the bean
		try {
			XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(
					new FileInputStream(FILENAME)));
			readBean = (AddressBean) decoder.readObject();
			decoder.close();
			System.out.println("Bean read");
			
			decoder = new XMLDecoder(new BufferedInputStream(
					new FileInputStream("ResultBean.xml")));
			readResult = (VerifyAddress) decoder.readObject();
			decoder.close();
			System.out.println("Result read");
		} catch (FileNotFoundException e) {
			System.err.println("Error reading the bean");
			e.printStackTrace();
		}
		
		System.out.println("The bean that was read is: " + readBean);
		System.out.println("The Result that was read is: " + readResult);
		readResult.run();
		System.out.println("\nThe AddressBean that was read matches the result: " + readResult.isVerified());
	}
}

/*
 * @author Alex Caruso
 * @version 10.20.2020
 */
public class AddressBean implements java.io.Serializable {
	private String name, address, email = null;
	
	// Constructor
	public AddressBean() {	
	}
	
	//name getter and setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	//address getter and setter
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	//email getter and setter
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	  public String toString() {
	    return String.format("\n[Name: %s]\n[Address: %s]\n[Email: %s]\n", name, address, email);
	 }
}
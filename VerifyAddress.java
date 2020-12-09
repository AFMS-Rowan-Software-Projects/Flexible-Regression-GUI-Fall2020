/*
 * @author Alex Caruso
 * @version 10.22.2020
 */
public class VerifyAddress implements Runnable {
	private String name;
	private String address;
	private String nickname;
	private boolean verified = false;
	
	public VerifyAddress() {
		
	}
	
	public VerifyAddress(String name, String address, String nickname) {
		this.name = name;
		this.address = address;
		this.nickname = nickname;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public boolean isVerified() {
		return verified;
	}
	
	@Override
	public void run() {
		if(name.equals(AddToAddressBook.getBean(nickname).getName()) && 
				address.equals(AddToAddressBook.getBean(nickname).getAddress())) {
			verified = true;
		}
	}
	
	public String toString() {
		return String.format("\n[Name: %s]\n[Address: %s]\n", name, address);
	}

}

import java.util.HashMap;

/*
 * @author Alex Caruso
 * @version 10.22.2020
 */
public class AddToAddressBook implements Runnable {
	private static HashMap<String, AddressBean> addressBook;
	private String nickname;
	private AddressBean bean;
	
	public AddToAddressBook(String nickname, AddressBean bean) {
		addressBook = new HashMap<String, AddressBean>();
		this.nickname = nickname;
		this.bean = bean;
	}
	public AddToAddressBook() {
		addressBook = new HashMap<String, AddressBean>();
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public void setBean(AddressBean bean) {
		this.bean = bean;
	}
	
	public void addNewPerson(String nickname, AddressBean bean) {
		addressBook.put(nickname, bean);
	}
	
	public static AddressBean getBean(String nickname) {
		return addressBook.get(nickname);
	}

	@Override
	public void run() {
		addNewPerson(nickname, bean);
	}
}

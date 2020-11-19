package gui;

public class AssertEquals {
	int result = 0; // Defaults to 0 before being instantiated 
	
	public AssertEquals() {
		// TODO Auto-generated constructor stub
	}

	public AssertEquals(int newResult) {
		result = newResult;
	}
	
	public int getResult() {
		return result;
	}
	
	public void setResult(int newResult) {
		result = newResult;
	}
	
	public boolean assertEqual(MultBean isEqual) {
		try {
			return ((AssertEquals) isEqual.call()).getResult() == this.result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public String toString() {
		return "" + result;
	}
}


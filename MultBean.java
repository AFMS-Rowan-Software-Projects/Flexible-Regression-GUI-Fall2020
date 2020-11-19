package gui;

import java.util.concurrent.Callable;

public class MultBean implements Callable {

	int left, right;
	
	public MultBean() {
		left = 0;
		right = 0;
	}
	
	public int getLeft() {
		return left;
	}
	
	public int getRight() {
		return right;
	}
	
	public void setLeft(int newLeft) {
		left = newLeft;
	}
	
	public void setRight(int newRight) {
		right = newRight;
	}
	
	public String toString() {
		return left + " * " + right;
	}

	@Override
	public Object call() throws Exception {
		return new AssertEquals(left * right);
	}

}


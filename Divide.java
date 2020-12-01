
/**
 * @author Alex Caruso
 * @version 12.01.2020
 */
public class Divide implements java.io.Serializable {
	
	private double num; //numerator
	private double denom; //denominator
	private double result; //quotient
	
	//no-arg constructor
	public Divide() {
		
	}

	public double getNum() {
		return num;
	}

	public void setNum(double num) {
		this.num = num;
	}

	public double getDenom() {
		return denom;
	}

	public void setDenom(double denom) {
		this.denom = denom;
	}
	
	public void setResult() {
		result = num/denom;
	}
	
	public double getResult() {
		return result;
	}
	
}

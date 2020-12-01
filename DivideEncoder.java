import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

/**
 * @author Alex Caruso
 * @version 12.01.2020
 */
public class DivideEncoder {
	
	public static void main(String[] args) throws Exception {
		
		Divide div = new Divide();
		div.setNum(5);
		div.setDenom(50);
		div.setResult();
		
		XMLEncoder xmlEncoder = new XMLEncoder(
				new BufferedOutputStream(new FileOutputStream("dividebean.xml")));
		xmlEncoder.writeObject(div);
		xmlEncoder.close();
	}
}

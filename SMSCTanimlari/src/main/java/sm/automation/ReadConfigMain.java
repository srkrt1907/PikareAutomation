
package sm.automation;

import java.io.IOException;

public class ReadConfigMain{
	public static void main(String[] args) throws IOException {
		ReadConfigMainValues properties = new ReadConfigMainValues();
		System.out.println(properties.getUserName());
		
	}
}
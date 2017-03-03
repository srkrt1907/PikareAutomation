package sm.automation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import java.util.Properties;

public class ReadConfigMainValues {
	String userName = " ";
	String passWord = " ";
	String smsc = " ";
	String briefDescription = " ";
	String titleDescription = " ";
	String smscDescription = "";
	String smDescription = "";
	String lastGenDescription = "";

	public ReadConfigMainValues() {
		super();
		try {
			getPropValues();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	



	public String getLastGenDescription() {
		return lastGenDescription;
	}





	public void setLastGenDescription(String lastGenDescription) {
		this.lastGenDescription = lastGenDescription;
	}





	public String getTitleDescription() {
		return titleDescription;
	}

	public void setTitleDescription(String titleDescription) {
		this.titleDescription = titleDescription;
	}

	public String getBriefDescription() {
		return briefDescription;
	}

	public void setBriefDescription(String briefDescription) {
		this.briefDescription = briefDescription;
	}

	public String getSmsc() {
		return smsc;
	}

	public void setSmsc(String smsc) {
		this.smsc = smsc;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	InputStream inputStream;

	public void getPropValues() throws IOException {
		try {
			Properties prop = new Properties();
			String propFileName = "config.properties";

			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file: " + propFileName);
			}

			String smUserName = prop.getProperty("sm_username");
			String smPassWord = prop.getProperty("sm_password");
			String smsc = prop.getProperty("smsc");
			String briefDescription = prop.getProperty("brief_description");
			String smscDescription = prop.getProperty("gen_smsc");
			String smDescription = prop.getProperty("gen_smsc_description");

			userName = smUserName;
			passWord = smPassWord;
			this.smsc = smsc;
			this.briefDescription = briefDescription;
			titleDescription = smsc + " " + briefDescription;
			this.smscDescription = smscDescription;
			this.smDescription = smDescription;
			lastGenDescription = smscDescription + " " + smDescription;
			

		} catch (Exception e) {
			System.out.println("Exception " + e);
		} finally {
			inputStream.close();
		}

	}
}

package sm.automation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

public class smCreateDef

{
	ReadConfigMainValues properties = new ReadConfigMainValues();
	
	
	
	WebDriver driver = null;
	String smUsername = properties.getUserName();
	String smPassword = properties.getPassWord();
	String changefile = "";
	String changefilename = "";
	String docfile;
	String docfileDetay;
	String changeFileDetay = "";
	String change_id;
	FileWriter fw = null;
	BufferedWriter bw = null;
	PrintWriter out = null;

	public void screenShot() throws IOException, InterruptedException {
		File scr = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File dest = new File(docfile + "\\" + timestamp() + ".png");
		FileUtils.copyFile(scr, dest);
		Thread.sleep(1000);
	}

	public String timestamp() {
		return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
	}

	@Given("^SMe girilir.$")
	public void SMeGirilir() throws Throwable {
		System.setProperty("webdriver.chrome.driver", "./dependency/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://sm.turktelekom.com.tr/sm/index.do");
		System.out.println("**SM sayfasi acilmistir.**");
	}

	@Then("^Kullanici adi girilir.$")
	public void kullaniciAdiGirilir() throws Throwable {
		driver.findElement(By.xpath("//*[@id='LoginUsername']")).clear();
		driver.findElement(By.xpath("//*[@id='LoginUsername']")).sendKeys(smUsername);
		System.out.println("**Kullanici adi girildi.**");
	}

	@Then("^Sifre girilir.$")
	public void sifreGirilir() throws Throwable {
		try {
			driver.findElement(By.xpath("//*[@id='LoginPassword']")).clear();
			driver.findElement(By.xpath("//*[@id='LoginPassword']")).sendKeys(smPassword);
			System.out.println("**Sifre girildi.**");
		} catch (Exception e) {
			System.out.println("Sistemsel bir hata olustu. Tekrardan baslatiniz.");
			Thread.sleep(10000);
		}
	}

	@Then("^Giris butonuna basilir.$")

	public void girisButonunaBasilir() throws Throwable {
		try {
			driver.findElement(By.xpath("//*[@id='loginBtn']")).click();
			System.out.println("**Giris butonuna basildi.**");
			Thread.sleep(200);
		} catch (Exception e) {
			System.out.println("Sistemsel bir hata olustu. Tekrardan baslatiniz.");
			Thread.sleep(10000);
		}
	}

	@Then("^Changes menusu secilir.$")
	public void changesMenusuSecilir() throws Throwable {
		try {
			driver.findElement(By.xpath("//*[@id='ROOT/Changes']/div/span")).click();
			System.out.println("**Changes menusu secildi.**");
			Thread.sleep(200);
		} catch (Exception e) {
			try {
				driver.switchTo().parentFrame();
				System.out.println("Sistemsel bir hata olustu. Oturum kapatilacak.");
				Thread.sleep(1000);
				driver.findElement(By.xpath("//*[@id='ext-gen-top103']")).click();
				Thread.sleep(500);
				for (int i = 0; i < 3; i++) {
					Actions actionoturum = new Actions(driver);
					actionoturum.sendKeys(Keys.TAB);
					actionoturum.perform();
					Thread.sleep(35);
				}
				Thread.sleep(1000);
				Actions cıkıs = new Actions(driver);
				cıkıs.sendKeys(Keys.ENTER);
				cıkıs.perform();
				Thread.sleep(1000);
				Robot robot = new Robot();
				Thread.sleep(100);
				robot.keyPress(KeyEvent.VK_ENTER);
				Thread.sleep(10000);

			} catch (Exception e1) {

				System.out.println("Oturum kapatilamadi. Lütfen manual olarak kapatiniz.");
				Thread.sleep(10000);
			}
		}
	}

	@Then("^Create New Change secilir.$")
	public void createNewChangeSecilir() throws Throwable {
		try {
			Thread.sleep(500);
			driver.findElement(By.xpath("//span[contains(text(),'Create New Change')]")).click();
			System.out.println("**Craete New Change menusu secildi.**");
		} catch (Exception e) {
			try {
				driver.switchTo().parentFrame();
				System.out.println("Sistemsel bir hata olustu. Oturum kapatilacak.");
				Thread.sleep(1000);
				driver.findElement(By.xpath("//*[@id='ext-gen-top103']")).click();
				Thread.sleep(500);
				for (int i = 0; i < 3; i++) {
					Actions actionoturum = new Actions(driver);
					actionoturum.sendKeys(Keys.TAB);
					actionoturum.perform();
					Thread.sleep(35);
				}
				Thread.sleep(1000);
				Actions cıkıs = new Actions(driver);
				cıkıs.sendKeys(Keys.ENTER);
				cıkıs.perform();
				Thread.sleep(1000);
				Robot robot = new Robot();
				Thread.sleep(100);
				robot.keyPress(KeyEvent.VK_ENTER);
				Thread.sleep(10000);

			} catch (Exception e1) {

				System.out.println("Oturum kapatilamadi. Lütfen manual olarak kapatiniz.");
				Thread.sleep(10000);
			}
		}
	}

	@Then("^Operational Change secilir.$")
	public void operationalChangeSecilir() throws Throwable {
		try {
			for (int i = 0; i < 3; i++) {
				Actions action = new Actions(driver);
				action.sendKeys(Keys.TAB);
				action.perform();
				Thread.sleep(30);
			}
			Actions actionenter = new Actions(driver);
			actionenter.sendKeys(Keys.ENTER);
			actionenter.perform();
			System.out.println("**Operational Change secildi.**");
		} catch (Exception e) {
			try {
				driver.switchTo().parentFrame();
				System.out.println("Sistemsel bir hata olustu. Oturum kapatilacak.");
				Thread.sleep(1000);
				driver.findElement(By.xpath("//*[@id='ext-gen-top103']")).click();
				Thread.sleep(500);
				for (int i = 0; i < 3; i++) {
					Actions actionoturum = new Actions(driver);
					actionoturum.sendKeys(Keys.TAB);
					actionoturum.perform();
					Thread.sleep(35);
				}
				Thread.sleep(1000);
				Actions cıkıs = new Actions(driver);
				cıkıs.sendKeys(Keys.ENTER);
				cıkıs.perform();
				Thread.sleep(1000);
				Robot robot = new Robot();
				Thread.sleep(100);
				robot.keyPress(KeyEvent.VK_ENTER);
				Thread.sleep(10000);
				driver.close();

			} catch (Exception e1) {

				System.out.println("Oturum kapatilamadi. Lütfen manual olarak kapatiniz.");
				Thread.sleep(10000);
			}
		}
	}

	@Then("^Change detaylari girilir.$")
	public void changedetaylarigirilir() throws Throwable {
		try {
			Thread.sleep(1000);
			driver.switchTo().frame(1);
			System.out.println("**Frame degisti.**");
			Thread.sleep(100);
			WebElement change_idtext = driver.findElement(By.xpath("//*[@id='X6']"));
			// System.out.println(change_id.getAttribute("value"));
			try {
				fw = new FileWriter("ChangeID.txt", true);
				bw = new BufferedWriter(fw);
			
				out = new PrintWriter(bw);
				out.println(change_idtext.getAttribute("value") + "  " + "Tarih:  " + timestamp()); // id
																									// pathfile
																									// yazıldı
				out.append("\n");
				out.close();

				// String output ="C:\\MsisdnTalepler\\Output\\Desktop\\Kanban
				// Style-Talepler\\Changes";
				//String o1 = "C:/MsisdnTalepler/Output/SmAtlasID.txt";
				//fw = new FileWriter(o1, true);
				bw = new BufferedWriter(fw);
				out = new PrintWriter(bw);
				out.println(change_idtext.getAttribute("value") + "  " + "Tarih:  " + timestamp()); // id
																									// pathfile
																									// yazıldı
				out.append("\n");
				out.close();
			} catch (IOException e) {
			}

			finally {
				if (out != null)
					out.close();
				try {
					if (bw != null)
						bw.close();
				} catch (IOException e) {
				}
				try {
					if (fw != null)
						fw.close();
				} catch (IOException e) {

				}
			}
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id='X12Button']")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id='X12Popup_2']")).click();
			Thread.sleep(1000);
			System.out.println("**Domain girildi.**");
			driver.findElement(By.xpath("//*[@id='X19FillButton']")).click();
			System.out.println("**Category secmek icin popup acildi.**");
			Thread.sleep(4000);

			for (int i = 0; i < 36; i++) {
				Actions actioncategory = new Actions(driver);
				actioncategory.sendKeys(Keys.DOWN);
				actioncategory.perform();
				Thread.sleep(80);
			}
			Actions actioncategoryenter = new Actions(driver);
			actioncategoryenter.sendKeys(Keys.ENTER);
			actioncategoryenter.perform();
			Thread.sleep(2500);
			for (int i = 0; i < 18; i++) {
				Actions actionSubCategory = new Actions(driver);
				actionSubCategory.sendKeys(Keys.DOWN);
				actionSubCategory.perform();
				Thread.sleep(80);
			}
			Thread.sleep(500);
			Actions actionSubCategoryenter = new Actions(driver);
			actionSubCategoryenter.sendKeys(Keys.ENTER);
			actionSubCategoryenter.perform();

			Thread.sleep(1500);
			System.out.println("**Category ve subcategory girildi.**");
			driver.findElement(By.xpath("//*[@id='X48FillButton']")).click();
			Thread.sleep(2500);
			System.out.println("**Coordinator Operation Line secmek icin popup acildi.**");
			for (int i = 0; i < 18; i++) {
				Actions actionCoordinatorOperation = new Actions(driver);
				actionCoordinatorOperation.sendKeys(Keys.DOWN);
				actionCoordinatorOperation.perform();
				Thread.sleep(80);
			}
			Actions actionCoordinatorOperationenter = new Actions(driver);
			actionCoordinatorOperationenter.sendKeys(Keys.ENTER);
			actionCoordinatorOperationenter.perform();
			Thread.sleep(1000);
			for (int i = 0; i < 2; i++) {
				Actions actionCoordinatorAssignmentGroup = new Actions(driver);
				actionCoordinatorAssignmentGroup.sendKeys(Keys.DOWN);
				actionCoordinatorAssignmentGroup.perform();
				Thread.sleep(30);
			}
			Actions actionCoordinatorAssignmentGroupenter = new Actions(driver);
			actionCoordinatorAssignmentGroupenter.sendKeys(Keys.ENTER);
			actionCoordinatorAssignmentGroupenter.perform();
			Thread.sleep(1000);
			System.out.println("**Coordinator Operation Line ve Coordinator Assignment Group secildi.**");
			driver.findElement(By.xpath("//*[@id='X54']")).click();
			Actions actionPagingAnnouncement = new Actions(driver);
			actionPagingAnnouncement.sendKeys(Keys.DOWN);
			actionPagingAnnouncement.perform();
			Thread.sleep(1000);
			Actions actionPagingAnnouncemententer = new Actions(driver);
			actionPagingAnnouncemententer.sendKeys(Keys.ENTER);
			actionPagingAnnouncemententer.perform();
			Thread.sleep(1000);
			System.out.println("**PagingAnnouncemententer secildi.**");
			driver.findElement(By.xpath("//*[@id='X60']")).click();
			Actions actionStartPA = new Actions(driver);
			actionStartPA.sendKeys(Keys.DOWN);
			actionStartPA.perform();
			Thread.sleep(1000);
			Actions actionStartPAenter = new Actions(driver);
			actionStartPAenter.sendKeys(Keys.ENTER);
			actionStartPAenter.perform();
			Thread.sleep(1000);
			System.out.println("**Start PA secildi.**");

			driver.findElement(By.xpath("//*[@id='X58']")).click();
			Thread.sleep(1000);
			Actions actionSurumenter = new Actions(driver);
			actionSurumenter.sendKeys(Keys.ENTER);
			actionSurumenter.perform();
			System.out.println("**Surum bilgisi secildi.**");
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, 5);
			// System.out.println(dateFormat.format(cal.getTime()));
			driver.findElement(By.xpath("//*[@id='X64']")).sendKeys(dateFormat.format(cal.getTime()));
			Thread.sleep(800);
			System.out.println("**Date bilgisi girildi.**");
			driver.findElement(By.xpath("//*[@id='X68']")).click();
			Thread.sleep(800);
			for (int i = 0; i < 6; i++) {
				Actions actionChangeReason = new Actions(driver);
				actionChangeReason.sendKeys(Keys.DOWN);
				actionChangeReason.perform();
				Thread.sleep(30);
			}
			Thread.sleep(200);
			System.out.println("**Change Reason bilgisi girildi.**");
			Actions actionChangeReasonenter = new Actions(driver);
			actionChangeReasonenter.sendKeys(Keys.ENTER);
			actionChangeReasonenter.perform();
			Thread.sleep(100);
			driver.findElement(By.xpath("//*[@id='X79']")).sendKeys("SMSGW");
			Thread.sleep(100);
			driver.findElement(By.xpath("//*[@id='X79FillButton']")).click();
			Thread.sleep(2500);
			Actions actionSevice = new Actions(driver);
			actionSevice.sendKeys(Keys.ENTER);
			actionSevice.perform();
			Thread.sleep(1000);
			System.out.println("Service bilgisi girildi.");

			// String directory ="C:\\Users\\kafein\\Desktop\\Kanban
			// Style-Talepler\\Changes";
			

			
			Thread.sleep(1000);
			String smsc = properties.getSmsc();
			String briefDescription = properties.getTitleDescription();
			System.out.println("TitleDescription: " + properties.getTitleDescription());
			driver.findElement(By.xpath("//*[@id='X115']"))
					.sendKeys(properties.getTitleDescription());
			// driver.findElement(By.xpath("//*[@id='X117']")).sendKeys(changefilename+"
			// "+ "talebine ait dokümanlar ektedir. Talebe istinaden msisdn fw
			// tanımlamasının yapılmasını rica ediyorum. Dökümandaki
			// tanımlanacak url için /* şeklinde tanımlanmasını rica ederim. ");

			// Açıklamaya text dosyasını at
			
				driver.findElement(By.xpath("//*[@id='X117']")).clear();

				driver.findElement(By.xpath("//*[@id='X117']")).sendKeys(properties.getLastGenDescription());

		
			Thread.sleep(2000);
			/*
			 * //Attchmanttttttttttt JavascriptExecutor js =
			 * (JavascriptExecutor) driver;
			 * js.executeScript("javascript:window.scrollBy(250,350)");
			 * js.executeScript("javascript:window.scrollBy(250,350)");
			 * Thread.sleep(1000);
			 * driver.findElement(By.xpath("//*[@id='X227_t']")).click();
			 * //Attchmant for(int i = 0 ; i<4;i++) { Actions actionChangeReason
			 * = new Actions(driver); actionChangeReason.sendKeys(Keys.TAB);
			 * actionChangeReason.perform(); Thread.sleep(35); }
			 * Thread.sleep(500); Actions dok = new Actions(driver);
			 * dok.sendKeys(Keys.ENTER); dok.perform(); Thread.sleep(1000);
			 * 
			 * StringSelection stringSelection = new
			 * StringSelection(changefile); Thread.sleep(100);
			 * Toolkit.getDefaultToolkit().getSystemClipboard().setContents(
			 * stringSelection, null); Thread.sleep(100); Robot robot = new
			 * Robot(); Thread.sleep(100); robot.keyPress(KeyEvent.VK_ENTER);
			 * Thread.sleep(100); robot.keyPress(KeyEvent.VK_ENTER);
			 * Thread.sleep(100); robot.keyPress(KeyEvent.VK_CONTROL);
			 * Thread.sleep(100); robot.keyPress(KeyEvent.VK_V);
			 * Thread.sleep(100); robot.keyRelease(KeyEvent.VK_V);
			 * Thread.sleep(100); robot.keyRelease(KeyEvent.VK_CONTROL);
			 * Thread.sleep(100); robot.keyPress(KeyEvent.VK_ENTER);
			 * Thread.sleep(100); robot.keyRelease(KeyEvent.VK_ENTER);
			 * Thread.sleep(2000);
			 */

			driver.switchTo().parentFrame();
			// System.out.println("Frame degisti");

			/*
			 * güncelleme Old SM Links-->ITSM Link
			 * driver.findElement(By.xpath("//*[@id='ext-gen-top170']")).click()
			 * ; // Old SM Links-->ITSM Link Thread.sleep(1000); for(int i = 0 ;
			 * i<4;i++) { Actions actionoturum = new Actions(driver);
			 * actionoturum.sendKeys(Keys.TAB); actionoturum.perform();
			 * Thread.sleep(35); } System.out.
			 * println("Guncelleme yapmayacaksaniz save butonuna basip devam edebilirsiniz. Sonrasonda next phase butonuna basmayi unutmayiniz..."
			 * );
			 */
			Thread.sleep(5000);
			// Thread.sleep(500);
			// Actions save = new Actions(driver);
			// driver.findElement(By.xpath("//*[@id='ext-gen-top933']")).click();
			// //save submit butonuna tıklanır
			// save.sendKeys(Keys.ENTER);
			// save.perform();
		}

		catch (Exception e) {
			try {
				driver.switchTo().parentFrame();
				System.out.println("Sistemsel bir hata olustu. Oturum kapatilacak.");
				Thread.sleep(1000);
				driver.findElement(By.xpath("//*[@id='ext-gen-top103']")).click();
				Thread.sleep(500);
				for (int i = 0; i < 3; i++) {
					Actions actionoturum = new Actions(driver);
					actionoturum.sendKeys(Keys.TAB);
					actionoturum.perform();
					Thread.sleep(35);
				}
				Thread.sleep(1000);
				Actions cıkıs = new Actions(driver);
				cıkıs.sendKeys(Keys.ENTER);
				cıkıs.perform();
				Thread.sleep(1000);
				Robot robot = new Robot();
				Thread.sleep(100);
				robot.keyPress(KeyEvent.VK_ENTER);
				Thread.sleep(10000);
				driver.close();
			} catch (Exception e1) {

				System.out.println("Oturum kapatilamadi. Lütfen manual olarak kapatiniz.");
				Thread.sleep(10000);
			}
		}
	

	}

	String boslukAzalt(String txt) {
		String temp = txt.replace("\t", " ");
		String yeniTxt = "";
		for (int i = 0; i < temp.length(); i++) {
			char c = temp.charAt(i);
			if (c == ' ') {
				if ((i + 1) == temp.length())
					continue;
				char c2 = temp.charAt(i + 1);
				if (c2 == ' ') {
					continue;
				}
			}

			yeniTxt += c;
		}
		return yeniTxt;
	}

}

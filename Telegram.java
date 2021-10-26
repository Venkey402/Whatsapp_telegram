import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions; 

public class Telegram_Online_Dev {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		String profilename = "5_Helpline";
		//String profilename = "2_Relli";
		//String profilename = "Vadhina";
		//String profilename = "Annayya";
		//String profilename = "Mahesh Mamidi";
		
		String TelegramAccount = "" ;
		if (profilename.equals("5_Helpline"))
		{
			TelegramAccount = "Mine1";
		}
		else if (profilename.equals("2_Relli"))
		{
			TelegramAccount = "Chinna1";
		}
	
		 System.setProperty("webdriver.chrome.driver", "C:\\eclipse\\Drivers\\Chrome\\chromedriver.exe");  
		 ChromeOptions options = new ChromeOptions();
		
		 //options.addArguments("user-data-dir=C:\\Users\\venkatm\\AppData\\Local\\Google\\Chrome\\User Data\\Telegram_Amma2");
		options.addArguments("user-data-dir=C:\\Users\\venkatm\\AppData\\Local\\Google\\Chrome\\User Data\\Telegram_"+TelegramAccount);
		//options.addArguments("user-data-dir=C:\\Users\\venkatm\\AppData\\Local\\Google\\Chrome\\User Data\\Telegram_Chinna1");
		 
		 	 
		 ChromeDriver driver = new ChromeDriver(options);	
		
		 driver.navigate().to("https://web.telegram.org/");  
		 driver.manage().window().maximize(); 
		 
		 Thread.sleep(5000);
		 //Thread.sleep(15000);
		 
		 if (driver.findElementsById("telegram-search-input").size()!=0 )
		 {
			 driver.findElementById("telegram-search-input").clear();
			 driver.findElementById("telegram-search-input").sendKeys(profilename);
			 Thread.sleep(3000);
			 driver.findElementByXPath("//div[@class='ListItem-button']/div/div/div[@class='title']/h3[text()='"+profilename+"']").click(); 
		 
		 }
		/* else if (driver.findElementsByXPath("//div[@class='input-search']/input").size()!=0)
		 {
			 driver.findElementByXPath("//div[@class='input-search']/input").clear();
			 driver.findElementByXPath("//div[@class='input-search']/input").sendKeys(profilename);
			 Thread.sleep(3000);
			
			 driver.findElementByXPath("//div/p[@class='dialog-title']/span/span[text()='2_Relli'][@data-dialog=1]").click(); 
			 
			// driver.findElementByXPath("//div[@class='ListItem-button']/div/div/div[@class='title']/h3[text()='"+profilename+"']").click(); 
			 //driver.navigate().to("https://web.telegram.org/"); 
		 
		 }*/
		 
		 
		 Thread.sleep(3000);
		
		 int IsOnline = driver.findElementsByXPath("//span[text()='online']").size();
		 for (int i=0;i==0;) 
		 {
		 
			 //System.out.println("1");
			 while (IsOnline==0)
			 {
				 //System.out.println("2");
				 IsOnline = driver.findElementsByXPath("//span[text()='online']").size();
			 }
			//Online time
			 DateTimeFormatter myDate = DateTimeFormatter.ofPattern("M/dd/yyyy");  
			 DateTimeFormatter myOnlinetime = DateTimeFormatter.ofPattern("HH:mm:ss"); 
			 String myDate1 =myDate.format(LocalDateTime.now());  
			 String myOnlinetime1= myOnlinetime.format(LocalDateTime.now()); 
			//System.out.print("Online time "+myOnlinetime1); 	
			 int Refresh=0;
			 while (IsOnline>=1)
			 {
				 //System.out.println("3"); 
				 IsOnline = driver.findElementsByXPath("//span[text()='online']").size();
				 Thread.sleep(1000);
				 Refresh = Refresh++;
				 if(Refresh%10==0)
				 {
					driver.navigate().refresh();
				 Thread.sleep(3000);
				 }
				 //System.out.println(IsOnline); 
			 }
			 //driver.findElementByXPath("//div[@role='textbox']").click();
			 //System.out.print("Online time "+myOnlinetime1); 	
			 //Offline time
			 DateTimeFormatter myOfflinetime = DateTimeFormatter.ofPattern("HH:mm:ss"); 
			 String myOfflinetime1 = myOfflinetime.format(LocalDateTime.now()); 
			 String filename= "C:\\Users\\venkatm\\Downloads\\telegram_"+profilename+".csv";
			 FileWriter fw = new FileWriter(filename,true); //the true will append the new data
			 fw.write(myDate1+","+myOnlinetime1+","+myOfflinetime1+"\r");//appends the string to the file
			 fw.close();
			 String filename2= "C:\\Users\\venkatm\\Downloads\\telegram_"+profilename+"WithAccount.csv";
			 FileWriter fw2 = new FileWriter(filename2,true); //the true will append the new data
			 fw2.write(myDate1+","+myOnlinetime1+","+myOfflinetime1+","+TelegramAccount+"\r");//appends the string to the file
			 fw2.close();
			 System.out.println("Dev Telegram completed on :  " + myDate1+","+myOnlinetime1+","+myOfflinetime1);
		
		 }    
        
	}

}

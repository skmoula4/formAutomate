package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
    ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation.
     * Follow `testCase01` `testCase02`... format or what is provided in
     * instructions
     */
    @Test
    public void testCase01() throws Exception   {

        driver.get(
                "https://docs.google.com/forms/d/e/1FAIpQLSep9LTMntH5YqIXa5nkiPKSs283kdwitBBhXWyZdAS-e4CxBQ/viewform");
        
        Thread.sleep(2000);
            
        //Enter the Name 
        Wrappers.enterText(driver, By.xpath("(//input[@type='text'])[1]"), "Crio Learner");
        //convert time into epoch and upend to text
        long epoch = System.currentTimeMillis() / 1000; // convert time into epoch
        System.out.println("Actual time : "+System.currentTimeMillis()+" epoch time : " +epoch);
        Wrappers.enterText(driver, By.xpath("//textarea[@class='KHxj8b tL9Q4c']"),
                "I want to be the best QA Engineer! " + epoch);
        //Select the 3-5  years experience 
        Wrappers.click(driver, By.xpath("//span[contains(text(),'3 - 5')]"));
        //Store the skills in string array
        String[] skills = { "Java", "Selenium", "TestNG" };
        //Iterate through the all skills and select
        for (String skill : skills) {
            Wrappers.click(driver, By.xpath("//span[contains(text(),'" + skill + "')]"));

        }
        //click the dropdown to open
        Wrappers.click(driver, By.xpath("//div[@class='MocG8c HZ3kWc mhLiyf LMgvRb KKjvXb DEh1R']"));
       //click the Mr from the dropdown
        Wrappers.click(driver, By.xpath("(//span[@class='vRMGwf oJeWuf' and text()='Mr'])[2]"));

        //Get current date and minus 7 days with mmddyyyy format
        String date = LocalDate.now().minusDays(7).format(DateTimeFormatter.ofPattern("MMddyyyy"));
        System.out.println(date);
        
        Wrappers.enterText(driver, By.xpath("//input[@type='date']"), date);
        //locat hour and minute elements and pass 7 and 30 
        Wrappers.enterText(driver, By.xpath("//input[@aria-label='Hour']"), "07");
        Wrappers.enterText(driver, By.xpath("//input[@aria-label='Minute']"), "30");
        // Click on Submit button
        Wrappers.click(driver, By.xpath("//span[text()='Submit']"));
        //Get the success message and display in the consol
        String message = Wrappers.getText(driver, By.xpath("//div[@class='vHW8K']"));
        System.out.println(message);
    }

    /*
     * Do not change the provided methods unless necessary, they will help in
     * automation and assessment
     */
    @BeforeTest
    public void startBrowser() {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log");

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }

    @AfterTest
    public void endTest() {
        driver.close();
        driver.quit();

    }
}
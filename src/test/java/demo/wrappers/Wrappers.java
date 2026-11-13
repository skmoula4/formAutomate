package demo.wrappers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */
    static  WebDriverWait wait;
    public static void enterText(WebDriver driver,By locator, String text){
        try{
        wait= new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

        WebElement element=driver.findElement(locator);
       
        element.sendKeys(text);    
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }

    public static void click(WebDriver driver, By locator){
        try{
        wait= new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        WebElement element=driver.findElement(locator);
        element.click();
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    public static  String getText(WebDriver driver, By locator){
       try{
            wait= new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        WebElement element=driver.findElement(locator);
        return element.getText();
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }
}

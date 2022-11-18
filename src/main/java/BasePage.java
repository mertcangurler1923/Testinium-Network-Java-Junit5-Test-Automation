import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BasePage {
    WebDriver driver;
    WebDriverWait wait;
    Actions actions;
    public BasePage(WebDriver driver) {
        this.driver=driver;
        actions = new Actions(this.driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }
    public WebElement find(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    public List<WebElement> findAll(By locator) {
        return driver.findElements(locator);
    }
    public void click(By locator) {
        find(locator).click();
    }
    public void click(WebElement element) {
        element.click();
    }
    public void inputEnter(By locator, String sendkeys){
        find(locator).sendKeys(sendkeys, Keys.ENTER);
    }
    public void input(By locator, String sendkeys){
        find(locator).sendKeys(sendkeys);
    }
    public String getlocation(){
        return driver.getCurrentUrl();
    }
    public void cookie(By locator){
        try{
            click(locator);
        }
        catch (Exception e){

        }
    }
    public void scrollToPage(By locator) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement webElement=find(locator);
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})", webElement);
        sleep(4000);
    }
    public void scrollToPage(WebElement webElement) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})", webElement);
        sleep(4000);
    }
    public void sleep(int value){
        try{
            Thread.sleep(value);
        }
        catch (InterruptedException ex)
        {
        }
    }
    public void moveToElement(WebElement element) {
        actions.moveToElement(element).perform();
        sleep(1000);
    }
    public void moveToElement(By locator) {
        WebElement element=find(locator);
        actions.moveToElement(element).perform();
        sleep(1000);
    }
    public String getAttribute(By locator, String attribute) {
        String value = find(locator).getAttribute(attribute);
        return value;
    }
    public String getText(By locator) {
        String value = find(locator).getText();
        return value;
    }
    public List<List<By>> discountPercentLocatorFind(By locator){
        List<WebElement> allPercentList=findAll(locator);
        List<By> testLocatorCard=new ArrayList<>();
        List<By> testSizeCard=new ArrayList<>();
        List<By> locatorAfterMoneyCard=new ArrayList<>();
        List<By> locatorBeforeMoneyCard=new ArrayList<>();
        List<List<By>> discountPercentCard=new ArrayList<List<By>>();
        int count=0;
        for (int i=0;i<allPercentList.size();i++) {
            String value=allPercentList.get(i).getAttribute("id");
            By testLocator=By.cssSelector("div[id='"+value+"'] div[class='product__discountPercent']");
            try {
                wait.until(ExpectedConditions.presenceOfElementLocated(testLocator));
                By testSize = By.cssSelector("div[id='" + value + "'] div[class='product__size -productCart radio-box'] label[class='radio-box__label']");
                By locatorBeforeMoney = By.cssSelector("div[id='" + value + "'] span[class='product__price -label -old -size']");
                By locatorAfterMoney = By.cssSelector("div[id='" + value + "'] span[class='product__price -actual']");
                testLocatorCard.add(testLocator);
                testSizeCard.add(testSize);
                locatorAfterMoneyCard.add(locatorAfterMoney);
                locatorBeforeMoneyCard.add(locatorBeforeMoney);

            } catch (Exception e){
                if (count==1){
                    break;
                }
                else{
                    count++;
                }
            }
        }
        discountPercentCard.add(testLocatorCard);
        discountPercentCard.add(testSizeCard);
        discountPercentCard.add(locatorAfterMoneyCard);
        discountPercentCard.add(locatorBeforeMoneyCard);
        return discountPercentCard;
    }
    public static List<String> csvRead() throws FileNotFoundException {
        // List<String> idPassword=new ArrayList<>();
        File fl = new File("dosya.csv");
        if (fl.exists()) {
            System.out.println("Bulundu");
        } else {
            System.out.println("Bulunmadı");
        }
        Scanner s = new Scanner(fl);
        List<String> idPassword = null;
        while (s.hasNextLine()) {
            String text = s.nextLine();
            idPassword = List.of(text.split(";"));
        }
        return idPassword;
    }
    public String wait(By locator){
        String stringWait;
        /*wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        stringWait=Boolean.toString(booleanWait);*/
        try{
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            stringWait="true";
        }
        catch (Exception ex){
            stringWait="false";
        }
        return stringWait;
    }


}



package com.fkt.base;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Utilites {
    public static WebDriver driver;

    public static String Propertyfile(String file) throws IOException {
        FileInputStream obj = new FileInputStream(new File("src/com/flip/Testdata/Testdata.properties").getAbsolutePath());
        Properties obj1 = new Properties();
        obj1.load(obj);
        return obj1.getProperty(file);
    }

    public static String Excelfile(String sheetname, int row, int col){
        String result="";
        try{
            FileInputStream obj2 = new FileInputStream(new File("src/com/flip/Testdata/Customerdetails.xlsx").getAbsolutePath());
            XSSFWorkbook obj3 = new XSSFWorkbook(obj2);
            XSSFSheet obj4 = obj3.getSheet(sheetname);
            XSSFRow obj5 = obj4.getRow(row);
            XSSFCell obj6 = obj5.getCell(col);
            if(obj6!=null){
                switch(obj6.getCellType()){
                    case XSSFCell.CELL_TYPE_STRING:
                        result = String.valueOf(obj6.getStringCellValue());
                        break;
                    case XSSFCell.CELL_TYPE_NUMERIC:
                        result = String.valueOf(obj6.getNumericCellValue());
                        break;
                    case XSSFCell.CELL_TYPE_FORMULA:
                        result = String.valueOf(obj6.getCellFormula());
                        break;
                    case XSSFCell.CELL_TYPE_BOOLEAN:
                        result = String.valueOf(obj6.getBooleanCellValue());
                        break;
                    case XSSFCell.CELL_TYPE_BLANK:
                        result="";
                        break;
                    default:
                        break;
                }
            }
        }
        catch(Exception e){
            result="";
        }
        return result;
    }

    public static void Openbrowser() throws IOException{
        String appbrowser = Propertyfile("browser");
        String appurl = Propertyfile("Url");
        if(appbrowser.equalsIgnoreCase("firefox")){
            driver = new FirefoxDriver();
            driver.get(appurl);
            driver.manage().window().maximize();
        }
        else if(appbrowser.equalsIgnoreCase("Chrome")){
            System.setProperty("WebDriver.chrome.driver", new File("src/com/flip/Resources/chromedriver.exe").getAbsolutePath());
            driver = new ChromeDriver();
            driver.get(appurl);
            driver.manage().window().maximize();
        }
        else{
            System.setProperty("WebDriver.iexplorer.driver", new File("src/com/flip/Resources/IEDriverServer.exe").getAbsolutePath());
            driver = new InternetExplorerDriver();
            driver.get(appurl);
            driver.manage().window().maximize();
        }
    }

    public static void Closebrowser(){
        driver.close();
        driver.quit();
    }

    public static void Typeinfo(WebElement segment,String value){
        segment.sendKeys(value);
    }



    public static void Cleartypedinfo(WebElement segment){
        segment.clear();
    }

    public static void Clickafield(WebElement segment){
        segment.click();
    }

    public static void Dropdownselection1(WebElement segment, String valueinui){
        new Select(segment).selectByVisibleText(valueinui);
    }

    public static void Dropdownselection2(WebElement segment, int numberofdrop){
        new Select(segment).selectByIndex(numberofdrop);
    }

    public static void Dropdownselection3(WebElement segment, String valueofxpath){
        new Select(segment).selectByValue(valueofxpath);
    }

    public static void Popuphandle1(){
        Alert obj13 = driver.switchTo().alert();
        obj13.accept();
    }

    public static void Popuphandle2(){
        Alert popup = driver.switchTo().alert();
        popup.dismiss();
    }

    public static void Implicitwait(int time){
        driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
    }

    public static void Explicitwait1(WebElement segment, int time){
        WebDriverWait obj7 = new WebDriverWait(driver, time);
        obj7.until(ExpectedConditions.visibilityOf(segment));
    }

    public static void Explicitwait2(WebElement segment, int time){
        WebDriverWait obj8 = new WebDriverWait(driver,time);
        obj8.until(ExpectedConditions.elementToBeClickable(segment));
    }

    public static void Explicitwait3(WebElement segment, int time){
        WebDriverWait obj9 = new WebDriverWait(driver,time);
        obj9.until(ExpectedConditions.titleContains("value"));
    }

    public static void Navigate(String navig,WebElement segment){
        if(navig.equalsIgnoreCase("back")){
            driver.navigate().back();
        }
        else{
            driver.navigate().forward();
        }
    }

    public static void Capturescreenshot(String file) throws IOException{
        File obj10 = new File("src/com/flip/Screenshot").getAbsoluteFile();
        File obj11 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(obj11, new File(obj10+"/"+file+".png"));
    }

    ///storage comments

    public static String Capturetitle(){
        return driver.getTitle();
    }

    public static String Captureurl(){
        return driver.getCurrentUrl();
    }

    public static String Capturetypedtext(WebElement segment){
        return segment.getText();
    }

    public static String Captureclickvalue(WebElement segment){
        return segment.getAttribute("value");
    }

    public static String Capturedropselectedoption(WebElement segment){
        return new Select(segment).getFirstSelectedOption().getText();
    }

    public static List<WebElement> Capturedropalloption(WebElement segment){
        return new Select(segment).getOptions();
    }

    public static String Capturealertmessage(){
        Alert obj12 = driver.switchTo().alert();
        return obj12.getText();
    }

    public static boolean Checkisdisplayed(WebElement segment){
        try{
            segment.isDisplayed();
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public static boolean CheckisEnabled(WebElement segment){
        try{
            segment.isEnabled();
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    public static boolean CheckisSelected(WebElement segment){
        try{
            segment.isSelected();
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    public static boolean Checkisalertpresent(){
        try{
            Alert obj14 = driver.switchTo().alert();
            obj14.getText();
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    public static void Mutiwindow(WebElement segment){
        for(String childwindow:driver.getWindowHandles()){
            driver.switchTo().window(childwindow);
        }
    }

    public static void Mousehower(WebElement segment){
        Actions obj15 = new Actions(driver);
        obj15.moveToElement(segment).build().perform();
    }

    public static void Mouseclick(WebElement segment){
        Actions obj16 = new Actions(driver);
        obj16.click(segment).build().perform();
    }

    public static void Mousedoubleclick(WebElement segment){
        Actions obj17 = new Actions(driver);
        obj17.doubleClick(segment).build().perform();
    }

    public static void Mouserightclick(WebElement segment){
        Actions obj18 = new Actions(driver);
        obj18.contextClick(segment).build().perform();
    }

    public static void Mousedragdrop(WebElement source, WebElement target){
        Actions obj19 = new Actions(driver);
        obj19.dragAndDrop(source, target).build().perform();
    }

    public static void Keystrokes(Keys board, WebElement segment){
        segment.sendKeys(board);
    }

    public static void Iframego(WebElement segment){
        driver.switchTo().frame(segment);
    }

    public static void Iframeout(){
        driver.switchTo().defaultContent();
    }


}

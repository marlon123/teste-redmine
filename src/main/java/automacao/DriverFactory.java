package automacao;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.openqa.selenium.remote.BrowserType.CHROME;
import static org.openqa.selenium.remote.BrowserType.FIREFOX;

public class DriverFactory {
    private static WebDriver driver;

    private DriverFactory() {}

    public static WebDriver getDriver(String navegador){
        if(driver == null) {
            if (navegador.equalsIgnoreCase("firefox")){
                driver = new FirefoxDriver();
            }else if(navegador.equalsIgnoreCase("chrome")){
                driver = new ChromeDriver();
            }
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static WebDriver getDriver(){
        return driver;
    }

    public static void killDriver(){
        if(driver != null) {
            driver.quit();
            driver = null;
        }
    }
}

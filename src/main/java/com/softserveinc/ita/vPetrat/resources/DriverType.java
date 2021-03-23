package com.softserveinc.ita.vPetrat.resources;


import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public enum DriverType {

    CHROME("webdriver.chrome.driver","src/main/java/com/softserveinc/ita/vPetrat/resources/drivers/chromedriver.exe"),
    OPERA("webdriver.opera.driver", "src/main/java/com/softserveinc/ita/vPetrat/resources/drivers/operadriver.exe"),
    MOZILLA("webdriver.gecko.driver", "src/main/java/com/softserveinc/ita/vPetrat/resources/drivers/geckodriver.exe"),
    EDGE("webdriver.ie.driver", "src/main/java/com/softserveinc/ita/vPetrat/resources/drivers/msedgedriver.exe");

    private final String driverKey;
    private final String driverPath;

    DriverType(String driverKey, String driverPath) {
        this.driverKey = driverKey;
        this.driverPath = driverPath;
    }

    public String getDriverKey() {
        return driverKey;
    }

    public String getDriverPath() {
        return driverPath;
    }

    public static DriverType initDriverType(String browserName) {
         switch (browserName) {
             case "Chrome":
                 return CHROME;
             case "Opera":
                 return OPERA;
             case "Mozilla":
                 return MOZILLA;
             case "Edge":
                 return EDGE;
             default:
                 throw new IllegalArgumentException(browserName);
         }
    }

    public static RemoteWebDriver createRemoteDriverObject(String browserName) {
         switch (browserName) {
            case "Chrome" : return new ChromeDriver();
            case "Opera" : return new OperaDriver();
            case "Mozilla" : return new FirefoxDriver();
            case "Edge" : return new EdgeDriver();
            default :  throw new IllegalArgumentException(browserName);
        }
    }
}

package webTests.google;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {
    public static WebDriver getDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                // Автоматическое определение версии браузера и загрузка драйвера
                //WebDriverManager.chromedriver().setup();
                //return new ChromeDriver();
                // Указанное определение версии браузера и загрузка драйвера
                WebDriverManager.chromedriver().browserVersion("131.0.6778.86").setup();
                return new ChromeDriver();
            case "yandex":
                WebDriverManager.chromedriver().browserVersion("128").setup();
                ChromeOptions options = new ChromeOptions();
                options.setBinary("C:\\Users\\Anastasia\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
                return new ChromeDriver(options);
            // Для других браузеров
            /*case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            case "edge":
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();*/
            default:
                throw new IllegalArgumentException("Unknown browser: " + browser);
        }
    }
}

// При указании конкретного пути к вебдрайверам
    /*public static WebDriver getDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            case "yandex":
                System.setProperty("webdriver.chrome.driver", "D:\\WebDriver\\bin\\chromedriver128.exe");
                ChromeOptions options = new ChromeOptions();
                options.setBinary("C:\\Users\\Anastasia\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
                return new ChromeDriver(options);
            // Для других браузеров
            /*case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            case "edge":
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();*/
            /*default:
                throw new IllegalArgumentException("Unknown browser: " + browser);
        }
    }
}*/


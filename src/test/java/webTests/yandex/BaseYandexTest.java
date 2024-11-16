// Основные начальные методы начала тестирования

package webTests.yandex;

import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import webTests.pageObjects.ConstructorPage;
import webTests.pageObjects.LoginPage;
import webTests.pageObjects.RegistrationPage;
import java.util.concurrent.TimeUnit;

public class BaseYandexTest {
    protected WebDriver driver;
    protected RegistrationPage registrationPage;
    protected LoginPage loginPage;
    protected ConstructorPage constructorPage;
    protected final String URL = "https://stellarburgers.nomoreparties.site/";

    @Step("Настройка WebDriver и открытие страницы регистрации в Яндекс браузере")
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:\\WebDriver\\bin\\chromedriver128.exe");
        // Опции для Яндекс.Браузера
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Users\\Anastasia\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
        // Инициализируем драйвер
        driver = new ChromeDriver(options);
        driver.get(URL);
        registrationPage = new RegistrationPage(driver);
        loginPage = new LoginPage(driver);
        constructorPage = new ConstructorPage(driver);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Step("Завершение работы с WebDriver в Яндекс браузере")
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

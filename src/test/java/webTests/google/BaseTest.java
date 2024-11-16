// Основные начальные методы начала тестирования

package webTests.google;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import webTests.pageObjects.ConstructorPage;
import webTests.pageObjects.LoginPage;
import webTests.pageObjects.RegistrationPage;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver driver;
    protected RegistrationPage registrationPage;
    protected LoginPage loginPage;
    protected ConstructorPage constructorPage;
    protected final String URL = "https://stellarburgers.nomoreparties.site/";

    @Step("Настройка WebDriver и открытие страницы регистрации в Google Chrome браузере")
    @Before
    public void setUp() {
        WebDriverManager.chromedriver().driverVersion("130.0.6723.119").setup();
        driver = new ChromeDriver();
        driver.get(URL);
        registrationPage = new RegistrationPage(driver);
        loginPage = new LoginPage(driver);
        constructorPage = new ConstructorPage(driver);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Step("Завершение работы с WebDriver в Google Chrome браузере")
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}


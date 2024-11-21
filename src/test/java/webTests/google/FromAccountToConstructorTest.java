/* Переход из личного кабинета в конструктор.
Проверь переход по клику на «Конструктор» и на логотип Stellar Burgers
*/

package webTests.google;

import io.qameta.allure.Description;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Collection;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class FromAccountToConstructorTest extends BaseTest {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return BrowserParameters.getBrowserData(); // Вызов параметров из внешнего класса
    }

    // Конструктор для передачи параметров в BaseTest
    // Для вызова тестов в разных браузерах одной командой mvn clean test
    public FromAccountToConstructorTest(String browser) {
        super(browser);
    }

    private final String LOGIN_URL = "https://stellarburgers.nomoreparties.site/";
    private final String LOGIN_URL2 = "https://stellarburgers.nomoreparties.site/account/profile";

    // *Баг. После входа в аккаунт через клик по личному кабинету, после входа открывается url
    // страницы логина, а должен быть url главной страницы
    @Test
    @Description("Тестирование перехода из личного кабинета в конструктор по клику на «Конструктор»")
    public void testConstructorButtonFromPersonalCabinet() {
        loginPage.clickPersonalCabinetButton();
        loginPage.enterEmail("testuser@example.com");
        loginPage.enterPassword("password123");
        loginPage.submitLogin();
        assertEquals("URL после входа должен быть главной страницей", LOGIN_URL, driver.getCurrentUrl());
        loginPage.clickPersonalCabinetButton();
        assertEquals("URL после входа в аккаунт и повторного клика по кнопке «Личный кабинет» должен быть переход на страницу профиля", LOGIN_URL2, driver.getCurrentUrl());
        loginPage.clickConstructor();
        assertEquals("URL после клика по кнопке Конструктор из личного кабинета должен быть главной страницей", LOGIN_URL, driver.getCurrentUrl());
    }

    // *Баг. После входа в аккаунт через клик по личному кабинету, после входа открывается url
    // страницы логина, а должен быть url главной страницы
    @Test
    @Description("Тестирование перехода из личного кабинета в конструктор по клику на логотип Stellar Burgers")
    public void testConstructorButtonFromStellarBurgersLogo() {
        loginPage.clickPersonalCabinetButton();
        loginPage.enterEmail("testuser@example.com");
        loginPage.enterPassword("password123");
        loginPage.submitLogin();
        assertEquals("URL после входа должен быть главной страницей", LOGIN_URL, driver.getCurrentUrl());
        loginPage.clickPersonalCabinetButton();
        assertEquals("URL после входа в аккаунт и повторного клика по кнопке «Личный кабинет» должен быть переход на страницу профиля", LOGIN_URL2, driver.getCurrentUrl());
        loginPage.clickStellarBurgersLogo();
        assertEquals("URL после клика по логитипу Stellar Burgers должен быть главной страницей", LOGIN_URL, driver.getCurrentUrl());
    }
}

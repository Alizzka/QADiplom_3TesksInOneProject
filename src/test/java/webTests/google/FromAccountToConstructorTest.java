/* Переход из личного кабинета в конструктор.
Проверь переход по клику на «Конструктор» и на логотип Stellar Burgers
*/

package webTests.google;

import io.qameta.allure.Step;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class FromAccountToConstructorTest extends BaseTest {

    private final String LOGIN_URL = "https://stellarburgers.nomoreparties.site/";
    private final String LOGIN_URL2 = "https://stellarburgers.nomoreparties.site/account/profile";

    // *Баг. После входа в аккаунт через клик по личному кабинету, после входа открывается url
    // страницы логина, а должен быть url главной страницы
    @Test
    @Step("Тестирование перехода из личного кабинета в конструктор по клику на «Конструктор» в Google Chrome браузере")
    public void testConstructorButtonFromPersonalCabinetGoogle() {
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
    @Step("Тестирование перехода из личного кабинета в конструктор по клику на логотип Stellar Burgers в Google Chrome браузере")
    public void testConstructorButtonFromStellarBurgersLogoGoogle() {
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

/* Выход из аккаунта.
Проверь выход по кнопке «Выйти» в личном кабинете.
*/

package webTests.yandex;

import io.qameta.allure.Step;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class LogoutYandexTest extends BaseYandexTest {
    private final String LOGIN_URL = "https://stellarburgers.nomoreparties.site/";
    private final String LOGIN_URL2 = "https://stellarburgers.nomoreparties.site/account/profile";

    // *Баг. После входа в аккаунт через клик по личному кабинету, после входа открывается url
    // страницы логина, а должен быть url главной страницы
    @Test
    @Step("Тестирование выхода из аккаунта по кнопке «Выйти» в личном кабинете")
    public void testExitButtonYandex() {
        registrationPage.clickLoginAccountButton();
        loginPage.enterEmail("testuser@example.com");
        loginPage.enterPassword("password123");
        loginPage.submitLogin();
        assertEquals("URL после входа должен быть главной страницей", LOGIN_URL, driver.getCurrentUrl());
        loginPage.clickPersonalCabinetButton();
        assertEquals("URL после входа в аккаунт и повторного клика по кнопке «Личный кабинет» должен быть переход на страницу профиля", LOGIN_URL2, driver.getCurrentUrl());
        // Клик по кнопке "Выход"
        loginPage.clickExit();
    }
}
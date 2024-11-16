/* Вход.
Проверь:
- вход по кнопке «Войти в аккаунт» на главной,
- вход через кнопку «Личный кабинет»,
- вход через кнопку в форме регистрации,
- вход через кнопку в форме восстановления пароля.
*/

package webTests.yandex;

import io.qameta.allure.Step;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class LoginYandexTest extends BaseYandexTest {
    private final String LOGIN_URL = "https://stellarburgers.nomoreparties.site/login";

    @Test
    @Step("Тестирование входа по кнопке 'Войти в аккаунт' на главной странице")
    public void testLoginViaLoginAccountButtonYandex() {
        registrationPage.clickLoginAccountButton();
        loginPage.enterEmail("testuser@example.com");
        loginPage.enterPassword("password123");
        loginPage.submitLogin();
        assertEquals("URL после входа должен быть страницей логина", LOGIN_URL, driver.getCurrentUrl());
    }

    @Test
    @Step("Тестирование входа через кнопку 'Личный кабинет'")
    public void testLoginViaPersonalCabinetButtonYandex() {
        loginPage.clickPersonalCabinetButton();
        loginPage.enterEmail("testuser@example.com");
        loginPage.enterPassword("password123");
        loginPage.submitLogin();
        assertEquals("URL после входа должен быть страницей логина", LOGIN_URL, driver.getCurrentUrl());
    }

    @Test
    @Step("Тестирование входа через кнопку в форме регистрации")
    public void testLoginViaRegisterFormButtonYandex() {
        registrationPage.clickLoginAccountButton();
        registrationPage.scrollToRegisterButton();
        registrationPage.clickRegisterButton();
        loginPage.clickRegisterLoginButton();
        loginPage.enterEmail("testuser@example.com");
        loginPage.enterPassword("password123");
        loginPage.submitLogin();
        assertEquals("URL после входа должен быть страницей логина", LOGIN_URL, driver.getCurrentUrl());
    }

    @Test
    @Step("Тестирование входа через кнопку в форме восстановления пароля")
    public void testLoginViaForgotPasswordButtonYandex() {
        registrationPage.clickLoginAccountButton();
        registrationPage.scrollToRegisterButton();
        loginPage.clickRestorePasswordButton();
        loginPage.enterEmail("testuser@example.com");
        loginPage.enterRestorePassword("password123");
        loginPage.submitRestoreLogin();
        assertEquals("URL после входа должен быть страницей логина", LOGIN_URL, driver.getCurrentUrl());
    }
}

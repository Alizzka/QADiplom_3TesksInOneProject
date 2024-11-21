/* Вход.
Проверь:
- вход по кнопке «Войти в аккаунт» на главной,
- вход через кнопку «Личный кабинет»,
- вход через кнопку в форме регистрации,
- вход через кнопку в форме восстановления пароля.
*/

package webTests.google;

import io.qameta.allure.Description;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Collection;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class LoginTest extends BaseTest {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return BrowserParameters.getBrowserData(); // Вызов параметров из внешнего класса
    }

    // Конструктор для передачи параметров в BaseTest
    // Для вызова тестов в разных браузерах одной командой mvn clean test
    public LoginTest(String browser) {
        super(browser);
    }
    private final String LOGIN_URL = "https://stellarburgers.nomoreparties.site/login";

    @Test
    @Description("Тестирование входа по кнопке 'Войти в аккаунт' на главной странице")
    public void testLoginViaLoginAccountButton() {
        registrationPage.clickLoginAccountButton();
        loginPage.enterEmail("testuser@example.com");
        loginPage.enterPassword("password123");
        loginPage.submitLogin();
        assertEquals("URL после входа должен быть страницей логина", LOGIN_URL, driver.getCurrentUrl());
    }

    @Test
    @Description("Тестирование входа через кнопку 'Личный кабинет'")
    public void testLoginViaPersonalCabinetButton() {
        loginPage.clickPersonalCabinetButton();
        loginPage.enterEmail("testuser@example.com");
        loginPage.enterPassword("password123");
        loginPage.submitLogin();
        assertEquals("URL после входа должен быть страницей логина", LOGIN_URL, driver.getCurrentUrl());
    }

    @Test
    @Description("Тестирование входа через кнопку в форме регистрации")
    public void testLoginViaRegisterFormButton() {
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
    @Description("Тестирование входа через кнопку в форме восстановления пароля")
    public void testLoginViaForgotPasswordButton() {
        registrationPage.clickLoginAccountButton();
        registrationPage.scrollToRegisterButton();
        loginPage.clickRestorePasswordButton();
        loginPage.enterEmail("testuser@example.com");
        loginPage.enterRestorePassword("password123");
        loginPage.submitRestoreLogin();
        assertEquals("URL после входа должен быть страницей логина", LOGIN_URL, driver.getCurrentUrl());
    }
}


/* Регистрация.
Проверь:
- успешную регистрацию.
- ошибку для некорректного пароля. Минимальный пароль — шесть символов.
*/

package webTests.google;

import io.qameta.allure.Description;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Collection;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class RegistrationTest extends BaseTest {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return BrowserParameters.getBrowserData(); // Вызов параметров из внешнего класса
    }

    // Конструктор для передачи параметров в BaseTest
    // Для вызова тестов в разных браузерах одной командой mvn clean test
    public RegistrationTest(String browser) {
        super(browser);
    }

    @Test
    @Description("Проверка успешной регистрации пользователя")
    public void testSuccessfulRegistration() {
        registrationPage.clickLoginAccountButton();
        registrationPage.scrollToRegisterButton(); // Прокрутка до кнопки "Зарегистрироваться"
        registrationPage.clickRegisterButton();
        assertEquals("URL после регистрации должен быть страницей регистрации",
                "https://stellarburgers.nomoreparties.site/register",
                driver.getCurrentUrl());
        registrationPage.waitForNameInput(); // Нажатие на кнопку "Зарегистрироваться"
        registrationPage.enterName("Test User");
        registrationPage.waitForEmailInput();
        registrationPage.enterEmail("testuser@example.com");
        registrationPage.waitForPasswordInput();
        registrationPage.enterPassword("password123");
        registrationPage.clickRegisterButtonClick();
        assertEquals("URL после регистрации должен быть страницей логина",
                "https://stellarburgers.nomoreparties.site/login",
                driver.getCurrentUrl());
    }

    @Test
    @Description("Проверка регистрации с коротким паролем")
    public void testRegistrationWithShortPassword() {
        registrationPage.clickLoginAccountButton();
        registrationPage.scrollToRegisterButton(); // Прокрутка до кнопки "Зарегистрироваться"
        registrationPage.clickRegisterButton();
        assertEquals("URL после регистрации должен быть страницей регистрации",
                "https://stellarburgers.nomoreparties.site/register",
                driver.getCurrentUrl());
        registrationPage.waitForNameInput(); // Нажатие на кнопку "Зарегистрироваться"
        registrationPage.enterName("Test User");
        registrationPage.waitForEmailInput();
        registrationPage.enterEmail("testuser@example.com");
        registrationPage.waitForPasswordInput();
        registrationPage.enterPassword("12345");
        registrationPage.clickRegisterButtonClick();
        assertTrue("Сообщение об ошибке должно отображаться", registrationPage.isPasswordErrorDisplayed());
        assertEquals("Некорректный текст ошибки", "Некорректный пароль", registrationPage.getPasswordErrorText());
    }
}



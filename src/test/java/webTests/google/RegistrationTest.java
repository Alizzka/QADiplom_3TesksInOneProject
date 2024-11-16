/* Регистрация.
Проверь:
- успешную регистрацию.
- ошибку для некорректного пароля. Минимальный пароль — шесть символов.
*/

package webTests.google;

import io.qameta.allure.Step;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegistrationTest extends BaseTest {

    @Test
    @Step("Проверка успешной регистрации пользователя в Google Chrome браузере")
    public void testSuccessfulRegistrationGoogle() {
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
    @Step("Проверка регистрации с коротким паролем в Google Chrome браузере")
    public void testRegistrationWithShortPasswordGoogle() {
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


/* Выход из аккаунта.
Проверь выход по кнопке «Выйти» в личном кабинете.
*/

package webTests.google;

import io.qameta.allure.Description;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Collection;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class LogoutTest extends BaseTest {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return BrowserParameters.getBrowserData(); // Вызов параметров из внешнего класса
    }

    // Конструктор для передачи параметров в BaseTest
    // Для вызова тестов в разных браузерах одной командой mvn clean test
    public LogoutTest(String browser) {
        super(browser);
    }
    private final String LOGIN_URL = "https://stellarburgers.nomoreparties.site/";
    private final String LOGIN_URL2 = "https://stellarburgers.nomoreparties.site/account/profile";

    // *Баг. После входа в аккаунт через клик по личному кабинету, после входа открывается url
    // страницы логина, а должен быть url главной страницы
    @Test
    @Description("Тестирование выхода из аккаунта по кнопке «Выйти» в личном кабинете")
    public void testExitButton() {
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


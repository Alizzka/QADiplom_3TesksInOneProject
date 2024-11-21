/* Переход в личный кабинет.
Проверь переход по клику на «Личный кабинет»
*/

package webTests.google;

import io.qameta.allure.Step;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class AccountLoginTest extends BaseTest {

    private final String LOGIN_URL = "https://stellarburgers.nomoreparties.site/";
    private final String LOGIN_URL2 = "https://stellarburgers.nomoreparties.site/account/profile";

    // *Баг. После входа в аккаунт через клик по личному кабинету, после входа открывается url
    // страницы логина, а должен быть url главной страницы
    @Test
    @Step("Тестирование перехода в личный кабинет по клику на «Личный кабинет»")
    public void testLoginViaButtonPersonalCabinetIntoPersonalCabinetGoogle() {
        loginPage.clickPersonalCabinetButton();
        loginPage.enterEmail("testuser@example.com");
        loginPage.enterPassword("password123");
        loginPage.submitLogin();
        assertEquals("URL после входа должен быть главной страницей", LOGIN_URL, driver.getCurrentUrl());
        // Повторный клик по "Личный кабинет" после входа в аккаунт
        loginPage.clickPersonalCabinetButton();
        assertEquals("URL после входа в аккаунт и повторного клика по кнопке «Личный кабинет» должен быть переход на страницу профиля", LOGIN_URL2, driver.getCurrentUrl());
    }
}

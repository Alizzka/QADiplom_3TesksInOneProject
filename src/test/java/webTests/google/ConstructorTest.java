/* Раздел «Конструктор».
Проверь, что работают переходы к разделам:
- «Булки»,
- «Соусы»,
- «Начинки»
*/

package webTests.google;

import io.qameta.allure.Description;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Collection;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ConstructorTest extends BaseTest {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return BrowserParameters.getBrowserData(); // Вызов параметров из внешнего класса
    }

    // Конструктор для передачи параметров в BaseTest
    // Для вызова тестов в разных браузерах одной командой mvn clean test
    public ConstructorTest(String browser) {
        super(browser);
    }


    @Test
    @Description("Проверка перехода в раздел 'Соусы'")
    public void testSaucesSection() {
        constructorPage.clickSaucesSection();
        assertEquals("Соусы", constructorPage.getSaucesHeaderText());
    }

    @Test
    @Description("Проверка перехода в раздел 'Начинки'")
    public void testFillingsSection() {
        constructorPage.clickFillingsSection();
        assertEquals("Начинки", constructorPage.getFillingsHeaderText());
    }

    @Test
    @Description("Проверка перехода в раздел 'Булки'")
    public void testBunsSection() {
        constructorPage.clickSaucesSection();
        constructorPage.clickBunsSection();
        assertEquals("Булки", constructorPage.getBunsHeaderText());
    }
}

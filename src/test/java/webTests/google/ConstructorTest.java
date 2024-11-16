/* Раздел «Конструктор».
Проверь, что работают переходы к разделам:
- «Булки»,
- «Соусы»,
- «Начинки»
*/

package webTests.google;

import io.qameta.allure.Step;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ConstructorTest extends BaseTest {


    @Test
    @Step("Проверка перехода в раздел 'Соусы' в Google Chrome браузере")
    public void testSaucesSection() {
        constructorPage.clickSaucesSection();
        assertEquals("Соусы", constructorPage.getSaucesHeaderText());
    }

    @Test
    @Step("Проверка перехода в раздел 'Начинки' в Google Chrome браузере")
    public void testFillingsSection() {
        constructorPage.clickFillingsSection();
        assertEquals("Начинки", constructorPage.getFillingsHeaderText());
    }

    @Test
    @Step("Проверка перехода в раздел 'Булки' в Google Chrome браузере")
    public void testBunsSection() {
        constructorPage.clickSaucesSection();
        constructorPage.clickBunsSection();
        assertEquals("Булки", constructorPage.getBunsHeaderText());
    }
}

package unitTests;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import praktikum.Ingredient;
import praktikum.IngredientType;
import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class IngredientTest {

    @Test
    @Parameters({
            "SAUCE, ketchup, 10.5",
            "FILLING, lettuce, 5.0"
    })
    public void testIngredientProperties(IngredientType type, String name, float price) {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals(type, ingredient.getType());
        assertEquals(name, ingredient.getName());
        assertEquals(price, ingredient.getPrice(), 0.01);
    }
}

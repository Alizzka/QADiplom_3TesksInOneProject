package apiTests;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UserOrdersTest extends MethodsUserCreation {

    private MethodsUserCreation methodsUserLogin = new MethodsUserCreation();

    @Test
    @Step("Получение заказов авторизованного пользователя")
    public void getUserOrdersWithAuthorization() {
        // Генерация уникальных данных для пользователя
        String email = generateUniqueEmail();
        String password = generateUniquePassword();
        String name = generateUniqueName();
        // Создание нового пользователя
        Response createUserResponse = createUniqueUser(email, password, name);
        createUserResponse.then().statusCode(200);
        // Логин под созданным пользователем и получение токена авторизации
        Response loginResponse = loginWithUser(email, password, name);
        loginResponse.then().statusCode(200);
        // Извлечение accessToken из ответа
        String accessToken = loginResponse.jsonPath().getString("accessToken");
        // Создание заказа с токеном авторизации с использованием MethodsUserCreation
        Response orderResponse = MethodsUserCreation.createOrderWithIngredients(accessToken);
        // Логирование и проверка успешного создания заказа
        orderResponse.then().log().all();
        MethodsUserCreation.verifyOrderCreation(orderResponse);
        try {
            // Получение заказов авторизованного пользователя
            Response ordersResponse = MethodsUserCreation.getUserOrders(accessToken);
            // Проверка успешного получения заказов
            MethodsUserCreation.verifyUserOrdersRetrieval(ordersResponse);
        } finally {
            // Удаление пользователя по токену
            deleteUserByToken(accessToken);
        }
    }


    @Test
    @Step("Проверка создания заказа и получения списка заказов без авторизации")
    public void createOrderAndGetUserOrdersWithoutAuthorization() {
        // Попытка получения списка заказов без авторизации
        Response ordersResponse = MethodsUserCreation.getUserOrdersWithoutAuthorization();
        // Проверка что запрос без авторизации возвращает ошибку 401 Unauthorized
        MethodsUserCreation.verifyUnauthorizedResponse(ordersResponse);
    }
}

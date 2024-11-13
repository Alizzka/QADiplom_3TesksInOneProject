package apiTests;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.Test;

public class UserCreationTest extends MethodsUserCreation {

    private final MethodsUserCreation userActions = new MethodsUserCreation();

    @Test
    @Description("Создание уникального пользователя и удаление после проверки")
    @Step("Создание уникального пользователя")
    public void creationUniqueUser() {
        // Генерация случайных данных для пользователя
        String email = generateUniqueEmail();
        String password = generateUniquePassword();
        String name = generateUniqueName();
        // Создаем пользователя
        Response response = createUniqueUser(email, password, name);
        // Проверяем ответ и сохраняем токен для последующего удаления
        String accessToken = userActions.verifyUserCreation(response, email, name);
        // Можно использовать явно вызываемое удаление пользователя внутри теста
        deleteUserByToken(accessToken);
    }

    @Test
    @Description("Создание зарегистрированного пользователя и проверка на ошибку повторной регистрации")
    @Step("Создание пользователя, который уже зарегистрирован")
    public void createExistingUser() {
        // Генерация случайных данных для пользователя
        String email = generateUniqueEmail();
        String password = generateUniquePassword();
        String name = generateUniqueName();
        // Первый запрос на создание пользователя
        Response firstResponse = userActions.createUniqueUser(email, password, name);
        // Проверка успешного создания пользователя
        userActions.verifyUserCreationSuccess(firstResponse);
        // Сохраняем accessToken для последующего удаления пользователя
        accessToken = firstResponse.jsonPath().getString("accessToken");

        // Второй запрос на создание того же пользователя
        Response secondResponse = userActions.createUniqueUser(email, password, name);
        // Проверка ошибки при повторной регистрации
        userActions.verifyDuplicateUserError(secondResponse);
    }

    @Test
    @Description("Создание пользователя без пароля и проверка на ошибку")
    @Step("Создание пользователя без обязательного поля - password")
    public void createUserWithoutRequiredFieldPassword() {
        // Генерация случайных данных для пользователя без password
        String email = generateUniqueEmail();
        String name = generateUniqueName();
        // Создаем пользователя
        Response response = createUniqueUserWithoutPassword(email, name);
        // Проверяем, что создание пользователя без password завершилось ошибкой
        userActions.verifyUserCreationFailurePassword(response);
    }

    @Test
    @Description("Создание пользователя без email и проверка на ошибку")
    @Step("Создание пользователя без обязательного поля - email")
    public void createUserWithoutRequiredFieldEmail() {
        // Генерация случайных данных для пользователя без email
        String password = generateUniquePassword();
        String name = generateUniqueName();
        // Создаем пользователя
        Response response = createUniqueUserWithoutEmail(password, name);
        // Проверяем, что создание пользователя без email завершилось ошибкой
        userActions.verifyUserCreationFailureEmail(response, "Email, password and name are required fields");
    }

    @Test
    @Description("Создание пользователя без имени и проверка на ошибку")
    @Step("Создание пользователя без обязательного поля - name")
    public void createUserWithoutRequiredFieldName() {
        // Генерация случайных данных для пользователя без name
        String password = generateUniquePassword();
        String email = generateUniqueEmail();
        // Создаем пользователя
        Response response = createUniqueUserWithoutName(password, email);
        // Проверяем, что создание пользователя без name завершилось ошибкой
        userActions.verifyUserCreationFailureName(response, "Email, password and name are required fields");
    }
}



// Тесты без создания отдельного класса для методов
/*package apiTests;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UserCreationTest {

    private String accessToken; // Токен для удаления пользователя

    // Метод для удаления пользователя по токену
    private void deleteUserByToken(String token) {
        // Убираем "Bearer " из начала токена
        String cleanToken = token.replace("Bearer ", "");
        // Отправляем запрос на удаление пользователя
        Response deleteResponse = given()
                .header("Authorization", "Bearer " + cleanToken)  // Добавляем токен в заголовок
                .when()
                .delete("/api/auth/user");
        // Вывод тела ответа и кода
        System.out.println("Delete Response Code: " + deleteResponse.getStatusCode());
        System.out.println("Delete Response Body:\n" + deleteResponse.getBody().prettyPrint());
        // Проверка успешного удаления
        assertThat(deleteResponse.getStatusCode(), is(202));  // Ожидаем, что код ответа 200
        assertThat(deleteResponse.jsonPath().getBoolean("success"), is(true));  // Проверка поля success
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
    }

    @After
    public void tearDown() {
        // Удаление пользователя после каждого теста по токену, если токен был получен
        if (accessToken != null && !accessToken.isEmpty()) {
            deleteUserByToken(accessToken); // Вызов метода для удаления пользователя
        }
    }

    @Test
    @Step("Создание уникального пользователя")
    public void creationUniqueUser() {
        // Генерация случайных данных для пользователя
        String uniqueId = UUID.randomUUID().toString().substring(0, 6); // уникальный идентификатор
        String email = "user" + uniqueId + "@yandex.ru";
        String password = "pass" + uniqueId;
        String name = "User" + uniqueId;
        String body = String.format(
                "{\n" +
                        "  \"email\": \"%s\",\n" +
                        "  \"password\": \"%s\",\n" +
                        "  \"name\": \"%s\"\n" +
                        "}", email, password, name
        );
        // Создаем пользователя
        Response response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/api/auth/register");
        // Вывод тела и кода ответа на консоль в формате JSON с переносами строк
        System.out.println("Response Code: " + response.getStatusCode());
        System.out.println("Response body:\n" + response.getBody().prettyPrint());
        // Проверяем код ответа - 200
        assertThat(response.getStatusCode(), is(200));
        // Проверяем, что поле "success" равно true
        assertThat(response.jsonPath().getBoolean("success"), is(true));
        // Сохраняем accessToken для последующего удаления пользователя
        //accessToken = "Bearer " + response.jsonPath().getString("accessToken");
        // Для удаления по явному вызову метода удаления
        String accessToken = "Bearer " + response.jsonPath().getString("accessToken");
        // Проверяем, что "user.email" и "user.name" соответствуют ожиданиям
        assertThat(response.jsonPath().getString("user.email"), is(email));
        assertThat(response.jsonPath().getString("user.name"), is(name));
        // Проверяем, что "accessToken" и "refreshToken" не пустые
        assertThat(accessToken, not(isEmptyOrNullString()));
        assertThat(response.jsonPath().getString("refreshToken"), not(isEmptyOrNullString()));
        // Можно использовать явно вызываемое удаление пользователя внутри теста
        deleteUserByToken(accessToken);
    }

    @Test
    @Step("Создание пользователя, который уже зарегистрирован")
    public void createExistingUser() {
        // Генерация случайных данных для пользователя
        String uniqueId = UUID.randomUUID().toString().substring(0, 6);
        String email = "user" + uniqueId + "@yandex.ru";
        String password = "pass" + uniqueId;
        String name = "User" + uniqueId;
        String body = String.format(
                "{\n" +
                        "  \"email\": \"%s\",\n" +
                        "  \"password\": \"%s\",\n" +
                        "  \"name\": \"%s\"\n" +
                        "}", email, password, name
        );
        // Первый запрос на создание пользователя
        Response firstResponse = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/api/auth/register");
        // Вывод тела и кода первого ответа
        System.out.println("First Response Code: " + firstResponse.getStatusCode());
        System.out.println("First Response Body:\n" + firstResponse.getBody().prettyPrint());
        // Проверка успешной регистрации
        assertThat(firstResponse.getStatusCode(), is(200));
        assertThat(firstResponse.jsonPath().getBoolean("success"), is(true));
        // Сохраняем accessToken для последующего удаления пользователя
        accessToken = firstResponse.jsonPath().getString("accessToken");

        // Второй запрос на создание того же пользователя
        Response secondResponse = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/api/auth/register");
        // Вывод тела и кода второго ответа
        System.out.println("Second Response Code: " + secondResponse.getStatusCode());
        System.out.println("Second Response Body:\n" + secondResponse.getBody().prettyPrint());
        // Проверка что повторная регистрация вызывает ошибку 403 с сообщением "User already exists"
        assertThat(secondResponse.getStatusCode(), is(403));
        assertThat(secondResponse.jsonPath().getString("message"), equalTo("User already exists"));
    }

    @Test
    @Step("Создание пользователя без обязательного поля - пароль")
    public void createUserWithoutRequiredFieldPassword() {
        // Генерация случайных данных для пользователя без поля пароля
        String uniqueId = UUID.randomUUID().toString().substring(0, 6); // уникальный идентификатор
        String email = "user" + uniqueId + "@yandex.ru";
        String name = "User" + uniqueId;
        String body = String.format(
                "{\n" +
                        "  \"email\": \"%s\",\n" +
                        "  \"name\": \"%s\"\n" +
                        "}", email, name
        );
        // Создаем пользователя
        Response response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/api/auth/register");
        // Вывод тела и кода ответа на консоль в формате JSON с переносами строк
        System.out.println("First Response Code: " + response.getStatusCode());
        System.out.println("Response body:\n" + response.getBody().prettyPrint());
        // Проверяем код ответа - 403
        assertThat(response.getStatusCode(), is(403));
        // Проверяем что поле "success" равно false
        assertThat(response.jsonPath().getBoolean("success"), is(false));
        // Ожидаемое сообщение об ошибке
        String expectedMessage = "Email, password and name are required fields";
        // Проверяем правильное сообщение об ошибке
        assertThat(response.jsonPath().getString("message"), is(expectedMessage));
    }
}*/



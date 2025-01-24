package api;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.NonNull;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import requestSpecification.Specification;
import scheme.UserRegisterScheme;
import util.ApiNegative;
import util.ApiPositive;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.condition.JRE.JAVA_20;

@Tag("ApiTest")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@EnabledOnJre(value = JAVA_20)
public class RestApiTest {

    private final String valid = "/dataForRegisterUser/validUser.csv";
    private final String invalid = "/dataForRegisterUser/invalidUser.csv";
    private final String emailPassword = "[{index}] Email - {arguments} ";
    private final String onlyEmail = "[{index}] Email - {arguments}";
    private final Gson gson = new Gson();
    private final UserRegisterScheme userRegisterScheme = new UserRegisterScheme();
    private final Specification specification = new Specification();
    private final RequestSpecification baseSpecification = given()
            .spec(specification.setBasicSpecification("https://reqres.in/", "api/"));


    @Order(1)
    @Tag("Post")
    @ApiPositive(statusCode = "200", method = "POST, Register - successful")
    @DisplayName("В этом тест кейсе мы проверяем успешную регистрацию пользователя с валидными данными")
    @Timeout(value = 5000L, unit = TimeUnit.MILLISECONDS)
    @CsvFileSource(resources = valid, delimiter = ',', lineSeparator = "\n", numLinesToSkip = 1)
    @ParameterizedTest(name = emailPassword)
    void mustRegisterNewUserTest(@NonNull String email, String password) {

        baseSpecification
                .body(gson.toJson(userRegisterScheme.schema(email, password)))
                .when()
                .post("register")
                .then()
                .assertThat()
                .statusCode(200)
                .header("Content-Type", "application/json; charset=utf-8")
                .contentType(ContentType.JSON)
                .time(lessThan(2000L))
                .body(matchesJsonSchemaInClasspath("schema/schemaResponseRegisterUser.json"));
    }

    @Order(2)
    @Tag("Post")
    @ApiNegative(statusCode = "400", method = "POST, Register - unsuccessful")
    @DisplayName("В этом тест кейсе мы проверяем статус код 400, при регистрации нового пользователя из за отсутствия пароля")
    @Timeout(value = 5000L, unit = TimeUnit.MILLISECONDS)
    @CsvFileSource(resources = invalid, lineSeparator = "\n", numLinesToSkip = 1)
    @ParameterizedTest(name = onlyEmail)
    void mustNotRegisterNewUserTest(@NonNull String email) {

        baseSpecification
                .body(gson.toJson(userRegisterScheme.schemaNoPassword(email)))
                .when()
                .post("register")
                .then()
                .assertThat()
                .statusCode(400)
                .header("Content-Type", "application/json; charset=utf-8")
                .contentType(ContentType.JSON)
                .time(lessThan(2000L))
                .body("error", equalTo("Missing password"))
                .body(matchesJsonSchemaInClasspath("schema/errorResponseScheme.json"));
    }

    @Order(3)
    @Tag("Get")
    @ApiPositive(statusCode = "200", method = "GET, List users")
    @DisplayName("В этом тест кейсе мы проверяем что email пользователей имеет окончание @reqres.in")
    @Timeout(value = 5000L, unit = TimeUnit.MILLISECONDS)
    @Test
    void emailMustEndWith() {

        List<String> emailList = Arrays.asList(baseSpecification
                        .when()
                        .get("users?page=2").getBody().jsonPath().getList("data.email").toString()
                        .replace("[", "")
                        .replace("]", "")
                        .split(","));

        baseSpecification
                .when()
                .get("users?page=2")
                .then()
                .assertThat()
                .statusCode(200)
                .header("Content-Type", "application/json; charset=utf-8")
                .contentType(ContentType.JSON)
                .time(lessThan(2000L));

        for (String value : emailList) {
            assertTrue(value.endsWith("@reqres.in"));
        }
    }

    @Order(4)
    @Tag("Delete")
    @ApiPositive(statusCode = "204", method = "DELETE, Delete")
    @DisplayName("В этом тест кейсе мы проверяем удаление пользователя")
    @Timeout(value = 5000L, unit = TimeUnit.MILLISECONDS)
    @Test
    void mustDeleteUser() {
        baseSpecification
                .when()
                .delete("users/2")
                .then()
                .assertThat()
                .statusCode(204)
                .header("Content-Length", "0")
                .time(lessThan(2000L));
    }
}

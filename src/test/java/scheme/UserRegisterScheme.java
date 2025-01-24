package scheme;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jdk.jfr.Description;
import lombok.Value;

/**
 * Класс содержит метод со схемой в строковом представлении, возвращает json.
 *
 * @author Ivan
 * @version 1.0
 */
@Value
public class UserRegisterScheme {

    /**
     * Метод создает json объект из строки.
     *
     * @param email    - email пользователя
     * @param password - password пользователя
     * @return json - пользователя для регистрации
     */
    @Description("Метод создает json объект из строки")
    public JsonObject schema(
            final String email,
            final String password
    ) {
        final String jsonString = "{" +
                                  "email :" + email + "," +
                                  "password :" + password +
                                  "}";
        return (JsonObject) JsonParser.parseString(jsonString);
    }

    /**
     * Метод создает json объект из строки.
     *
     * @param email    - email пользователя
     * @return json - пользователя для регистрации
     */
    @Description("Метод создает json объект из строки")
    public JsonObject schemaNoPassword(final String email) {
        final String jsonString = "{" +
                                  "email :" + email +
                                  "}";
        return (JsonObject) JsonParser.parseString(jsonString);
    }
}

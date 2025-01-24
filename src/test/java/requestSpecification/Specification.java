package requestSpecification;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.Data;

/**
 * Класс с настройками для запроса.
 *
 * @author Ivan
 * @version 1.0
 */
@Data
public class Specification {

    RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();

    /**
     * Метод создает json объект из строки.
     *
     * @param basePage - стартовый адрес
     * @param basePath - путь
     * @return json - базовую спецификацию
     */
    public RequestSpecification setBasicSpecification(String basePage, String basePath) {
        requestSpecBuilder
                .setBaseUri(basePage)
                .setBasePath(basePath)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL);
        return requestSpecBuilder.build();
    }
}

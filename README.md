[![Java CI with Gradle](https://github.com/ivan3035789/RestAssuredJavaCore/actions/workflows/gradle.yml/badge.svg)](https://github.com/ivan3035789/RestAssuredJavaCore/actions/workflows/gradle.yml)

## _Задание 1 - Api Rest Assured_

<span style="color: blue">Ссылка на API</span>- [https://reqres.in](https://reqres.in)

## _Кейс 1:_

```gradle
Протестировать регистрацию пользователя в системе, необходимо создание 2-х тестов:
- Успешная регистрация с валидными данными
- Регистрация с ошибкой из-за отсутствия пароля и проверить, что статус-код в ответе 400
```

## _Кейс 2:_

```gradle
1. Получить список пользователей страницы
2. Убедиться, что email пользователей имеет окончание @reqres.in
```

## _Кейс 3:_

```gradle
- Удалить пользователя и проверить, что стутус-код 204
```

&#10071;В реализации необходимо использовать **request/response <span style="color: green">"specifications"**</span>

Обычный запуск тестов ввести команду:
```shell
./gradlew test
```

Для запуска тестов аннотированных <span style="color: yellow">@Tag("ApiTest")</span> ввести команду:
```shell
./gradlew test -PincludeTags="ApiTest"
```

Для запуска тестов аннотированных <span style="color: yellow">@Tag("Post")</span> ввести команду:
```shell
./gradlew test -PincludeTags="Post"
```

Для запуска тестов аннотированных <span style="color: yellow">@Tag("Get")</span> ввести команду:
```shell
./gradlew test -PincludeTags="Get"
```

Для запуска тестов аннотированных <span style="color: yellow">@Tag("Delete")</span> ввести команду:
```shell
./gradlew test -PincludeTags="Delete"
```


## Перечень используемых инструментов для автоматизации:

| **_Инструмент_**            | **_Описание_**                                                     |
|-----------------------------|--------------------------------------------------------------------|
| **_java_**                  | язык програмирования для написания тестов                          |
| **_Gradle_**                | система автоматической сборки                                      |
| **_rest-assured_**          | библиотека для тестирования API                                    |
| **_json-schema-validator_** | проверяет схему на соответствие                                    |
| **_gson_**                  | библиотека преобразует строку в json объект                        |
| **_lombok_**                | библиотека Java, которая позволяет сократить объём шаблонного кода |
| **_Git_**                   | система контроля версий                                            |
| **_GitHub_**                | для хранения тестов                                                |
| **_GitHub Action_**         | CI для непрерывной интеграции                                      |

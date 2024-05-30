# Spring Boot Starter

[![Java21](https://img.shields.io/badge/JAVA-21-blue.svg)](https://docs.oracle.com/en/java/)
[![Spring Boot v3](https://img.shields.io/badge/SpringBoot-6DB33F?style=flat-square&logo=Spring&logoColor=white)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=flat-square&logo=postgresql&logoColor=white)](https://www.postgresql.org)
[![Hibernate](https://img.shields.io/badge/Hibernate-59666C.svg?style=flat-square&logo=Hibernate&logoColor=white)](https://hibernate.org)
[![Maven](https://img.shields.io/badge/Apache%20Maven-C71A36.svg?style=flat-square&logo=Apache-Maven&logoColor=white)](https://maven.apache.org)

## Оглавление

- [Введение](#введение)
- [Функции](#функции)
- [Используемые технологии](#используемые-технологии)
- [Версия JDK](#версия-jdk)
- [Структура стартера](#структура-стартера)
- [Установка](#установка)
- [Конфигурация](#конфигурация)
- [Использование](#использование)

## Введение

Spring Boot Starter предназначен для упрощения настройки проекта Spring Boot, предоставляя основные конфигурации и зависимости для логирования HTTP запросов и ответов.

## Функции

- Легкая интеграция с проектами Spring Boot
- Полное логирование HTTP запросов и ответов
- Настраивается через `application.properties` или `application.yml`

## Используемые технологии

- Java 21
- Spring Boot 3.x
- PostgreSQL
- Hibernate
- Maven

## Версия JDK

Проект написан с использованием JDK (Java) версии 21.

## Структура стартера

- `spring-starter-logger` - Модуль стартера
- `config/*` - Конфигурационные классы
- `filter/*` - Классы фильтры
- `logger/*` - Классы логирования
- `test/filter/*` - Классы тестирования фильтров
- `test/logger/*` - Классы тестирования логирования

## Установка

Чтобы использовать этот стартер, добавьте следующую зависимость в ваш `pom.xml`:

```xml
<dependency>
    <groupId>org.example</groupId>
    <artifactId>spring-starter-logger</artifactId>
    <version>1.0</version>
</dependency>
```

## Конфигурация

Настройте стартер в файле `application.properties` или `application.yml`.

### application.properties

```properties
spring-starter-logger.enabled=true
```

### application.yml
```yml
spring-starter-logger:
  enabled: true
```

## Использование
После добавления и настройки этого стартера, будет включено автоматическое логирование всех HTTP запросов и ответов. Это логирование предоставляет подробную информацию о каждом запросе и ответе, включая:

- Метод HTTP запроса (GET, POST, PUT, DELETE и т.д.)
- URL запроса
- Заголовки запроса
- Тело запроса
- Статус ответа HTTP
- Заголовки ответа
- Тело ответа
- Время обработки

Такое логирование помогает отслеживать и анализировать взаимодействия между клиентом и сервером, что особенно полезно для отладки и мониторинга работы приложения.

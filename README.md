# Проект реализован на Kotlin по материалам книги "Аделекан И. - Kotlin. Программирование на примерах"

Особенности реализации, которые были для меня новыми:

## ControllerAdvice
Используется как обработчик исключений для ошибок сервера. Например UserControllerAdvice.kt в котором содержатся хэндлеры ошибок.
Возвращает ResponseEntity с кодами ошибки.

## Services
Методы сервиса анотированы классами исключений, вызываемыми в этих методах, например UserServiceImpl.kt

## Exceptions
Классы исключений собраны в один файл, нет необходимости помещать каждое исключение в свой класс. Пример UserExceptions.kt

## Security
Ограничение доступа реализовано применяя Spring Security и JSON Web Tokens (JWT)

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.5/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.5/maven-plugin/reference/html/#build-image)
* [Spring Security](https://docs.spring.io/spring-boot/docs/2.4.5/reference/htmlsingle/#boot-features-security)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.4.5/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.4.5/reference/htmlsingle/#boot-features-jpa-and-spring-data)

### Guides
The following guides illustrate how to use some features concretely:

* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)


---
# Wildfly приложение с поддержкой Zkoss веб-форм.

Веб-приложение позволяет:
 1. Просматривать список контактов.
 2. Просматривать список адресов
 3. Фильтровать список адресов по id-контакта и городу, в т.ч. доступен быстрый переход со страницы контактов. 
 4. Сгенерировать sample-данные для БД
 
Тэги: #javaee, #ejb, #hibernate, #wildfly, #zkoss, #maven

В проекте используется: JDK8, Local Wildfly, Local MySql DB

## Получение исходного кода и старт проекта 
 
- Получить исходный код проекта командой
 ```
 git clone https://github.com/KhrulSergey/sampleWildlfy
 ```

- Перейти в рабочую папку проекта
`cd ./sampleWildlfy`

- Создать подключение к БД и схему в БД
JNDI: `java:jboss/datasources/MySqlDS` в БД со схемой `sample`.
Пример параметра подключения  `jdbc:mysql://localhost:3306/sample`

При необходимости сменить JDNI на свои в файлах:
```
\sampleWildfly\ejb\src\main\java\com\khsa\javaee\init\InitializerBean.java

\sampleWildfly\ejb\src\main\resources\META-INF\persistence.xml
```

- Запустить сборку проекта командой
`mvn package`

- Скопировать сборку с релизом приложения в папку "deployments" сервера Wildfly
`cp ./ear/target C:/Program Files/Wildfly/standalone/deployments`
или деплой через Wildfly UI 
`http://localhost:9990/console/App.html#standalone-deployments`

## Доступ к веб-интерфейсу проекта
После деплоя проекта на сервере, будут доступны:
Главная
* http://localhost:8080/web/index.zul

Контакты
* http://localhost:8080/web/contactPage.zul

Адреса
* http://localhost:8080/web/addressPage.zul
* http://localhost:8080/web/address_page.zul?contactId=contactID

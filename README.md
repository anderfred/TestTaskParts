TestTask - Part-list,  JavaRush выполнил Egor Belkov  
 

Ссылка на проект https://github.com/anderfred/TestTaskParts

Используемые технологии:

 Maven
 Spring 5.0.9
 Hibernate 5.3.6
 Tomcat 8.5.34
 MySql server 8
 Thymeleaf 3.0.9
 Spring Boot 2.0.5
 Bootstrap
 jQuery
 Spring Data Jpa
 

При старте приложения spring boot инициализирует базу данных файлами schema.sql для создания структуры таблицы и data.sql для наполнения ее тестовыми данными, тоже самое можно сделать самому нажав кнопку "Заполнить БД тестовыми данными". Для более красивого отображения сайта был применен Bootstrap , за отрисовку таблицы отвечает DataTables плагин к jQuery. 

Тестировалось на Win10, openJdk 11 , MySql server 8.

Данные для подключения к БД
  DB:test 
  username:root
  password:root

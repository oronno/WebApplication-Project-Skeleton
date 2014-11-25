# Standard Web Application Project Skeleton
### Develop web application with Spring Web MVC, Spring Security, JPA/Hibernate + MySQL, Thymeleaf, Bootstrap, Gradle

Just clone this project from github and start developing your application functionality without wasting time on writing
configurations and library setup if you gonna use following technology stack:

1. Spring 4.0.5.RELEASE
2. Spring Security 3.2.4.RELEASE
3. Servlet 3.0
4. Thymeleaf 2.1.3
5. JPA/Hibernate 4.3.5
6. Bootstrap 3.3.0
7. Gradle 2.1
8. Joda Time 2.4
9. Tomcat 7.0.54 (Embedded)
10. Slf4j/Log4j 1.7.5
11. Junit 4.11
12. spring-test 4.0.5.RELEASE (Spring MVC Test Framework)
13. Mockito 1.9.5

Following things has been configured:

* Spring Security standard User and Role based security system
* Transaction support in service/dao layer
* Database connection pooling with enterprise-ready c3p0
* Bean Validation to validate model objects
* Internationalization(i18n) with MessageSource
* Templating framework (like Apache Tiles) to work with thymeleaf (Thymeleaf Layout Dialect)
* Efficiently serving static contents like image/css/js
* Prevent browsers from caching dynamic contents

**Project Done so far:** A standard web application with authentication (login/logout) system and User management (create/role assignment) system has been implemented.

## Quick start

First clone this project from github and navigate there from your command line/terminal.

**If you have gradle already installed**, just run `gradle build`
then you will find a **war** file in your projects **/build/libs/** folder. Deploy it in your favorite container e.g. tomcat.

**If you don't have gradle installed**, well, there is a awesome news for you! You don't need to download/install gradle to 
build this project! Here, [gradle wrapper](http://java.dzone.com/articles/use-gradle-wrapper-and-stop) is used to make your life easier ;)
All you have to do to run script, e.g. if you want to build this project, just execute following command from your terminal

* `./gradlew build ` (from Unix)
* `gradlew.bat build ` (from Windows)

This script will automatically determine your gradle installation or download & install gradle if required & then execute 
the gradle task, e.g. build war file for above command. To see the list of tasks available, use `./gradlew tasks` or `gradlew.bat tasks` 


## Database Connection

You need to create a database named "webapp" in your local MySQL database to work with this application.
Change corresponding user credential to access database in `persistence-mysql.properties`.  
**Note:** There is a `import.sql` file in classpath, which execute and insert a user 'admin' with password '123456' and proper roles
when hibernate.hbm2ddl.auto property is set to create-drop in applicationContext-jpa.xml.


## Quick development -> direct deploy app on embedded tomcat
Well, building project and deploying manually in app container sometimes become pain as these are repetitive work. To make 
your life easier, here embedded tomcat container has been integrated. Just type `gradle tomcatRunWar` in your command line 
and your project will build and run automatically on a tomcat container within a minute. You just have to go **http://localhost:8080/** from 
your browser to see it running. Cool...right? ;)

## Deploy in local Tomcat with JRebel Support
1. Install Tomcat 7 at `/usr/local/tomcat7`
2. Copy the script file from `config/catalina-jrebel.sh` in your `/usr/local/tomcat7/bin` directory
3. Make sure, you installed jrebel at `/usr/local/JRebel` directory
4. Run **gradle deployWar** in your source code which will copy the war file to tomcat's webapps folder
5. From tomcat7/bin, start tomcat server by `./catalina-jrebel.sh run`
6. Access application by browsing: [localhost:8080/javafest/](localhost:8080/javafest/)
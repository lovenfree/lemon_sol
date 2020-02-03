# Universal Accounting Service

## General Project Structure

```bash
├─┬ src/main/java                   # Main program for this project
│ ├─ uas/                           # Main class for launching java engine
│ ├─ uas.billcode/					# Endpoint, service, and db handling code for the bill code data
│ ├─ uas.corpcarduse/				# Endpoint, service, and db handling code for the credit card usage data
│ ├─ uas.categorizationcode/		# Endpoint, service, and db handling code for the categorization code data
├─┬ src/main/resources              # Resource for launching service, including sql migrations
│ ├─ application.yml                # properties for launching java engine
├─┬ src/test/java                   # Test Cases for this project 
│ ├─ uas.module/					# module tests for each endpoint
│ ├─ uas.unit/						# unit tests for all classes
├─┬ src/test/resources              # Resource for launching tests
|- build.gradle                     # Gradle Build Script. If you want to add dependency , update this file.
|- docker-compose.yml               # Docker script for MariaDB
|- gradlew                          # Gradle build run command
|- eclipse-java-google-style.xml	# code style configuration
|- Jenkinsfile						# pipeline configurations
```

Each `src/main/java/uas` endpoint-specific package contains at least one model (VO class), repository (db handler), restcontroller (http endpoint), 
and service (main business logic) class. 

## The original task

> Write an application that that polls the queue in IES(Import/Encryption Service) and imports the data
>
> The application can read the queue from IES.
> If successful, data in IES table/queue as processed.
> CC data is validated to be complete and errors are returned where appropriate.
> The database is created to map the encrypted CC number to employeeId.
> Data is imported into UAS and status is changed to NEW
>
> The code should be production level quality and it should be possible to run the application.
>
> Further, the code should demonstrate your level of proficiency in Test Driven Development.
>
> The technology to be used:
> - Spring boot 2.1.7.RELEASE
> - OpenJDK 1.8
> - Gradle 5.6 

## Decisions made

* Java 8
* CorpCardUseId is chosen to be Primary Key for the data (it's generated automatically by sequence).
* This will help demonstrate "corpCardUse already exists" kind of errors in API.
* Spring Framework stack chosen as the most popular one.
* MariaDB  database used as very easy to use, managed, and can change to MySQL or Postgres.

## To run the application locally

### To run on default ports

Default ports are `3306` (for MariaDB-local) and `9090` (for Spring Boot Embedded Tomcat)

0. Install OpenJDK  8

    For Windows,
    
        https://github.com/ojdkbuild/ojdkbuild

	For MacOS,

        1) https://sdkman.io/install
        2) sdk install java 8.0.222-zulu


1A. Install STS(Spring Tool Suits)

        https://spring.io/tools   This is option. If you want to use another tool, please use your favorite tool
        I have been developing by using STS
        

1B. Install plug-ins in Spring Tool Suits (egit and gradle-ide-pack)

        1) Help -> Install New Software click
        2) Add Button Click

        3) name : egit , url : http://download.eclipse.org/egit/updates
        4) Git integration for Eclipse click and install plug-in

        5) name: Gradle IDE Pack, url: https://nodeclipse.github.io/updates/gradle-ide-pack/
        6) Select: Gradle IDE Pack


1C. lombok install and Update Spring Tool Suits ini file.

        The lombok is library for auto generating getter / setter method in model class 

        For Window https://lee-mandu.tistory.com/369?category=715433  Restart STS after installing lombok

        For Mac http://codeomitted.com/setup-lombok-with-stseclipse-based-ide/
            1) Copy lombok.jar to /Applications/SpringToolSuite4.app/Contents/Eclipse/
            2) Edit /Applications/SpringToolSuite4.app/Contents/Eclipse/SpringToolSuite4.ini
            3) add following line at the end of the ini file
                -javaagent:/Applications/SpringToolSuite4.app/Contents/Eclipse/lombok.jar
            4) Restart Spring Tool Suite


2. Start MariaDB-local

        docker-compose up


3. After cloning current repository run the app

        ./gradlew clean bootRun

5. Optional - git scm install

        https://git-scm.com/      If you want to use another tool, please use your favorite tool 


6. Optional - Install MySQL Workbench

https://dev.mysql.com/downloads/file/?id=488575

NOTE: Database schema will be automatically created by Flyway.

7. Gradle Note:

Please always use gradle wrapper by ./gradlew instead of gradle
Gradle version is managed in gradle/wrapper/gradle-wrapper.properties



### To run unit tests

    ./gradlew clean test

Unit test report will be in `./build/reports/tests/index.html`

### To run integration tests

Following command line assumes that you are running MariaDB-Local on port 3306

    ./gradlew clean moduleTest
    
### To run unit tests and integration tests

Following command line assumes that you are running MariaDB-Local on port 3306

    ./gradlew clean test moduleTest

## REST API
### Swagger doc generation
swagger fox allows for RequestMapping properties and other annotations to generate living API documentation
https://springfox.github.io/springfox/docs/snapshot/

this creates the following url locally, localhost:9090/swagger-ui.html

### Health Check

    GET /health

HTTP Response `200 OK` considered as healthy

Example:

    curl -i -X GET http://localhost:9090/health
    HTTP/1.1 200 OK
    Server: Apache-Coyote/1.1
    Content-Type: text/plain;charset=UTF-8
    Content-Length: 2
    Date: Sat, 14 May 2016 09:30:24 GMT

    up

### Import transaction to UAS from IES

    GET /v1/corpcarduse

Returns the list of all processed corpCardUse.
Returns `204 NO CONTENT` if queue has no record to import (no 'new' status), `200 OK` if results present, and other standard HTTP response codes.

Example:

    curl -i -X GET http://localhost:9090/v1/corpcarduse
    HTTP/1.1 200 OK
    Server: Apache-Coyote/1.1
    Content-Type: application/json;charset=UTF-8
    Transfer-Encoding: chunked
    Date: Sat, 14 May 2016 09:54:28 GMT

[
    {
        "corpCardUseId": 3,
        "corpCardId": 1,
        "employeeId": 100001,
        "useDate": "20190322",
        "progStatCd": 100,
        "frchzNm": "요거프레소",
        "useAmt": 19500,
        "vatStat": "1",
        "crtId": 100,
        "crtDt": "20190930",
        "updtId": 100,
        "updtDt": "20190930"
    },
    {
        "corpCardUseId": 4,
        "corpCardId": 1,
        "employeeId": 100001,
        "useDate": "20190322",
        "progStatCd": 100,
        "frchzNm": "25시김밥",
        "useAmt": 12000,
        "vatStat": "2",
        "crtId": 100,
        "crtDt": "20190930",
        "updtId": 100,
        "updtDt": "20190930"
    }
]


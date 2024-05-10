<img src="./springBoot-icon.png" title="Spring Boot Icon" width="50"/> JAVA - SpringBoot 
=================


**Code Editor Used:** Intellij Community version <img src="./IntelliJ_IDEA_Icon.png" title="IntelliJ IDEA Icon" width="25"/>

**Pre-Requirement:** Java

**Used version:** 17

**App Initializer:** [Spring Boot Initializer](https://start.spring.io/) this helps to build basic structure for our application or build a starter pack.

**Build Tool:** Maven

**What is maven ?**  
Maven is build automation tool and external dependency injection.

**Official Website:** [Maven Repository ](https://mvnrepository.com/)  
- Maven gives a Lists all dependencies list in pom.xml where we can see and handle our project dependencies as we need.
- After adding new dependency reload project to auto download it.
- That maven handle it owns.

---
### Some command of MVN / maven:

1.  `mvn validate` - validate the packages and decencies of application / project

2.  `mvn compile` - Compiles the application
3.  `mvn test` - Run the test and validate test cases
4.  `mvn package` - This convert application to jar file that will help to share application and run on another pc’s using `java <package name . jar>`
5.  `mvn install` - Save all dependencies to `.m2/repository`folder for future use in another projects.
6.  `mvn clean` - clear the target folder or junk files from project.   


### IOC and Application Context:
1.  **IOC** (***Inversion of controller***)   
    \- it means we can’t create object of class we ask spring for object and spring provides it by using It.  
    \- Using IOC we containerize the application.  
    \- It autodetect the class with `@Component` and generates their `bean`.
 
2.  **Application Context** - IOC creates the objects of class but application context avails that object accessible through the application.

3. IOC stores that classes with **@Component** decorators.

4. IOC scans classes and stores register classes through the application.


### Annotations:

**annotations give extra information about class, method and field.**

1.  `@SpringBootApplication` -  
    \- It defines entry point of application provided by ___springBootFramework .___  
    \- It configures application, Enables autoconfiguration for application and starts Components scanning thought the project.   
    \- This annotation only applies on main class of project.

2.  `@Component` - Component helps to IOC to create its bean.

3.  `@RestController` - Defines controller of application

4.  `@GetMapping` - maps the get method of API
 
5. `@Autowired` - It injects the dependencies into respective class.
 
6. `@Bean` - It creates bean in IOC, but it is only applicable on functions not on classes.

7. `@RequestMappling` - Maps the API endpoint with some extra path.

8. `@RequestBody` - It get data from request and turn it to java object that can used in to application.

9. `@PathVariable` - It works ap params in java to get params from path.

10. `@Transactional` - It set block of code in transaction phase that work like if any error occurs then roll back all successful changes.

11. `@EnableTransactionManagement` - It allows in application to user`@Transactional` annotation. It used in main class of project.

12. `@JsonIgnore` - It Make entity key private by securing that key from response to API.

13. `@Transient` - The `@Transient` annotation tells serialization frameworks (like Jackson for JSON) to ignore a field when converting an object to a serialized format (e.g., JSON).

---

**REST API:- Representational state transfer application programming interfaces.**

**Note: Methods in a controller class should be public so that they can be accessed and invoked by the spring framework or external HTTP request.**

---

**ORM: It is technique to map Java objects to database tables.**

*   It allows developers to work with database using object-oriented programming concepts, making it easier to interact with relational databases.

### JPA: JAVA Persistence API

*   JPA is a way to achieve ORM, includes interfaces and annotations that you use in your java classes, requires a persistence provider (ORM tool) for implementation.
*   JPA is primarily designed for working with relational Databases
*   JPA is not used with non SQL databases

**Spring Data MongoDB: This is ORM tool for mongoDB to communicate between mongoDB and JAVA application.**

### Medium to Interact with Databases:

*   **Query Method DSL** - this is simple convenient way to create queries based on method naming conventions.
*   **Criteria API** - It offers a more dynamic and programmatic approach for building complex and custom queries.


### Lombok:

*   It aims to reduce the boilerplate code that developer have to write such as getters, setters, constructors and more...
*   It achieves this by generating code automatically during compilation based on annotations as follows:  
    1. `@Getter` - It generates getter for class entities.  
    2. `@Setter` - It generates setters for class entities.  
    3. `@NonNull` - validate entity before save that it have to be not null  
    4. `@DBRef` - It refers to another collection mention while declaration of entity**


### Transaction:

**Transaction helps to prevent false entries in the database during changes in happened on table or collection.**

**Transaction rollback the new changes if any exception happened during the transaction.**

1. **PlatformTransactionManager:**
   *   It is responsible for handle `transaction` in application
   *   It provides interfaces like `rollback` and `commit`.
   
2. **MongoTransactionManager:**
*   `PlatformTransactionManager` responsible for transaction action but `MongoTransactionManager` implements the events on mongoDB.
*   It provides interfaces like `commitTransaction`, `abortTransaction`, `closeConnection`

For Implement the Transaction in the application functions have to annotated as `@Transactional` and main class have annotated as `@EnableTransactionManagement`

After adding annotation, we also have to create a `bean` of `PlatformTransactionManager` to implement it.


### Spring Security:

*   Spring Security is the **customized framework** that used to handle `authentication and authorization` in spring boot application.

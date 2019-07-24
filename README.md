## Pentathlon Competition

The project uses:

* Java 8
* Spring Boot
* Gradle 5.4.x
* Lombok
* JUnit 4

Task description can be found in 'Task' folder.

Generated JavaDoc can be found in 'doc' folder. Class AthletesServiceImpl method calculateResults() implements facade design pattern. 

Test coverage can be found in 'coverage' folder.

### Build (make sure JDK and gradle are installed and added to the system environment variables)

Go to the project's root directory and run this command in the terminal:

```bash
gradlew clean build
```

### Run

```bash
gradlew bootRun
```

### Check

Visit endpoint: http://localhost:8080/api/leaderboard



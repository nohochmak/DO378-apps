# expense-restful-service project

-------------------------------------------
2023 Nov 29 - Dev Notes
Podman doesn't work for Quarkus 2.13 - the DO378 lab version or 3.2.6 versions.  Had to start docker instead.  
resources/import.sql 
hibernate_sequence had been renamed in Hibernate 6+ : https://thorben-janssen.com/sequence-naming-strategies-in-hibernate-6/
replaced with Expense_SEQ per migration instructions.
-------------------------------------------

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```
./mvnw quarkus:dev
```

## Packaging and running the application

The application can be packaged using `./mvnw package`.
It produces the `expense-restful-service-1.0-SNAPSHOT-runner.jar` file in the `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

The application is now runnable using `java -jar target/expense-restful-service-1.0-SNAPSHOT-runner.jar`.

## Creating a native executable

You can create a native executable using: `./mvnw package -Pnative`.

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: `./mvnw package -Pnative -Dquarkus.native.container-build=true`.

You can then execute your native executable with: `./target/expense-restful-service-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image.
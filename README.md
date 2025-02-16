# quarkus-bank-contracts

This project was created for learning quarkus as goal.
The scope of project is a contract of credit bank simplified 

The main goal is create an implementation of each common integration made in this type of system.

Objectives:
- Create a CRUD 
- Database integration
- Client HTTP request
- Schedule some process with cron
- Consume from kafka topic
- Produce to kafka topic
- Write in DB the DLT events
- Send an email


## System Diagram 
<a href="https://drive.google.com/file/d/1yzobVBsF3mvwEApqZeG8SiMfSSJdX7gU/view?usp=sharing">Draw.io diagram</a> 

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./gradlew quarkusDev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

#### Test Containers - DevMode

It is recommended to install and run Podman or docker on the developer machine to start application in dev mode or run integration tests, either use testContainers 

###  <a href="https://podman.io/docs/installation">Podman Install guide</a>

## Packaging and running the application

The application can be packaged using:

```shell script
./gradlew build
```

It produces the `quarkus-run.jar` file in the `build/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `build/quarkus-app/lib/` directory.

The application is now runnable using `java -jar build/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./gradlew build -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar build/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./gradlew build -Dquarkus.native.enabled=true
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./gradlew build -Dquarkus.native.enabled=true -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./build/quarkus-bank-contracts-1.0.0-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/gradle-tooling>.

## Related Guides

- Hibernate ORM ([guide](https://quarkus.io/guides/hibernate-orm)): Define your persistent model with Hibernate ORM and Jakarta Persistence
- REST ([guide](https://quarkus.io/guides/rest)): A Jakarta REST implementation utilizing build time processing and Vert.x. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on it.
- REST Client ([guide](https://quarkus.io/guides/rest-client)): Call REST services
- JDBC Driver - PostgreSQL ([guide](https://quarkus.io/guides/datasource)): Connect to the PostgreSQL database via JDBC



## Running in WSL 2 + Podman

If you are using Testcontainers, you might need to configure it to use Podman. You can do this by setting the DOCKER_HOST environment variable to point to the Podman socket. Add the following to your environment variables:
```
export DOCKER_HOST=unix:///run/user/$(id -u)/podman/podman.sock
```

Check Docker Configuration File: Ensure that the Docker configuration file exists at /home/bruno/.docker/config.json. If it does not exist, you can create it by running:
```
mkdir -p /home/bruno/.docker
touch /home/bruno/.docker/config.json
```
Add this content
```
echo '{
  "auths": {},
  "HttpHeaders": {
    "User-Agent": "Docker-Client/20.10.7 (linux)"
  }
}' > /home/bruno/.docker/config.json
```

Set Environment Variable: If you have a custom Docker authentication configuration, you can set the DOCKER_AUTH_CONFIG environment variable to point to your configuration file:

```
export DOCKER_AUTH_CONFIG=/home/bruno/.docker/config.json
```


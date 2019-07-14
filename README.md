# jee-keycloak-microprofile-quickstart
JEE Wildfly Keycloak Microprofile REST Swagger Quickstart

## Features
This is a fully configured example microservice that uses:
- JEE [Wildfly](https://wildfly.org/) 16.0.0.Final
- Uses [wildfly-keycloak-microprofile](https://github.com/fuinorg/wildfly-keycloak-microprofile) Docker image
- [Keycloak](https://www.keycloak.org/) (Identity and Access Management)
- [PostgreSQL](https://www.postgresql.org/) Database (used for Keycloak and the Microservice)
- [pgAdmin4](https://www.pgadmin.org/) (Admin GUI for PostgreSQL)
- [Swagger UI](https://swagger.io/tools/swagger-ui/)
- [Jaeger](https://www.jaegertracing.io/) (End-to-end Distributed Tracing)
- [Docker](https://www.docker.com/) (Container built using [docker-maven-plugin](https://github.com/fabric8io/docker-maven-plugin))

The microservice has:
- [JAX-RS](https://projects.eclipse.org/projects/ee4j.jaxrs) Microservice (REST API)
- [Eclipse MicroProfile OpenTracing](https://github.com/eclipse/microprofile-opentracing)
- [Eclipse MicroProfile Fault Tolerance](https://github.com/eclipse/microprofile-fault-tolerance)
- Services secured with Keycloak OAUTH (See [keycloak.json](src/main/webapp/WEB-INF/keycloak.json))
- Java security annotations like **@RolesAllowed**
- [Open API](https://swagger.io/docs/specification/about/) (See [api.yaml](/src/main/webapp/api.yaml))
- Java Persistence API (JPA) configured for PostgreSQL (See [persistence.xml](src/main/resources/META-INF/persistence.xml))
- [Apache DeltaSpike Data Module](https://deltaspike.apache.org/documentation/data.html)
- [CORS Filter](src/main/java/org/fuin/examples/jkmq/service/StaticCorsFilter.java)
- [JSON-B Resolver](src/main/java/org/fuin/examples/jkmq/service/JsonbResolver.java)

Other features:
- Local developer testing with [Chrome in a Docker container](docker-compose-chrome.md) (No ports are mapped in the [docker-compose.yml](docker-compose.yml)) 

**TODO**
- *FEATURE BROKEN* (See [ELY-1705](https://issues.jboss.org/browse/ELY-1705)) Protect the Wildfly Management Console with Keycloak
- [Eclipse MicroProfile Health](https://github.com/eclipse/microprofile-health) (Depends on previous feature as health is supplied via management console port)
- [Eclipse Microprofile Metrics](https://github.com/eclipse/microprofile-metrics)

## Getting started
The following instructions were only tested on [Lubuntu](https://lubuntu.net/) Linux. Windows is not supported.

### Create file with passwords
Create a file named '.env' in the same directory as the [docker-compose.yml](docker-compose.yml) with the following content:

```
postgresRootPassword=<your-password>
postgresPassword=<another-password>
keycloakUser=admin
keycloakPassword=<one-more-password>
```

### Build the microservice
The Java microservice is built locally using the [Maven Wrapper](https://github.com/takari/maven-wrapper).
**CAUTION:** Requires Java 1.8 to build (1.9+ is currently not supported).

```
./mvnw clean package
```

You should now be able to see the new Docker image:

```
docker images
REPOSITORY                                     TAG                 IMAGE ID            CREATED             SIZE
fuinorg/jee-keycloak-microprofile-quickstart   0.2.0-SNAPSHOT      xxxxxxxxxxxx        16 hours ago        658 MB
fuinorg/jee-keycloak-microprofile-quickstart   latest              xxxxxxxxxxxx        16 hours ago        658 MB
```

### Start Docker Compose
Run Docker Compose to start all parts of the application: 

```
docker-compose up
```

### Use a Chrome browser in a Docker container
You can access the application only **inside** the Docker private network as no ports are mapped to the host.
This is possible by using a Chrome browser that is running inside a Docker container.
See [docker-compose-chrome.md](docker-compose-chrome.md) for instructions on how to start the Chrome Docker container.

### Stop the application
Press `<ctrl><c>` on the console

### Remove Docker Compose files
Run Docker Compose to remove all parts of the application:

```
docker-compose rm
```


### Applications
You can connect via the Chrome browser to the following applications:

* Swagger UI: [http://jkmq-swagger-ui:8080/](http://jkmq-swagger-ui:8080/)
    * Use the "Authorize" button to connect to KeyCloak
    * The *client-id* to use is **swagger-ui**

* Keycloak: [http://jkmq-keycloak:8080/auth/admin/master/console/](http://jkmq-keycloak:8080/auth/admin/master/console/)
     * KeyCloak administrator
         * User: ${keycloakUser}
         * Password: ${keycloakPassword}

* Example Service: [http://jkmq-service:8080/jkmq-service/api/](http://jkmq-service:8080/jkmq-service/api/)
     * See [keycloak-security-data-import.json](keycloak-security-data-import.json) for demo security setup
     * User with service role "admin" (Not allowd to run "/persons/*")
         * User: jane
         * Password: abc
     * User with service role "user" (Not allowed to run "/admin/hello")
         * User: john
         * Password: abc

* Jaeger: [http://jkmq-jaeger:16686](http://jkmq-jaeger:16686)

* pgAdmin4: [http://jkmq-pgadmin4](http://jkmq-pgadmin4)
    * Login
       * Email: admin@localhost
       * PW: ${postgresPassword}
    * Database
       * Host: jkmq-postgres
       * Port: 5432
       * Name: postgres
       * USer: postgres
       * Password: ${postgresPassword})

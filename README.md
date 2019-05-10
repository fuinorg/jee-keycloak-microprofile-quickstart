# jee-keycloak-microprofile-quickstart
JEE Wildfly Keycloak Microprofile REST Swagger Quickstart

This is a fully configured example microservice that uses:
- JEE [Wildfly](https://wildfly.org/) 14.0.1.Final
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

Other features:
- Local developer testing using nginx as reverse proxy and project specific local service domains in 'etc/hosts'.

**TODO**
- *FEATURE BROKEN* (See [ELY-1705](https://issues.jboss.org/browse/ELY-1705)) Protect the Wildfly Management Console with Keycloak
- [Eclipse MicroProfile Health](https://github.com/eclipse/microprofile-health) (Depends on previous feature as health is supplied via management console port)
- [Eclipse Microprofile Metrics](https://github.com/eclipse/microprofile-metrics)

## Getting started
The following instructions were only tested on [Lubuntu](https://lubuntu.net/) Linux. Windows is not supported.

### Install nginx and add host entries
Execute the following script to install [nginx](https://www.nginx.com/) as reverse proxy and add some names to "/etc/hosts":

```
./install-and-configure-nginx.sh
```

### Create file with passwords
Create a file named '.env' in the same directory as the [docker-compose.yml](docker-compose.yml) with the following content:

```
postgresRootPassword=<your-password>
postgresPassword=<another-password>
keycloakUser=admin
keycloakPassword=<one-more-password>
```

### Start Docker Compose
Run Docker Compose to start all parts of the application: 

```
docker-compose -f ./docker-compose.yml up
```

### Stop the application
Press `ctrl><c>` on the console

### Remove Docker Compose files
Run Docker Compose to remove all parts of the application:

```
docker-compose rm
```


### Applications mapped via nginx reverse proxy:
You can connect via your browser to the following applications (all on port 8080):
* Swagger UI: http://jkmq-swagger-ui:8080/
    * Use the "Authorize" button to connect to KeyCloak
    * The *client-id* to use is **swagger-ui**
* Keycloak: http://jkmq-keycloak:8080/auth/admin/master/console/
     * KeyCloak administrator
         * User: ${keycloakUser}
         * Password: ${keycloakPassword}
* Example Service: http://jkmq-service:8080/jkmq-service/api/
     * See [keycloak-security-data-import.json](keycloak-security-data-import.json) for demo security setup
     * User with service role "admin" (Not allowd to run "/persons/*")
         * User: jane
         * Password: abc
     * User with service role "user" (Not allowed to run "/admin/hello")
         * User: john
         * Password: abc

### Additional applications (not mapped via nginx reverse proxy):
You can connect via your browser to the following applications on their specific port:
* Jaeger: http://jkmq-jaeger:16686
* pgAdmin4: http://jkmq-pgadmin4:8084
    * Login
       * Email: admin@localhost
       * PW: ${postgresPassword}
    * Database
       * Host: jkmq-postgres
       * Port: 5432
       * Name: postgres
       * USer: postgres
       * Password: ${postgresPassword})

NUEVO PROYECTO!!!!

Herramientas nesesarias para corre la aplicacion son:

1.- Docker y docker compose
2.- Intellij 2021
3.- Java 11
4.- Gradle (Opcional pero es para un mejor desempeño)

Pasos para ejecutar la aplicacion

Cambiar la ip del host postgresql por la de tu maquina (ip local)
En el archivo application.yml, la ruta es: /infraestructure/web/micronaut/src/main/resources/application.yml

Ejemplo:


    customdatabase:
        host: "jdbc:postgresql://192.168.0.4:5432/examen" <--- aqui cambiar por la ip local de tu maquina

En el archivo docker-compose.yml, la ruta es: /docker-compose.yml
    
    services:
        web:
            build: .
            ports:
              - "8080:8080"
            environment:
              JDBC_URL: jdbc:postgresql://192.168.0.4:5432/examen <--- aqui cambiar por la ip local de tu maquina


1.- ./gradlew :infraestructure:web:micronaut:clean

2.- ./gradlew :infraestructure:web:micronaut:build

3.- docker-compose build (ejecutar solo una vez para crear la imagen)

(OPCIONAL) ELIMINAR IMAGEN DOCKER - Si hiciste algun ajuste en el codigo y ya existe la imagen, hay que eliminarla

4.- docker-compose up -d (para iniciar los servicios)

(OPCIONAL) docker-compose down (para dar de baja los servicios)

6.- ./gradlew :infraestructure:database:jooq:tables:flywayClean

7.- ./gradlew :infraestructure:database:jooq:tables:flywayMigrate

En caso de correr la aplicacion desde el intellij hacer lo siguiente:

Construir (Build) la apliacion dando click derecho en el archivo micronaut (PATH: /infraestrusture/web/micronaut)

Levantar algun servicio de postgres 14 o hacer lo siguiente:
1.- docker-compose build (ejecutar solo una vez para crear la imagen)

2.- docker-compose up -d (para iniciar los servicios)

Y listo puede probar la siguiente url:
http://localhost:8080/company/genders


API REST - Uris de ejemplo

GET - http://localhost:8080/company/genders

GET - http://localhost:8080/company/jobs

POST - http://localhost:8080/company/employees - EJERCICIO 1

Ejemplo body json
`{
"gender_id": 1,
"job_id": 1,
"name": "hector123",
"last_name": "alvarez",
"birthdate": "2020-07-14"
}`

GET - http://localhost:8080/company/employees

GET - http://localhost:8080/company/employees?idjob=1 - EJERCICIO 3

POST - http://localhost:8080/company/employees/bitacora - EJERCICIO 2

Ejemplo body json
`{
    "employee_id": 1,
    "worked_hours": 11,
    "worked_date": "2022-04-19"
}`

GET - http://localhost:8080/company/employees/bitacora

GET - http://localhost:8080/company/employees/total-payment?id=2&start=2022-04-14&end=2022-04-18 - EJERCICIO 5

GET - http://localhost:8080/company/employees/bitacora/total-hours?id=1&start=2022-04-14&end=2022-04-14 - EJERCICIO 4


## Micronaut 3.4.3 Documentation

- [User Guide](https://docs.micronaut.io/3.4.3/guide/index.html)
- [API Reference](https://docs.micronaut.io/3.4.3/api/index.html)
- [Configuration Reference](https://docs.micronaut.io/3.4.3/guide/configurationreference.html)
- [Micronaut Guides](https://guides.micronaut.io/index.html)
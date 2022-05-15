NUEVO PROYECTO!!!!

1.- ./gradlew :infraestructure:web:micronaut:clean

2.- ./gradlew :infraestructure:web:micronaut:build

3.- docker-compose build (ejecutar solo una vez para crear la imagen)

(OPCIONAL) ELIMINAR IMAGEN DOCKER - Si hiciste algun ajuste en el codigo y ya existe la imagen, hay que eliminarla

4.- docker-compose up -d (para iniciar los servicios)

(OPCIONAL) docker-compose down (para dar de baja los servicios)

6.- ./gradlew :infraestructure:database:jooq:tables:flywayClean

7.- ./gradlew :infraestructure:database:jooq:tables:flywayMigrate

Para ejecutar la aplicacion, es necesario contar con: 

1.- docker y docker-compose instalado.

Una vez instalado lo necesario, dentro de la carpeta del proyecto 
(ubicado en la raiz del proyecto), ejecutamos los siguientes comandos de Gradle.


1.- gradle clean


2.- gradle build

Antes de iniciar los servicios, modificar el siguien archivo "docker-compose.yml", ubicado en la carpeta rai


Al abrirlo, cambiar la siguiente linea de la "IP" por tu "IP local" (la obtienes por medio de la consola con ifconfig o ipconfig)
* JDBC_URL: jdbc:postgresql://192.168.0.4:5432/examen

Guardamos y continuamos con los comandos

2.- gradle task dockerfile


3.- gradle task dockerBuild


4.- docker-compose up -d (comando para iniciar los servicios)


5.- docker-compose stop (comando para detener los servicios)

Y listo puede probar la siguiente url:
http://localhost:8080/company/gender/list

Si desea ejecutar el proyecto desde un IDE como intellij se necesita primero
levantar un servicio de base datos POSTGRESQL 14.2.
Una vez iniciado postgresql, ejecutar en linea de comando:

1.- Cambiar la ip en el archivo src/main/resources/application.yml 
por la de su ip local, buscar la siguiente linea:

* url: ${JDBC_URL:`jdbc:postgresql://192.168.0.4:5432/examen`} <--- aqui cambiar la ip por la de la instancia del contenedor de posgresql

2.- listo, ejecutar la aplicacion



API REST - Uris de ejemplo

GET - http://localhost:8080/company/gender/list


GET - http://localhost:8080/company/job/list


GET - http://localhost:8080/company/employee/list


GET - http://localhost:8080/company/worker/list


GET - http://localhost:8080/company/payments?id=1&start=2022-04-12&end=2022-04-16


GET - http://localhost:8080/company/hours?id=1&start=2022-04-12&end=2022-04-16

POST - http://localhost:8080/company/employee


Ejemplo body:
{
"gender_id": 1,
"job_id": 1,
"name": "hector23",
"last_name": "alvarez2",
"birthdate": "1990-07-14"
}

POST- http://localhost:8080/company/worker

Ejemplo body:
{
"employee_id": 1,
"worked_hours": 9,
"worked_date": "2022-04-18"
}

## Micronaut 3.4.3 Documentation

- [User Guide](https://docs.micronaut.io/3.4.3/guide/index.html)
- [API Reference](https://docs.micronaut.io/3.4.3/api/index.html)
- [Configuration Reference](https://docs.micronaut.io/3.4.3/guide/configurationreference.html)
- [Micronaut Guides](https://guides.micronaut.io/index.html)
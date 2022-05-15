FROM openjdk:17-alpine AS myexamen
WORKDIR /home/app
COPY infraestructure/web/micronaut/build/docker/main/layers/libs /home/app/libs
COPY infraestructure/web/micronaut/build/docker/main/layers/classes /home/app/classes
COPY infraestructure/web/micronaut/build/docker/main/layers/resources /home/app/resources
COPY infraestructure/web/micronaut/build/docker/main/layers/application.jar /home/app/application.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/home/app/application.jar"]

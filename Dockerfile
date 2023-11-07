FROM openjdk:17

COPY target/Tourism.jar .

EXPOSE 8080

ENTRYPOINT ["java", "-jar","Tourism.jar"]
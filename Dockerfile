FROM maven:latest as build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:21-jdk
VOLUME /tmp
COPY --from=build /app/target/springboot-gestao-atendimentos-1.0.jar .
ENTRYPOINT ["java","-jar","springboot-gestao-atendimentos-1.0.jar"]
# Fase de build - Maven
FROM maven:eclipse-temurin-17 AS build
WORKDIR /app

# Copiar os arquivos do projeto (já clonados)
COPY . .

# Construir o projeto (sem rodar os testes)
RUN mvn clean package -DskipTests

# Fase final - Imagem com o Java
FROM eclipse-temurin:17-jdk
VOLUME /tmp

# Copiar o arquivo JAR gerado na fase de build
COPY --from=build /app/target/springboot-ouvidoria-1.0.jar .

# Definir o comando de entrada para rodar o JAR
ENTRYPOINT ["java", "-jar", "springboot-ouvidoria-1.0.jar"]
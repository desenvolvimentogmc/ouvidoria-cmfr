# Fase de build - Maven
FROM maven:latest as build
WORKDIR /app

# Copiar os arquivos do projeto (já clonados)
COPY . .

# Receber a variável de ambiente BRANCH_NAME (ou um valor padrão)
ARG BRANCH_NAME=main

# Alterar para a branch especificada (caso necessário)
RUN git checkout $BRANCH_NAME

# Construir o projeto (sem rodar os testes)
RUN mvn clean package -DskipTests

# Fase final - Imagem com o Java
FROM openjdk:21-jdk
VOLUME /tmp

# Copiar o arquivo JAR gerado na fase de build
COPY --from=build /app/target/springboot-ouvidoria-1.0.jar .

# Definir o comando de entrada para rodar o JAR
ENTRYPOINT ["java", "-jar", "springboot-ouvidoria-1.0.jar"]
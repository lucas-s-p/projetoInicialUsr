# Etapa de construção
FROM ubuntu:latest AS build

# Atualiza os pacotes e instala o OpenJDK 17 e o Maven
RUN apt-get update && apt-get install -y openjdk-17-jdk maven

# Copia todo o conteúdo do diretório atual para o contêiner
COPY . .

# Compila o projeto utilizando Maven
RUN mvn clean install

# Etapa de execução
FROM openjdk:17-jdk-slim

# Expõe a porta 8080
EXPOSE 8080

# Copia o arquivo JAR gerado na etapa de construção para o contêiner de execução
COPY --from=build /target/usrfacil-0.0.1-SNAPSHOT.jar /app/usrfacil-0.0.1-SNAPSHOT.jar

# Define o ponto de entrada para executar o aplicativo
ENTRYPOINT ["java", "-jar", "/app/usrfacil-0.0.1-SNAPSHOT.jar"]

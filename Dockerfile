FROM maven:3.8.4-openjdk-17-slim AS build

# Atualize e instale pacotes necessários
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk wget unzip

# Defina o diretório de trabalho
WORKDIR /usr/src/usrfacil

# Copie o arquivo pom.xml e instale as dependências
COPY usrfacil/pom.xml .

RUN mvn clean install -DskipTests

# Copie o código-fonte do aplicativo
COPY usrfacil/src ./src

# Compile o aplicativo
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim

# Exponha a porta 8080
EXPOSE 8080

# Defina o diretório de trabalho
WORKDIR /usr/src/usrfacil

# Copie o JAR do build stage para o diretório de trabalho
COPY --from=build /usr/src/usrfacil/target/usrfacil-0.0.1-SNAPSHOT.jar /usr/src/usrfacil/

# Comando para executar o aplicativo quando o contêiner inicia
ENTRYPOINT ["java", "-jar", "usrfacil-0.0.1-SNAPSHOT.jar"]

# Etapa 1: Construção do projeto
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

# Copia o arquivo do projeto para o container
COPY . .

# Compila o projeto usando o Maven (assumindo que o projeto usa Maven)
RUN ./mvnw clean package -DskipTests

# Etapa 2: Imagem final para execução
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copia o JAR gerado da etapa de build
COPY --from=build /app/target/apiFinancas-*.jar app.jar

# Expõe a porta padrão da aplicação Spring Boot
EXPOSE 8083

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
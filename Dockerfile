# Etapa de build
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

# Copia os arquivos e dá permissão ao Maven Wrapper
COPY . .
RUN chmod +x mvnw

# Executa o build, pulando os testes
RUN ./mvnw clean package -DskipTests

# Etapa de execução
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copia o JAR da etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta (Render ignora, mas útil localmente)
EXPOSE 8080

# Usa o .env se for local e executa
ENTRYPOINT ["java", "-jar", "/app.jar"]
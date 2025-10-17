# =========================================================================
# ETAPA 1: Construcción (Builder) - Usa un JDK completo para compilar
# =========================================================================
# Usamos una imagen base con Java 17 (puedes cambiarla si usas otra versión) y Maven.
FROM eclipse-temurin:21-jdk-jammy AS builder

# Establecemos el directorio de trabajo dentro del contenedor.
WORKDIR /app

# Copiamos primero el pom.xml y los scripts del wrapper de Maven.
# Esto aprovecha el sistema de caché de Docker. Si no cambian, no se vuelven a descargar las dependencias.
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Descargamos todas las dependencias del proyecto.
RUN ./mvnw dependency:go-offline

# Copiamos el resto del código fuente de la aplicación.
COPY src ./src

# Empaquetamos la aplicación, saltando los tests para agilizar la construcción del Dockerfile.
RUN ./mvnw package -DskipTests

# =========================================================================
# ETAPA 2: Ejecución (Runner) - Usa solo un JRE, que es más ligero
# =========================================================================
# Usamos una imagen base más ligera que solo contiene el entorno de ejecución de Java (JRE).
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

# Copiamos el archivo .jar generado en la etapa anterior (builder) a la imagen final.
# Lo renombramos a app.jar para simplificar el comando de ejecución.
COPY --from=builder /app/target/*.jar app.jar

# Exponemos el puerto 8080, que es el que usa tu aplicación Spring Boot.
EXPOSE 8080

# Comando que se ejecutará cuando se inicie el contenedor.
ENTRYPOINT ["java", "-jar", "app.jar"]
FROM ghcr.io/graalvm/native-image-community:22-muslib

WORKDIR /app
COPY target/backend-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "backend-0.0.1-SNAPSHOT.jar"]
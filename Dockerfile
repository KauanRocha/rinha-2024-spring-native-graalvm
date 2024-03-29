#FROM container-registry.oracle.com/graalvm/native-image:21.0.0-ol9-20230919 AS builder
#RUN microdnf install -y maven
#
#WORKDIR /java-is-goat
#
#COPY . /java-is-goat
#COPY .mvn/ .mvn
#COPY pom.xml .
#
## Adicione o Maven ao PATH
#ENV PATH="/usr/share/maven/bin:${PATH}"
#RUN mvn -B dependency:resolve
#RUN mvn -B clean package
#RUN mvn -B native-image:build
#FROM container-registry.oracle.com/os/oraclelinux:9-slim
#COPY --from=builder /java-is-goat/target/native-image/application /app
#ENTRYPOINT ["/app", "-Xms64m", "-Xmx120m"]



FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app
COPY . /app

COPY mvnw pom.xml ./
RUN ./mvnw dependency:resolve
CMD ["./mvnw", "spring-boot:run"]
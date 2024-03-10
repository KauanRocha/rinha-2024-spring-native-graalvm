#FROM container-registry.oracle.com/graalvm/native-image:21.0.0-ol9-20230919 AS builder
#WORKDIR /java-is-goat
#
#COPY . /java-is-goat
#COPY .mvn/ .mvn
#COPY pom.xml .
#
#RUN mvn -B dependency:resolve
#
#RUN mvn -B clean package &&\
#    mvn -B native-image:build
#
#FROM container-registry.oracle.com/os/oraclelinux:9-slim
#
#COPY --from=builder /java-is-goat/target/native-image/application /app
#
#ENTRYPOINT ["/app", "-Xms64m", "-Xmx120m"]

FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:resolve
COPY src ./src
CMD ["./mvnw", "spring-boot:run"]
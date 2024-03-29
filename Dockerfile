FROM ubuntu:latest
WORKDIR /app
COPY /target/rinha-de-backend-java-21 rinha
ENTRYPOINT ["/app/rinha"]
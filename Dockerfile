FROM container-registry.oracle.com/os/oraclelinux:9-slim
WORKDIR /app
COPY /target/backend rinha
ENTRYPOINT ["/app/rinha"]

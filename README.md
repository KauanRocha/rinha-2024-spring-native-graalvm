# Desafio da Rinha de Backend Q1 2024

Este é um repositório para o meu projeto incrível que utiliza tecnologias modernas para desenvolvimento e implantação, criado como parte do desafio da rinha de backend do primeiro trimestre de 2024.

![image](https://github.com/KauanRocha/rinha-2024-spring-native-graalvm/assets/107556890/bbb5b803-c84e-4053-b36e-bcb04f52fad4)


## Tecnologias Utilizadas

- ![Spring JDBC](https://img.shields.io/badge/Spring%20JDBC-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
  - O Spring JDBC foi escolhido pela sua simplicidade e eficiência no acesso a dados relacionais, permitindo a execução de consultas SQL e a manipulação de dados de forma integrada com o Spring Framework.

- ![Java 22](https://img.shields.io/badge/Java%2022-007396?style=for-the-badge&logo=java&logoColor=white)
  - A versão Java 22 foi adotada devido às suas melhorias de desempenho, especialmente na execução de aplicações que fazem uso intenso de recursos, como é o caso deste projeto.

- ![Virtual Threads](https://img.shields.io/badge/Virtual%20Threads-4CAF50?style=for-the-badge&logo=java&logoColor=white)
  - Os Virtual Threads são uma funcionalidade do Java que permite a criação de threads leves e escalonáveis, proporcionando um melhor aproveitamento dos recursos do sistema operacional e uma execução mais eficiente das tarefas concorrentes.

-  ![Spring Native](https://img.shields.io/badge/Spring%20Native-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
  - A compilação para código nativo oferecida pelo Spring Native foi escolhida para reduzir o tempo de inicialização da aplicação e o consumo de memória, garantindo uma experiência mais ágil e responsiva para os usuários.

-  ![GraalVM](https://img.shields.io/badge/GraalVM-EC7211?style=for-the-badge&logo=graalvm&logoColor=white)
  - O GraalVM foi utilizado como runtime para a execução do código nativo gerado pelo Spring Native, proporcionando otimizações de desempenho e a possibilidade de integração com outras linguagens além do Java.

-  ![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
  - O Docker foi adotado para a criação de contêineres isolados que encapsulam o ambiente de execução do aplicativo, garantindo consistência e portabilidade entre diferentes ambientes de desenvolvimento, teste e produção.

-  ![Nginx](https://img.shields.io/badge/Nginx-009639?style=for-the-badge&logo=nginx&logoColor=white)
  - O Nginx foi escolhido como servidor proxy reverso para distribuir o tráfego de entrada de forma balanceada entre múltiplas instâncias do aplicativo, melhorando a escalabilidade e a disponibilidade do sistema.

## Pré-requisitos

Certifique-se de ter as seguintes ferramentas instaladas:

- Java 22 JDK
- Docker
- GraalVM

## Como Executar

1. Clone este repositório.
2. Execute `./mvnw -Pnative native:compile` para compilar o projeto.
3. Execute `docker build -t api-backend-native .` para construir a imagem Docker.
4. Execute `docker-compose up -d` para subir os containers Docker necessários.
5. Execute os ttestes como manda a documentacao da rinha.

## Contribuindo

Sinta-se à vontade para contribuir com melhorias ou correções de bugs. Abra um pull request ou uma issue para discutir suas sugestões.

## Licença

Este projeto está licenciado sob a [MIT License](LICENSE).

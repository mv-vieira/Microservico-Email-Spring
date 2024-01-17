# Microserviço Email

Tecnológias e conceitos usados no projeto:

• Spring Framework

• RabbitMQ

• PostgreSQL

• Microserviço

• SMTP Email

• Maven

• REST

• Swagger

• Validation

• Lombok

• Clean Architeture

• JUnit

• Mockito

• Tratamento de Exceções

## Fluxo completo da comunicação assíncrona

![preview_arch](https://github.com/mv-vieira/Microservico-Email-Spring/assets/107760543/09bb3d11-788f-48a0-829f-aa992f06f7af)

• Dando uma visão geral, esta arquitetura é dividida em Microserviço de Usuário e Microserviço de E-mail, cada um com seus próprios bancos de dados. A comunicação entre eles ocorre por meio de um intermediário, que neste caso é baseado em filas assíncronas gerenciadas pelo RabbitMQ.

##

![broker](https://github.com/mv-vieira/Microservico-Email-Spring/assets/107760543/4e59314a-42cc-4c64-8617-c31b5963e1c8)

• O intermediário, neste contexto denominado "broker", atua como uma entidade intermediária entre os microserviços. Ele recebe eventos, os armazena em uma Estrutura de Dados de Fila e os aciona, permitindo que outros microserviços aguardem e consumam esses eventos para reagirem de acordo. Essa abordagem facilita a comunicação e a coordenação entre os componentes do sistema distribuído.



 
## Demonstração
![Captura de tela 2024-01-17 171248](https://github.com/mv-vieira/Microservico-Email-Spring/assets/107760543/2d693ba2-dbcd-41b2-9cce-be8bab93169b)

![Captura de tela 2024-01-17 172021](https://github.com/mv-vieira/Microservico-Email-Spring/assets/107760543/b2ef8fc0-e2ae-45a3-8ed3-0f89ba4ba665)

![Captura de tela 2024-01-17 170836](https://github.com/mv-vieira/Microservico-Email-Spring/assets/107760543/55fb2ed1-e5e9-420a-97b9-8b01ac97f0a0)


## Documentação feita pelo Swagger
http://localhost:8081/swagger-ui/index.html#/user-controller/saveUser

![Captura de tela 2024-01-17 173453](https://github.com/mv-vieira/Microservico-Email-Spring/assets/107760543/21086280-784d-498a-8708-5210495b0217)







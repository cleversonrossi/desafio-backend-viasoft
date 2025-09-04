# Desafio Back-end Viasoft

Projeto desenvolvido como solução do desafio Viasoft usando Spring Boot 3 + Java 17.

### Pré-requisitos
- Java 17
- Maven 3.8+

### Arquitetura de cadadas
- src/main/java/br/com/viasoft/  

    ├── controller/
  
    ├── dto/
  
    ├── exception/
  
    ├── service/
  
    └── DesafioBackendApplication.java
- src/main/resources/
  
    ├── application.properties
- src/main/test/java/br/com/viasoft/controller/
  
    ├── EmailControllerTest.java

* controller: Contém as classes REST controllers.
* service: Contém a lógica de negócio e serviços.
* resources: Contém classes de configuração (ex: application).
* dto: Contém os objetos de transferência de dados (Data Transfer Objects).

### Rodando o projeto
```bash
mvn spring-boot:run
```

### Testando via Postman
POST http://localhost:8080/api/email

Body JSON:
```json
{
  "destinatarioEmail": "teste@viasoft.com",
  "destinatarioNome": "Viasoft",
  "remetenteEmail": "eu@teste.com",
  "assunto": "Teste integração",
  "conteudo": "Olá, este é um teste de envio!"
}
```

### Rodando os testes
```bash
mvn test
```

## Configuração
Arquivo: `src/main/resources/application.properties`

```
mail.integracao=AWS ou OCI
server.port=8080
```

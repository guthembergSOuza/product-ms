
<h1>Crud de Produtos</h1>

<h3>Comandos para executar a aplicação:</h3> </br>

(1) Levantar o banco da aplicação com o docker: </br>

   sudo docker-compose up -d </br></br>
 
(2) Levantar a aplicação: </br>

   ./mvnw spring-boot:run

<h3> Endpoints </h3>

| Verbo HTTP | Resource path                   | Descrição                   | 
|------------|---------------------------------|-----------------------------|
| POST       | localhost:9999//products        | Criação de um produto       |
| PUT        | localhost:9999//products/{id}   | Busca de um produto por ID  |
| GET        | localhost:9999//products        | Lista de produtos           | 
| GET        | localhost:9999//products/search | Lista de produtos filtrados | 
| DELETE     | localhost:9999//products/{id}   | Lista de produtos           | 


Entidades Iniciais e cadastros

* Usuário/Funcionário/Fornecedores/Clientes
* categoria Produtos
* sub-categorias produtos
* Produtos
* Lojas
* Cidades
* Caixa
* Vendas
* Estoque
* Transferencia de Produtos entre lojas



Rodar em modo desenvolvimento

mvn clean         # delete temporary stuff
mvn test          # run all the tests (gwt and junit)
mvn gwt:run       # run development mode
mvn gwt:compile   # compile to javascript
mvn package       # generate a .war package ready to deploy

For more information about other available goals, read maven and gwt-maven-plugin 
documentation (http://maven.apache.org, http://mojo.codehaus.org/gwt-maven-plugin)  

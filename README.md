<img width=100% src="http://capsule-render.vercel.app/api?type=Soft&color=303030&height=100&section=header&text=GerenciamentoDeCondominios&fontSize=30&fontColor=fff&animation=twinkling&fontAlignY=50"/>

[![licence mit](https://img.shields.io/badge/licence-MIT-blue.svg)](./LICENSE)

Projeto de conclusão do módulo de Desenvolvimento de Aplicativos no Senai pela ETS-BOSCH CT <br>
Consiste em um app que gerencia as visitas e reservas de salas de evento do condominio

O Design do Aplicativo foi pensado de forma a ser intuitivo ao usuario, sem muito texto ou exigencia de conhecimento prévio, pois iria desde funcionarios até visitantes
> _Design é igual piada, se precisa explicar, é pq não funciona._

## Technologies/Tecnologias Utilizadas
- ### BackEnd
  - [Spring Boot](https://github.com/spring-projects/spring-boot/releases/tag/v3.1.5)
    > Programada em java, a API foi feita com SpringBoot, fornecendo segurança e conexão com o banco de dados 
  - [MongoDB](https://www.mongodb.com/pt-br)
    > Para armazenar os dados dos usuários e condomínios, foi usado MongoDB como banco de dados
- ### FrontEnd
  - [React-Native](https://reactnative.dev/docs/getting-started)
    > Feito para usar em dispositivos móveis, React-Native foi uma peça essencial para a parte visual do programa

## Structure/Estrutura do Projeto
- ### [BackEnd](./projeto-devapp/src/main)
  - [resources](./projeto-devapp/src/main/resources)
    > Local para salvar variaveis de ambiente
  - [projetodevapp](./projeto-devapp/src/main/java/com/gerenciador/projetodevapp)
    - #### [config](./projeto-devapp/src/main/java/com/gerenciador/projetodevapp/config)         - Configurações da API
    - #### [controller](./projeto-devapp/src/main/java/com/gerenciador/projetodevapp/controller) - Controla o que a API fará com determinada requisição
    - #### [model](./projeto-devapp/src/main/java/com/gerenciador/projetodevapp/model)           - Molda os tipos de objetos que serão guardados no banco de dados
    - #### [repository](./projeto-devapp/src/main/java/com/gerenciador/projetodevapp/repository) - Salva diretamente no banco de dados
    - #### [request](./projeto-devapp/src/main/java/com/gerenciador/projetodevapp/request)       - Modelos de requisições que o front pode enviar
    - #### [service](./projeto-devapp/src/main/java/com/gerenciador/projetodevapp/service)       - Serviços úteis que a API pode usar durante o processamento
- ### [FrontEnd](./src)
  - #### [components](./src/components) - Componentes personalizados para o aplicativo
  - #### [config](./src/config) - Configurações úteis para o aplicativo
  - #### [styles](./src/styles) - Define o estilo do aplicativo
    -  [default theme](./src/styles/theme/default.js) - Tema claro do aplicativo
    -  [dark theme](./src/styles/theme/dark.js) - Tema escuro do aplicativo
  - #### [views](./src/views) - Paginas do aplicativo

## License/Licença do Projeto

- [MIT](./LICENSE)

<img width=100% src="https://capsule-render.vercel.app/api?type=waving&color=303030&height=120&section=footer"/>

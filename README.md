# Empresas-Android

O projeto ioasys Enterprises consiste em um aplicativo Android que possui um login de autenticação em um servidor para que sejam exibidas as empresas salvas na API fornecida.

    * A interface do aplicativo assemelha-se ao padrão fornecido pela plataforma Zeplin;
    * O usuário entra com as credenciais (email e senha) e na resposta ao login são enviados access-token, client e uid;
    * Credenciais:
        * Usuário de Teste: testeapple@ioasys.com.br
        * Senha de Teste : 12341234
    * Para a requisição foi utilizada a biblioteca Retrofit por ser uma maneira mais eficiente e simples de fazer requisições HTTP;
    * Para parse de Json foi utilizada a biblioteca GSON;
    * Caso as credenciais sejam aprovadas, será exibida uma listagem das empresas;
        * Optou-se por utilizar RecyclerView para a listagem das empresas por ser mais eficiente do que o ListView por exemplo. Dentre as vantagens cita-se a reutilização das células que não estão visíveis adicionando novos valores, este fato contribui para a performance da aplicação.
        * O CardView foi utilizado para exibir as informações de cada empresa e optou-se por esta forma de visualização por ser mais agradável visualmente.
        * As bibliotecas adicionadas são "componentes" do Material Design.
    * Para carregar as imagens, optou-se pela biblioteca Glide, por possuir um consumo de memória menor se comparado a biblioteca Picasso

    * No CardView de cada empresa é exibido a imagem, nome, tipo e localização (cidade e país). Ao clicar em um card, é possível visualizar mais detalhes sobre a empresa (descrição).

# LightIMDB
Melhorar o protótipo de IMDB feito pelo professor de Técnicas de Programação 1

_Observação: melhor visto no [GitHub](https://github.com/victoragcosta/TP1-GoL) ou usando um editor de texto com preview de MarkDown como [Atom](https://atom.io/)._

## Desenvolvedores:
Aluno | Username | Matrícula
------|----------|----------
Victor André Gris Costa | @victoragcosta | 16/0019311
Hugo Nascimento Fonseca | @Hugo-NF | 16/0008166
André Filipe Caldas Laranjeira | @AndreLaranjeira | 16/0023777

## Na versão atual pode-se:
* [x] Cadastrar Usuários
* [x] Fazer Login com um Usuário
* [x] Adicionar Filmes
* [x] Visualizar detalhes de Filmes incluindo a avaliação média
* [x] Avaliar Filmes caso esteja logado
* [x] Listar Todos os Filmes
* [x] Passar de Filme em Filme

## Pretendia-se:
* [ ] Modificar Usuário por si mesmo
* [ ] Conta de Administrador para editar Filmes e banir usuários

## Bibliotecas utilizadas:
* [x] Play Framework 2.x
* [x] Bootstrap (Estilização de Página)
* [x] jQuery (Manipulação do DOM)

## Estrutura básica:
* HomeController:
  Função Simples de abrir a página inicial.
* FilmeController: Coração do programa, possui dois DAO para interagir com o banco de dados, FilmeDAO e AvaliaçãoDAO, recebendo pedidos de cadastro de Filmes, avaliação de filmes e exibição dos Filmes.
* UsuarioController: Gerencia a criação de usuários e o login de usuário, faz uso de UsuarioDAO.
* AvaliacaoDAO: Manipula e lê o banco de dados para adicionar Avaliações e editá-las também. Evita avaliações repetidas.
* FilmeDAO: Manipula e lê o banco de dados na tabela Filmes para adicionar Filmes ou exibi-los na tela.
* UsuarioDAO: Manipula e lê o banco de dados com o propósito de adicionar e logar usuários.
* A View: as classes da View são geradas automáticamente por formulários .scala.html que permitem a injeção de código Scala diretamente. Utilizou-se muita estilização Bootstrap e essa biblioteca se integra bem com a Play Framework. Existe uma Biblioteca para helpers de formúlarios Bootstrap, porém essa possibilidade não foi explorada por falta de tempo.

## Antes de executar:
* Configure sua database com a senha e usuário corretos, lá em aplication.conf
* Execute as evolutions 1.sql e 2.sql para gerar um banco de dados de teste

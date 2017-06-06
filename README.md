# LightIMDB
Melhorar o protótipo de IMDB feito pelo professor de Técnicas de Programação 1

Requerimentos:
* [ ] Implementar Usuários (login e tals);
* [ ] Implementar Avaliações para filme (dá nota e tals);
  * Criar relação de n para n entre filme e usuários com as avaliações (Criar uma terceira tabela avaliações contendo as chaves estrangeiras de Usuário e de Filme e um campo nota);

Opcionais:
* [ ] Implementar exibição de avaliação usando um comando SQL com a média usando:
  * ```SELECT titulo, AVG(nota) FROM Filme f Inner Join Avaliacao a GROUP BY titulo;```
* [ ] Implementar múltiplos tipos de trabalhos artísticos;

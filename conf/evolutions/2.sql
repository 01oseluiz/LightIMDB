USE imdb;
# --Inserts USUARIO--
INSERT INTO USUARIO(email, senha) VALUES('victoragcosta@hotmail.com', '12345678');
INSERT INTO USUARIO(email, senha) VALUES('suamae@hotmail.com', '12345678');
INSERT INTO USUARIO(email, senha) VALUES('seupai@gmail.com', '12345678');

# --Inserts FILMES--
INSERT INTO FILMES(titulo, diretor, ano, duracao, sinopse, origem) VALUES(
  'Moonlight',
  'Barry Jenkins',
  2016,
  111,
  'A chronicle of the childhood, adolescence and burgeoning adulthood of a young, African-American, gay man growing up in a rough neighborhood of Miami.',
  'USA'
);
INSERT INTO FILMES(titulo, diretor, ano, duracao, sinopse, origem) VALUES(
  'La La Land',
  'Damien Chazelle',
  2016,
  128,
  'Two proper L.A. dreamers, a suavely charming soft-spoken jazz pianist and a brilliant vivacious playwright, while waiting for their big break, attempt to reconcile aspirations and relationship in a magical old-school romance.',
  'USA'
);
INSERT INTO FILMES(titulo, diretor, ano, duracao, sinopse, origem) VALUES(
  'A Onda',
  ' Dennis Gansel',
  2008,
  107,
  'A high school teacher\'s experiment to demonstrate to his students what life is like under a dictatorship spins horribly out of control when he forms a social unit with a life of its own.',
  'Alemanha'
);
INSERT INTO FILMES(titulo, diretor, ano, duracao, sinopse, origem) VALUES(
  'Forrest Gump',
  'Robert Zemeckis',
  1994,
  142,
  'While not intelligent, Forrest Gump has accidentally been present at many historic moments, but his true love, Jenny Curran, eludes him.',
  'USA'
);
INSERT INTO FILMES(titulo, diretor, ano, duracao, sinopse, origem) VALUES(
  'Interstellar',
  'Christopher Nolan',
  2014,
  169,
  'A team of explorers travel through a wormhole in space in an attempt to ensure humanity\'s survival.',
  'USA'
);

# --Inserts AVALIACAO--
INSERT INTO AVALIACAO(USUARIO_id, FILMES_id, nota) VALUES(1, 1, 3);
INSERT INTO AVALIACAO(USUARIO_id, FILMES_id, nota) VALUES(2, 1, 5);
INSERT INTO AVALIACAO(USUARIO_id, FILMES_id, nota) VALUES(3, 1, 4);

INSERT INTO AVALIACAO(USUARIO_id, FILMES_id, nota) VALUES(1, 2, 3);
INSERT INTO AVALIACAO(USUARIO_id, FILMES_id, nota) VALUES(2, 2, 5);
INSERT INTO AVALIACAO(USUARIO_id, FILMES_id, nota) VALUES(3, 2, 4);

INSERT INTO AVALIACAO(USUARIO_id, FILMES_id, nota) VALUES(1, 3, 5);
INSERT INTO AVALIACAO(USUARIO_id, FILMES_id, nota) VALUES(2, 3, 3);
INSERT INTO AVALIACAO(USUARIO_id, FILMES_id, nota) VALUES(3, 3, 5);

INSERT INTO AVALIACAO(USUARIO_id, FILMES_id, nota) VALUES(1, 4, 5);
INSERT INTO AVALIACAO(USUARIO_id, FILMES_id, nota) VALUES(2, 4, 5);
INSERT INTO AVALIACAO(USUARIO_id, FILMES_id, nota) VALUES(3, 4, 5);

INSERT INTO AVALIACAO(USUARIO_id, FILMES_id, nota) VALUES(1, 5, 5);
INSERT INTO AVALIACAO(USUARIO_id, FILMES_id, nota) VALUES(2, 5, 4);
INSERT INTO AVALIACAO(USUARIO_id, FILMES_id, nota) VALUES(3, 5, 2);
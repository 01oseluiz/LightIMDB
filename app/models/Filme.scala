package models

import anorm.SQL
import anorm.RowParser
import anorm.Macro
import anorm.SqlStringInterpolation
import javax.inject.Inject
import play.api.db.Database

case class Filme(id: Int, titulo: String, diretor: String, ano: Int, duracao: Int, sinopse: String, origem: String)

class FilmeDAO @Inject() (database: Database) {
  val parser: RowParser[models.Filme] = Macro.namedParser[models.Filme]

  def salvar(filme: Filme) = database.withConnection { implicit connection =>
    val id: Option[Long] = SQL(
      """INSERT INTO FILMES(titulo, diretor, ano, duracao, sinopse, origem)
            values ({titulo}, {diretor}, {ano}, {duracao}, {sinopse}, {origem})""")
      .on('titulo -> filme.titulo,
        'diretor -> filme.diretor,
        'ano -> filme.ano,
        'duracao -> filme.duracao,
        'sinopse -> filme.sinopse,
        'origem -> filme.origem).executeInsert()
  }

  def listar = database.withConnection { implicit connection =>
    SQL"SELECT * FROM FILMES".as(parser.*)
  }
}
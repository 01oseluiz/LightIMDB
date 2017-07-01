package models

import javax.inject.Inject
import anorm.{Macro, RowParser, SQL}
import anorm.SqlStringInterpolation
import play.api.db.Database

case class Avaliacao (id: Int, id_usuario: Int, id_filmes: Int, nota: Int)

class AvaliacaoDAO @Inject() (database: Database) {
  val parser: RowParser[models.Avaliacao] = Macro.namedParser[models.Avaliacao]
  def salvar(review: Avaliacao) = database.withConnection { implicit connection =>
    val id: Option[Long] = SQL(
      """INSERT INTO AVALIACAO(USUARIO_id, FILMES_id, nota)
          values ({id_usuario}, {id_trabalho}, {nota})""").on('id_usuario -> review.id_usuario,
          'id_trabalho -> review.id_filmes,
          'nota -> review.nota).executeInsert()
  }

  def listar = database.withConnection { implicit connection =>
    SQL"SELECT USUARIO_id AS id_usuario, FILMES_id AS id_filmes, nota FROM AVALIACAO".as(parser.*)
  }


}

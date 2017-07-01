package models

import javax.inject.Inject
import anorm.{Macro, RowParser, SQL}
import anorm.SqlStringInterpolation
import play.api.db.Database

case class Avaliacao (id: Int, id_usuario: Int, id_trabalho: Int, nota: Int) {

  assert(id > 0)
  assert(id_usuario > 0)
  assert(id_trabalho > 0)
  assert(nota > 0)
  assert(nota < 6)

}

class AvaliacaoDAO @Inject() (database: Database) {
  val parser: RowParser[models.Avaliacao] = Macro.namedParser[models.Avaliacao]

  def salvar(review: Avaliacao) = database.withConnection { implicit connection =>
    val id: Option[Long] = SQL(
      """INSERT INTO TB_AVALIACAO(TB_USUARIO_id, TB_TRAB_id, nota)
            values ({id_usuario}, {id_trabalho}, {nota})""")
      .on('id_usuario -> review.id_usuario,
        'id_trabalho -> review.id_trabalho,
        'nota -> review.nota).executeInsert()
  }

  def listar = database.withConnection { implicit connection =>
    SQL"SELECT TB_USUARIO_id AS id_usuario, TB_TRAB_is AS id_trabalho, nota FROM TB_AVALIACAO".as(parser.*)
  }

}
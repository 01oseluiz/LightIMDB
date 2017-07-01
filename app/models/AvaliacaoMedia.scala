package models

import javax.inject.Inject
import anorm.{Macro, RowParser, SQL}
import anorm.SqlStringInterpolation
import play.api.db.Database

case class AvaliacaoMedia (id_trabalho: Int, media: Int)

class AvaliacaoMediaDAO @Inject() (database: Database) {
  val parser: RowParser[models.Avaliacao] = Macro.namedParser[models.Avaliacao]

  def listar = database.withConnection { implicit connection =>
    SQL"SELECT TB_TRAB_is AS id_trabalho, AVG(nota) AS media FROM TB_AVALIACAO GROUP BY TB_TRAB_is".as(parser.*)
  }

}

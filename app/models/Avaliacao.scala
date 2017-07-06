package models

import javax.inject.Inject
import anorm.{Macro, RowParser, SQL}
import anorm.SqlStringInterpolation
import play.api.db.Database

case class Avaliacao (id: Int, id_usuario: Int, id_filmes: Int, nota: Int)
case class AvalMedia (avg: Double)

class AvaliacaoDAO @Inject() (database: Database) {
  val parser: RowParser[models.Avaliacao] = Macro.namedParser[models.Avaliacao]
  val mediaParser: RowParser[models.AvalMedia] = Macro.namedParser[models.AvalMedia]

  def salvar(review: Avaliacao) = database.withConnection { implicit connection =>
    val id: Option[Long] = SQL(
      """INSERT INTO AVALIACAO(USUARIO_id, FILMES_id, nota)
          values ({id_usuario}, {id_trabalho}, {nota})""")
      .on('id_usuario -> review.id_usuario,
          'id_trabalho -> review.id_filmes,
          'nota -> review.nota).executeInsert()
  }

  def atualizar(review: Avaliacao) = database.withConnection { implicit connection =>
    SQL("""UPDATE AVALIACAO SET nota={nota} WHERE id={id};""")
      .on('nota -> review.nota,
      'id -> review.id).executeUpdate()
  }

  def listar = database.withConnection { implicit connection =>
    SQL"SELECT id, USUARIO_id AS id_usuario, FILMES_id AS id_filmes, nota FROM AVALIACAO".as(parser.*)
  }

  def getQuantAvaliacoesByFilmeId(id: Long): Int = {
    val result = database.withConnection { implicit connection =>
      SQL"""SELECT id, USUARIO_id AS id_usuario, FILMES_id AS id_filmes, nota FROM AVALIACAO
           WHERE FILMES_id=$id
         """.as(parser.*)
    }
    result.length
  }

  def getAvaliacaoByFilmeId(id: Long): Double = {
    val result = database.withConnection { implicit connection =>
      SQL"SELECT AVG(nota) AS avg FROM AVALIACAO WHERE FILMES_id=$id".as(mediaParser.*)
    }
    if(result.nonEmpty){
      return result(0).avg
    } else {
      return 0
    }
  }

  def getAvaliacaoByUserEFilme(idUser: Int, idFilme: Long): Option[Int] = {
    val result = database.withConnection {implicit connection =>
      SQL"""SELECT id, USUARIO_id AS id_usuario, FILMES_id AS id_filmes, nota FROM AVALIACAO
           WHERE USUARIO_id=$idUser AND FILMES_id=$idFilme;
         """.as(parser.*)
    }
    if(result.nonEmpty){
      return Some(result(0).nota)
    } else {
      return None
    }
  }

  def avaliar(review: Avaliacao) = {
    val avaliacoes = listar
    var encontrado = false
    var aval: Avaliacao = null
    avaliacoes.foreach(a => {
      if(a.id_usuario == review.id_usuario && a.id_filmes == review.id_filmes){
        encontrado = true
        aval = a
      }
    })
    if(encontrado){
      val reavaliar = Avaliacao(aval.id, 0, 0, review.nota)
      atualizar(reavaliar)
    } else {
      salvar(review)
    }
  }

}

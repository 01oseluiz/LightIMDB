package models

import javax.inject.Inject
import anorm.{Macro, RowParser, SQL}
import anorm.SqlStringInterpolation
import play.api.db.Database

case class Usuario(email: String, senha: String)

class UsuarioDAO @Inject() (database: Database) {
  val parser: RowParser[models.Usuario] = Macro.namedParser[models.Usuario]

  def salvar(user: Usuario) = database.withConnection { implicit connection =>
    val id: Option[Long] = SQL(
      """INSERT INTO TB_USUARIO(email, senha)
            values ({email}, {senha})""")
      .on('email -> user.email,
        'senha -> user.senha).executeInsert()
  }

  def listar = database.withConnection { implicit connection =>
    SQL"SELECT email, senha FROM TB_USUARIO".as(parser.*)
  }

}
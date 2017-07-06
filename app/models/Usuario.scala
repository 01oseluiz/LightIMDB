package models

import anorm.SQL
import anorm.SqlQuery
import anorm.RowParser
import anorm.Macro
import anorm.SqlStringInterpolation
import anorm.SqlParser
import play.api.Play
import javax.inject.Inject

import play.api.db.Database
import javax.inject.Singleton

case class Usuario(id: Int, email: String, senha: String)

class UsuarioDAO @Inject() (database: Database){
  val parser : RowParser[models.Usuario] = Macro.namedParser[models.Usuario]

  def salvar(usuario: Usuario) = database.withConnection { implicit connection =>
    Login.id = usuario.id
    Login.email = usuario.email
    val id: Option[Long] = SQL(
      """INSERT INTO USUARIO(email, senha)
            values ({email}, {senha})""")
      .on('email -> usuario.email,
        'senha -> usuario.senha
        ).executeInsert()
  }

  def login(usuario: Usuario): Boolean = {
    val currentUser = autenticar(usuario.email, usuario.senha)
    if (currentUser.nonEmpty) {
      Login.login = "Bem-vindo!"
      Login.id = currentUser.head.id
      Login.email = currentUser.head.email
      true
    }
    else {
      Login.login = "Credenciais de login invalidas"
      false
    }
  }

  def logout : Unit = {
    Login.login = "User disconnected"
    Login.email = null
    Login.id = 0
  }

  def autenticar(email: String, senha: String): List[Usuario] = database.withConnection{ implicit connection =>
    SQL(
      """SELECT * FROM USUARIO
        |WHERE email = {useremail} AND senha = {usersenha};""".stripMargin)
      .on('useremail -> email,
        'usersenha ->senha)
      .as(parser.*)
  }

  def listar = database.withConnection { implicit connection =>
    SQL"SELECT * FROM Usuario".as(parser.*)
  }

}

object Login {
  var id = 0
  var email: String = _
  var login: String = _
}

/* Implementação do Tio Boni
package models

case class Usuario(email: String, senha: String)

object Usuario {
var products = Set(
      Usuario("foo", "foo"),
      Usuario("blah", "blah")
    )

 def pesquisaPorEmail(email : String) = products.filter(u => u.email == email).toList(0)
}*/
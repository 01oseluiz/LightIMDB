package controllers

import play.api.mvc._
import models.{Usuario, UsuarioDAO}
import javax.inject.Inject
import play.api.data._
import play.api.data.Forms._
import javax.inject.Singleton
import play.api.i18n.I18nSupport
import play.api.i18n.MessagesApi

@Singleton
class UsuarioController @Inject()(dao: UsuarioDAO, val messagesApi: MessagesApi) extends Controller with I18nSupport {

  def cadastro = Action { implicit request =>
    Ok(views.html.usuario.novoUsuario(userForm))
  }

  def cadastroSubmissao = Action { implicit request =>
    userForm.bindFromRequest.fold(
      formWithErrors => {
        BadRequest(views.html.usuario.novoUsuario(formWithErrors))
      },
      usuario => {
        val novoCadastro = Usuario(0, usuario.email, usuario.senha)
        dao.salvar(novoCadastro)
        Created(views.html.index("Usuario registrado com sucesso!"))
      }
    )

  }

  def login = Action {
    Ok(views.html.usuario.login(userForm))
  }
  def loginAut = Action { implicit request =>
    userForm.bindFromRequest.fold(
      formWithErrors => {
        BadRequest(views.html.usuario.login(formWithErrors))
      },
      usuario => {
        val novoLogin = Usuario(0, usuario.email, usuario.senha)
        val loginRealizado = dao.login(novoLogin)
        Ok(views.html.index("Login efetuado com sucesso!"))
      }
    )
  }

  def logout = Action {
    dao.logout
    Created(views.html.index(""))
  }

  val userForm = Form(
    mapping(
      "Email" -> nonEmptyText,
      "Senha" -> nonEmptyText
    )(UsuarioVO.apply)(UsuarioVO.unapply)
  )
}

case class UsuarioVO(email: String, senha: String);

/* Implementação do Tio Boni
package controllers

import play.api.mvc._

class UsuarioController extends Controller {

  def login(email: String, senha:String) = Action {
    Ok("foo")
  }
}
*/

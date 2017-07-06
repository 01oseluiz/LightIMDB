package controllers

import play.api.mvc._
import models.{Login, Usuario, UsuarioDAO}
import javax.inject.Inject

import play.api.data._
import play.api.data.Forms._
import javax.inject.Singleton

import play.api.i18n.I18nSupport
import play.api.i18n.MessagesApi

@Singleton
class UsuarioController @Inject()(dao: UsuarioDAO, val messagesApi: MessagesApi) extends Controller with I18nSupport {

  def cadastro = Action { implicit request =>
    Ok(views.html.usuario.novoUsuario(userForm, "", 0))
  }

  def cadastroSubmissao = Action { implicit request =>
    userForm.bindFromRequest.fold(
      formWithErrors => {
        BadRequest(views.html.usuario.novoUsuario(formWithErrors, "Erro, tente novamente", 2))
      },
      usuario => {
        val novoCadastro = Usuario(0, usuario.email, usuario.senha)
        dao.salvar(novoCadastro)
        Created(views.html.index("Usuario registrado com sucesso!", 0))
      }
    )

  }

  def login = Action {
    Ok(views.html.usuario.login(userForm, "", 0))
  }
  def loginAut = Action { implicit request =>
    userForm.bindFromRequest.fold(
      formWithErrors => {
        BadRequest(views.html.usuario.login(formWithErrors, "Erro!", 0))
      },
      usuario => {
        val novoLogin = Usuario(0, usuario.email, usuario.senha)
        val loginRealizado = dao.login(novoLogin)
        if(loginRealizado){
          Created(views.html.index("Login Efetuado com Sucesso!",0))
        } else {
          Ok(views.html.usuario.login(userForm, "Senha ou Usuário inválidos!", 3))
        }
      }
    )
  }

  def logout = Action {
    dao.logout
    Created(views.html.index("",0))
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

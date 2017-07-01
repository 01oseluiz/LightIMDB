package controllers

import play.api.mvc._
import models.Filme
import models.FilmeDAO
import javax.inject.Inject
import play.api.data._
import play.api.data.Forms._
import javax.inject.Singleton
import play.api.i18n.I18nSupport
import play.api.i18n.MessagesApi

@Singleton()
class FilmeController @Inject()(dao: FilmeDAO, val messagesApi: MessagesApi) extends Controller with I18nSupport {
  def listar = Action {
    var filmes = dao.listar
    Ok(views.html.filmes.listagem(filmes))
  }

  def novoFilme = Action {
    Ok(views.html.filmes.novoFilme(filmeForm))
  }

  def novoFilmeSubmissao = Action { implicit request =>
    filmeForm.bindFromRequest.fold(
      formWithErrors => {
        BadRequest(views.html.filmes.novoFilme(formWithErrors))
      },
      filme => {
        val novoFilme = Filme(0, filme.titulo, filme.diretor, filme.ano, filme.duracao, filme.sinopse, filme.origem)
        dao.salvar(novoFilme)
        var filmes = dao.listar
        Created(views.html.filmes.listagem(filmes))
      }
    )
  }

  val filmeForm = Form(
    mapping(
      "Titulo" -> nonEmptyText,
      "Diretor" -> nonEmptyText,
      "Ano" -> number(min=0, max = 2100),
      "Duracao" -> number(min=0, max=9999),
      "Sinopse" -> nonEmptyText,
      "Origem" -> nonEmptyText
    ) (FilmeVO.apply) (FilmeVO.unapply)
  )
}

case class FilmeVO(titulo: String, diretor: String, ano: Int, duracao: Int, sinopse: String, origem: String)
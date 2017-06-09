package models

import anorm.SQL
import anorm.RowParser
import anorm.Macro
import anorm.SqlStringInterpolation
import javax.inject.Inject
import play.api.db.Database

case class TrabalhoArtistico(id: Int, tipo: Int, titulo: String, autor: String, genero: String, ano: Int, origem: String,
descricao: String, duracao: Int) {

  assert(id > 0)
  assert(tipo > 0)
  assert(ano > 0)
  assert(ano < 10000)
  assert(duracao > 0)
  assert(duracao < 1000)

}

class TrabalhoArtisticoDAO @Inject() (database: Database) {
  val parser: RowParser[models.Filme] = Macro.namedParser[models.Filme]

  def salvar(trabalho: TrabalhoArtistico) = database.withConnection { implicit connection =>
    val id: Option[Long] = SQL(
      """INSERT INTO TB_TRAB_ART(TB_TIPO_id, titulo, autor, genero, ano, origem, descricao, duracao)
            values ({tipo}, {titulo}, {autor}, {genero}, {ano}, {origem}, {descricao}, {duracao})""")
      .on('tipo -> trabalho.tipo,
        'titulo -> trabalho.titulo,
        'autor -> trabalho.autor,
        'genero -> trabalho.genero,
        'ano -> trabalho.ano,
        'origem -> trabalho.origem,
        'descricao -> trabalho.descricao,
        'duracao -> trabalho.duracao).executeInsert()
  }

  def listar = database.withConnection { implicit connection =>
    SQL"SELECT TB_TIPO_id AS tipo, titulo, autor, genero, ano, origem, descricao, duracao FROM TB_FILME".as(parser.*)
  }

}
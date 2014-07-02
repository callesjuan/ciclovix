package br.ufes.inf.lprm.ciclovix.mb;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.ufes.inf.lprm.ciclovix.dao.AnotacaoDAO;
import br.ufes.inf.lprm.ciclovix.dao.CategoriaDAO;
import br.ufes.inf.lprm.ciclovix.dao.LocalDAO;
import br.ufes.inf.lprm.ciclovix.dao.MapaDAO;
import br.ufes.inf.lprm.ciclovix.dao.PessoaDAO;
import br.ufes.inf.lprm.ciclovix.entities.Anotacao;
import br.ufes.inf.lprm.ciclovix.entities.Categoria;
import br.ufes.inf.lprm.ciclovix.entities.Local;
import br.ufes.inf.lprm.ciclovix.entities.Mapa;
import br.ufes.inf.lprm.ciclovix.entities.Pessoa;

@ManagedBean
@SessionScoped
public class MapaMB implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * DAOs
	 */
	@EJB
	MapaDAO daoMapa;
	@EJB
	CategoriaDAO daoCategoria;
	@EJB
	PessoaDAO daoPessoa;
	@EJB
	AnotacaoDAO daoAnotacao;
	@EJB
	LocalDAO daoLocal;

	public String getTest() throws Exception {

		/*
		 * SALVANDO MAPA
		 */
		Mapa mapa = new Mapa();
		mapa.setNome("ciclovix");
		mapa.setDescricao("ciclovix");
		mapa.setOntologia("ciclovix");

		daoMapa.salvar(mapa);

		/*
		 * SALVANDO CATEGORIA DE ANOTACAO
		 */

		Categoria categoria = new Categoria();
		categoria.setNome("bicicletario");
		categoria.setTipo(1);

		daoCategoria.salvar(categoria);

		/*
		 * ADICIONANDO NOVO USUARIO
		 */

		Pessoa usuario = new Pessoa();
		usuario.setNome("juan");
		usuario.setSobrenome("calles");
		usuario.setContaTwitter("@callesjuan");

		daoPessoa.salvar(usuario);

		/*
		 * ADICIONANDO ANOTACAO
		 */

		Anotacao anotacao = new Anotacao();
		anotacao.setMapa(mapa);
		anotacao.addAutor(usuario);
		anotacao.setCategoria(categoria);
		anotacao.setTimestamp((new Date()).getTime());

		daoAnotacao.salvar(anotacao);

		Local local = new Local();
		local.setAnotacao(anotacao);
		local.setLatitude(0);
		local.setLongitude(0);

		daoLocal.salvar(local);

		return "Test!";
	}
}

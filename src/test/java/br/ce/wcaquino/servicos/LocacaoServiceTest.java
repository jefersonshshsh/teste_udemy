package br.ce.wcaquino.servicos;


import static br.ce.wcaquino.builders.FilmeBuider.umFilme;
import static br.ce.wcaquino.builders.FilmeBuider.umFilmeSemEstoque;
import static br.ce.wcaquino.builders.UsuarioBuilder.umUsuario;
import static br.ce.wcaquino.matchers.MatchersProprios.caiNumaSegunda;
import static br.ce.wcaquino.matchers.MatchersProprios.ehHoje;
import static br.ce.wcaquino.matchers.MatchersProprios.ehHojeComDiferencaDias;
import static br.ce.wcaquino.utils.DataUtils.isMesmaData;
import static br.ce.wcaquino.utils.DataUtils.obterDataComDiferencaDias;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.ce.wcaquino.builders.FilmeBuider;
import br.ce.wcaquino.builders.UsuarioBuilder;
import br.ce.wcaquino.builders.UsuarioBuilderModoCerto;
import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;
import br.ce.wcaquino.utils.DataUtils;
import buildermaster.BuilderMaster;

public class LocacaoServiceTest {
	
	private LocacaoService service;
	
	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Before
	public void setup(){
		service = new LocacaoService();
	}
	
	@Test
	public void deveAlugarFilme() throws Exception {	
		Assume.assumeFalse(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));
		//cenario
		//Usuario usuario = UsuarioBuilderModoCerto.newBuilder().withNome("Usuario 1").build();
		Usuario usuario = umUsuario().agora();
		List<Filme> filmes = Arrays.asList(umFilme().comValor(5.0).agora());
		
		//acao
		Locacao locacao = service.alugarFilme(usuario, filmes);
		
		error.checkThat(locacao.getValor(), is(equalTo(5.0)));
		error.checkThat(locacao.getDataLocacao(), ehHoje());
		error.checkThat(locacao.getDataRetorno(), ehHojeComDiferencaDias(1));
	}

	
	@Test(expected = FilmeSemEstoqueException.class)
	public void naoDeveAlugarFilmeSemEstoque() throws Exception{
		//cenario
		Usuario usuario = umUsuario().agora();
		umFilme();
		// List<Filme> filmes = Arrays.asList(umFilme().semEstoque().agora());
		List<Filme> filmes = Arrays.asList(umFilmeSemEstoque().agora());

		//acao
		service.alugarFilme(usuario, filmes);
	}
	
	@Test
	public void naoDeveAlugarFilmeSemUsuario() throws FilmeSemEstoqueException{
		//cenario
		List<Filme> filmes = Arrays.asList(umFilme().agora());
		//acao
		try {
			service.alugarFilme(null, filmes);
			Assert.fail();
		} catch (LocadoraException e) {
			assertThat(e.getMessage(), is("Usuario vazio"));
		}
	}
	
	@Test
	public void naoDeveAlugarFilmeSemFilme() throws FilmeSemEstoqueException, LocadoraException{
		//cenario
		Usuario usuario = umUsuario().agora();
		
		exception.expect(LocadoraException.class);
		exception.expectMessage("Filme vazio");
		
		//acao
		service.alugarFilme(usuario, null);
		
	}
	
//	@Test
//	public void devePagar75PctNoFilme3() throws FilmeSemEstoqueException, LocadoraException{
//		//cenario
//		Usuario usuario = umUsuario().agora();
//		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 4.0), 
//											new Filme("Filme 2", 2, 4.0), 
//											new Filme("Filme 3", 2, 4.0));
//	
//		//acao
//		Locacao resultado = service.alugarFilme(usuario, filmes);
//		
//		//verificacao
//		assertThat(resultado.getValor(), is(11.0));
//	}
//	
//	@Test
//	public void devePagar50PctNoFilme4() throws FilmeSemEstoqueException, LocadoraException{
//		//cenario
//		Usuario usuario = umUsuario().agora();
//		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 4.0), 
//											new Filme("Filme 2", 2, 4.0), 
//											new Filme("Filme 3", 2, 4.0), 
//											new Filme("Filme 4", 2, 4.0));
//	
//		//acao
//		Locacao resultado = service.alugarFilme(usuario, filmes);
//		
//		//verificacao
//		assertThat(resultado.getValor(), is(13.0));
//	}
//	
//	@Test
//	public void devePagar75PctNoFilme5() throws FilmeSemEstoqueException, LocadoraException{
//		//cenario
//		Usuario usuario = umUsuario().agora();
//		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 4.0), 
//											new Filme("Filme 2", 2, 4.0), 
//											new Filme("Filme 3", 2, 4.0), 
//											new Filme("Filme 4", 2, 4.0), 
//											new Filme("Filme 5", 2, 4.0));
//	
//		//acao
//		Locacao resultado = service.alugarFilme(usuario, filmes);
//		
//		//verificacao
//		assertThat(resultado.getValor(), is(14.0));
//	}
//	
//	@Test
//	public void devePagar0PctNoFilme6() throws FilmeSemEstoqueException, LocadoraException{
//		//cenario
//		Usuario usuario = umUsuario().agora();
//		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 4.0), 
//											new Filme("Filme 2", 2, 4.0), 
//											new Filme("Filme 3", 2, 4.0), 
//											new Filme("Filme 4", 2, 4.0), 
//											new Filme("Filme 5", 2, 4.0),
//											new Filme("Filme 6", 2, 4.0));
//	
//		//acao
//		Locacao resultado = service.alugarFilme(usuario, filmes);
//		
//		//verificacao
//		assertThat(resultado.getValor(), is(14.0));
//	}
	
	@Test
	//@Ignore
	public void deveDevolverNaSegundaAoAlugarNoSabado() throws FilmeSemEstoqueException, LocadoraException{
		Assume.assumeTrue(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));
		
		//cenario
		Usuario usuario = umUsuario().agora();
		List<Filme> filmes = Arrays.asList(umFilme().agora());
		
		//acao
		Locacao retorno = service.alugarFilme(usuario, filmes);
		
		//verificacao
		assertThat(retorno.getDataRetorno(), caiNumaSegunda());
	}                                                    
	
	public static void main(String[] args) {
		new BuilderMaster().gerarCodigoClasse(Locacao.class);
	}
	
}

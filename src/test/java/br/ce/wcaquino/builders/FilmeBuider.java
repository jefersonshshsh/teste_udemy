package br.ce.wcaquino.builders;

import br.ce.wcaquino.entidades.Filme;

public class FilmeBuider {

	private Filme filme;
	
	private FilmeBuider(){}
	
	public static FilmeBuider umFilme() {
		FilmeBuider builder = new FilmeBuider();
		builder.filme = new Filme();
		builder.filme.setEstoque(2);
		builder.filme.setNome("Filme 1");
		builder.filme.setPrecoLocacao(4.0);
		return builder;
	}
	
	public static FilmeBuider umFilmeSemEstoque() {
		FilmeBuider builder = new FilmeBuider();
		builder.filme = new Filme();
		builder.filme.setEstoque(0);
		builder.filme.setNome("Filme 1");
		builder.filme.setPrecoLocacao(4.0);
		return builder;
	}
	
	public FilmeBuider semEstoque(){
		filme.setEstoque(0);
		return this;
	}
	
	public FilmeBuider comValor(Double valor){
		filme.setPrecoLocacao(valor);
		return this;
	}
	
	public Filme agora() {
		return filme;
	}

}

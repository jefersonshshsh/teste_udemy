package br.ce.wcaquino.builders;

import br.ce.wcaquino.entidades.Usuario;

public class UsuarioBuilderModoCerto {

	private Usuario usuario;	

	private UsuarioBuilderModoCerto(){
		usuario = new Usuario();
		usuario.setNome("Usuario 1");
	}

	public UsuarioBuilderModoCerto withNome(String nome) {
		usuario.setNome(nome);
		return this;
	}
	public static UsuarioBuilderModoCerto newBuilder(){
		return new UsuarioBuilderModoCerto();
	}
	public static UsuarioBuilderModoCerto umUsuario(){
		return new UsuarioBuilderModoCerto().withNome("Usuario 1");
	}
	public static UsuarioBuilderModoCerto doisUsuario(){
		return new UsuarioBuilderModoCerto().withNome("Usuario 2");
	}
	public static UsuarioBuilderModoCerto tresUsuario(){
		return new UsuarioBuilderModoCerto().withNome("Usuario 3");
	}
	public Usuario build(){
		return usuario;
	}
		
}

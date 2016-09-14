package br.edu.bsi.sistema.recursos;

public class Movie {
	private String nome;
	private String diretor;
	private String genero;
	private int duracao;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDiretor() {
		return diretor;
	}
	public void setDiretor(String diretor) {
		this.diretor = diretor;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public int getDuracao() {
		return duracao;
	}
	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}
	
	
	public Movie(String nome, String diretor, String genero, int duracao){
		this.nome= nome;
		this.diretor= diretor;
		this.genero = genero;
		this.duracao = duracao;
	}
	
	
}

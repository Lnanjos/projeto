package br.edu.bsi.sistema.recursos;

public class Movie {
	
	private String movie;
	private String directedBy;
	private String genres;
	private int runTime;
	
	public Movie(String movie,String directedBy, String genres, int runTime){
		this.movie = movie;
		this.directedBy = directedBy;
		this.genres = genres;
		this.runTime = runTime;
	}
	
	public String getNome() {
		return movie;
	}
	public void setNome(String movie) {
		this.movie = movie;
	}
	public String getDiretor() {
		return directedBy;
	}
	public void setDiretor(String directedBy) {
		this.directedBy = directedBy;
	}
	public String getGenero() {
		return genres;
	}
	public void setGenero(String genres) {
		this.genres = genres;
	}
	public int getDur() {
		return runTime;
	}
	public void setDur(int runTime) {
		this.runTime = runTime;
	}
	
	
	
}

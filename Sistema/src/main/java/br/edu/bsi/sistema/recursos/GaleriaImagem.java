package br.edu.bsi.sistema.recursos;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class GaleriaImagem {

	// 1ºPasso: cria uma List/Array de Strings
	// Oprime acha as imagens pelo nome delas, para isso temos que salvar elas
	private List<String> images;

	// O post é utilizado para executar a implementação
	@PostConstruct
	public void init() {
		images = new ArrayList<String>();
		// A cada iteração adiciona um nome de imagem a lista
		for (int i = 1; i <= 12; i++) {
			images.add("nature" + i + ".jpg");
			// usa o nome igual e so muda o numero da imagem para o comando de
			// repetição
		}
	}

	public List<String> getImages() {
		return images;
		// O get vai retornar as imagens, pegar e transpor para a galeria
	}
}

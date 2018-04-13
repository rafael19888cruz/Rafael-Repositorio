package edu.br.utfpr.http.utils.response;

import java.io.File;

/**
 * Interface para criação de respostas
 * 
 * 
  */
public interface Response {
	/**
	 * Método que contém devolve a resposta
	 * @return resposta
	 */
	public abstract String respond(File arquivo);
	public abstract String respond();
	public abstract String responde();

}
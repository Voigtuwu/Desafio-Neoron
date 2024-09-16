package br.com.neoron.exeception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomException  extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2915436441182707465L;

	// Construtor para enviar a mensagem personalizada
	public CustomException(String message) {
		super(message);
	}

}

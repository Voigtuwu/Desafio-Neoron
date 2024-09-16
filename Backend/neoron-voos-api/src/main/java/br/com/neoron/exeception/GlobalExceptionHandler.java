package br.com.neoron.exeception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
		
		// Manipulador para a exceção ResourceNotFound
		@ExceptionHandler(ResourceNotFoundException.class)
		public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException e) {
			// Retorna uma respostar HTTP com o status 400 e a mensagem de erro
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
}

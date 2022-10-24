package br.com.serratec.eCommerce.exception;

public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException(String mensagem) {
		super(mensagem);
	}
}

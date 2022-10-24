package br.com.serratec.eCommerce.exception;

public class ResourceBadRequestException extends RuntimeException {

	public ResourceBadRequestException(String mensagem) {
		super(mensagem);
	}
}

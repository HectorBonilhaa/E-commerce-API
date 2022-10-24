package br.com.serratec.eCommerce.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.serratec.eCommerce.exception.ResourceBadRequestException;
import br.com.serratec.eCommerce.exception.ResourceNotFoundException;
import br.com.serratec.eCommerce.model.error.MensagemError;
import br.com.serratec.eCommerce.utils.ConversorDeData;



@ControllerAdvice
public class RestExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<MensagemError> handlerResourceNotFoundException(ResourceNotFoundException ex){
		
		String dataHora = ConversorDeData.converterDateParaDataEHora(new Date());
		
		//Construo o objeto de erro com base na exception.
		MensagemError erro = new MensagemError(dataHora, 404, "Not Found", ex.getMessage());
		
		//Aqui eu estou devolvendo o objeto de erro montado com o status específico que desejo, neste caso o not found
		return new ResponseEntity<>(erro, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ResourceBadRequestException.class)
	public ResponseEntity<MensagemError> handlerResourceBadRequestException(ResourceBadRequestException ex){
		
		String dataHora = ConversorDeData.converterDateParaDataEHora(new Date());
		//Construo o objeto de erro com base na exception.
		MensagemError erro = new MensagemError(dataHora, 400, "Bad Request", ex.getMessage());
		
		//Aqui eu estou devolvendo o objeto de erro montado com o status específico que desejo, neste caso o not found
		return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
	}
	
	//Tratamento geral para qualquer exception não tratada 
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MensagemError> handlerResourceBadRequestException(Exception ex){
		
		String dataHora = ConversorDeData.converterDateParaDataEHora(new Date());
		//Construo o objeto de erro com base na exception.
		MensagemError erro = new MensagemError(dataHora, 500, "Internal Server Error", ex.getMessage());
		
		//Aqui eu estou devolvendo o objeto de erro montado com o status específico que desejo, neste caso o not found
		return new ResponseEntity<>(erro, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

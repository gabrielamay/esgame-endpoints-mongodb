package br.com.fiap.esgames_endpoints.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class SeloJaExistenteException extends RuntimeException {

    public SeloJaExistenteException(String mensagem) {
        super(mensagem);
    }

}
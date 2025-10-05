package br.com.fiap.esgames_endpoints.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class MissaoJaExistenteException extends RuntimeException {

    public MissaoJaExistenteException(String mensagem) {
        super(mensagem);
    }

}
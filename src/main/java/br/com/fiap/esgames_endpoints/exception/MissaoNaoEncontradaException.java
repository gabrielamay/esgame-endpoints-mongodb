package br.com.fiap.esgames_endpoints.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MissaoNaoEncontradaException extends RuntimeException {

    public MissaoNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

}
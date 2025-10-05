package br.com.fiap.esgames_endpoints.advice;

import br.com.fiap.esgames_endpoints.exception.MissaoJaExistenteException;
import br.com.fiap.esgames_endpoints.exception.MissaoNaoEncontradaException;
import br.com.fiap.esgames_endpoints.exception.SeloJaExistenteException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> manusearArgumentosInvalidos(MethodArgumentNotValidException erro) {

        Map<String, String> mapaDeErro = new HashMap<>();
        List<FieldError> campoErro = erro.getBindingResult().getFieldErrors();

        for (FieldError fe : campoErro) {
            mapaDeErro.put(fe.getField(), fe.getDefaultMessage());
        }
        return mapaDeErro;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Map<String, String> manusearIntegridadeDados() {
        Map<String, String> mapaErro = new HashMap<>();
        mapaErro.put("erro", "Usuário já está cadastrado.");
        return mapaErro;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(MissaoNaoEncontradaException.class)
    public Map<String, String> missaoNaoEncontrada(MissaoNaoEncontradaException ex) {
        Map<String, String> erro = new HashMap<>();
        erro.put("erro", ex.getMessage());
        return erro;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(MissaoJaExistenteException.class)
    public Map<String, String> missaoJaExistente(MissaoJaExistenteException ex) {
        Map<String, String> erro = new HashMap<>();
        erro.put("erro", ex.getMessage());
        return erro;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(SeloJaExistenteException.class)
    public Map<String, String> seloJaExistente(SeloJaExistenteException ex) {
        Map<String, String> erro = new HashMap<>();
        erro.put("erro", ex.getMessage());
        return erro;
    }
}

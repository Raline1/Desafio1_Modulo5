package br.com.zup.GerenciadorDeContas.config;

import br.com.zup.GerenciadorDeContas.excecoes.IdNaoExisteException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)

    public List<MensagemDeErro> tratarExcecoesValidacao(MethodArgumentNotValidException exception) {
        List<MensagemDeErro> mensagemDeErros = new ArrayList<>();
        for (FieldError fieldError : exception.getFieldErrors()) {
            mensagemDeErros.add(new MensagemDeErro(fieldError.getDefaultMessage()));
        }
        return mensagemDeErros;
    }

    @ExceptionHandler(IdNaoExisteException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public MensagemDeErro tratarExcecaoDeIdNaoExisteException(IdNaoExisteException exception){
        return new MensagemDeErro(exception.getMessage());
    }
}

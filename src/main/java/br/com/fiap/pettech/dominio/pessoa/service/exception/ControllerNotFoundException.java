package br.com.fiap.pettech.dominio.pessoa.service.exception;

public class ControllerNotFoundException extends RuntimeException {
    public ControllerNotFoundException(String message){
        super(message);
    }
}

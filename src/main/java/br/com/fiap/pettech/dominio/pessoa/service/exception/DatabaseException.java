package br.com.fiap.pettech.dominio.pessoa.service.exception;

public class DatabaseException extends RuntimeException{
    public DatabaseException(String msg){
        super(msg);
    }
}

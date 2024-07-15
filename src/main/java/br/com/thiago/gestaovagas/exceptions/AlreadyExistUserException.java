package br.com.thiago.gestaovagas.exceptions;

public class AlreadyExistUserException extends RuntimeException {
    public AlreadyExistUserException() {
        super("Usuário já existe");
    }
}

package br.edu.ifpr.foz.biblioteca.exceptions;


public class DatabaseIntegrityException extends RuntimeException {

    public DatabaseIntegrityException(String msg){
        super(msg);
    }

}

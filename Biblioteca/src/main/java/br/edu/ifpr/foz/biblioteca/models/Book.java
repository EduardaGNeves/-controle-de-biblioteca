package br.edu.ifpr.foz.biblioteca.models;

import java.time.LocalDate;

public class Book {

    private Integer id;
    private String nome;
    private LocalDate dataCriacao;
    private Autor autor;
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getStatus() {return status;}

    public void setStatus(String status) {this.status = status;}


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }

}

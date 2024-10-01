package br.edu.ifpr.foz.biblioteca.models;

public enum Status {
    disponivel(1), emprestado(2), indisponivel(3);

    private final int codigo;

    Status(int codigo) {
        this.codigo = codigo;
    }
    public int getCodigo() {
        return codigo;
    }
}

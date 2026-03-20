package model;

public record Cliente(String nome) {
    public Cliente {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome. do cliente nao pode ser vazio.");
        }
    }
}
package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Banco {
    private final String nome;
    private final List<Conta> contas;

    public Banco(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome do banco nao pode ser vazio.");
        }
        this.nome = nome;
        this.contas = new ArrayList<>();
    }

    public void adicionarConta(Conta conta) {
        if (conta == null) {
            throw new IllegalArgumentException("Conta nao pode ser nula.");
        }
        contas.add(conta);
    }

    public void listarContas() {
        System.out.println("\n=== CONTAS DO BANCO " + nome.toUpperCase() + " ===");
        contas.forEach(conta -> System.out.printf("Titular: %s | Agência: %d | Número: %d | Saldo: R$ %s%n",
                conta.getCliente().nome(),
                conta.getAgencia(),
                conta.getNumero(),
                conta.getSaldo()));
    }

    public String getNome() {
        return nome;
    }

    public List<Conta> getContas() {
        return Collections.unmodifiableList(contas);
    }
}
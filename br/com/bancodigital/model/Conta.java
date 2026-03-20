package model;

import exception.SaldoInsuficienteException;
import exception.ValorInvalidoException;
import service.IConta;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Conta implements IConta {

    private static final int AGENCIA_PADRAO = 1;
    private static int sequencial = 1;

    protected final int agencia;
    protected final int numero;
    protected final Cliente cliente;
    protected BigDecimal saldo;
    protected final List<Transacao> historico;

    protected Conta(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo.");
        }
        this.agencia = AGENCIA_PADRAO;
        this.numero = sequencial++;
        this.cliente = cliente;
        this.saldo = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_EVEN);
        this.historico = new ArrayList<>();
    }

    @Override
    public void depositar(BigDecimal valor) {
        validarValor(valor);
        saldo = saldo.add(valor);
        registrarTransacao(TipoTransacao.DEPOSITO, valor, "Depósito realizado");
    }

    @Override
    public void sacar(BigDecimal valor) {
        validarValor(valor);
        validarSaldo(valor);
        saldo = saldo.subtract(valor);
        registrarTransacao(TipoTransacao.SAQUE, valor, "Saque realizado");
    }

    @Override
    public void transferir(BigDecimal valor, Conta destino) {
        validarValor(valor);

        if (destino == null) {
            throw new IllegalArgumentException("Conta de destino não pode ser nula.");
        }

        if (this.equals(destino)) {
            throw new IllegalArgumentException("Não é permitido transferir para a mesma conta.");
        }

        validarSaldo(valor);

        saldo = saldo.subtract(valor);
        destino.saldo = destino.saldo.add(valor);

        registrarTransacao(TipoTransacao.TRANSFERENCIA_ENVIADA, valor,
                "Transferência enviada para conta " + destino.numero);

        destino.registrarTransacao(TipoTransacao.TRANSFERENCIA_RECEBIDA, valor,
                "Transferência recebida da conta " + this.numero);
    }

    protected void validarValor(BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValorInvalidoException("O valor deve ser maior que zero.");
        }
    }

    protected void validarSaldo(BigDecimal valor) {
        if (saldo.compareTo(valor) < 0) {
            throw new SaldoInsuficienteException("Saldo insuficiente para a operação.");
        }
    }

    protected void registrarTransacao(TipoTransacao tipo, BigDecimal valor, String descricao) {
        historico.add(new Transacao(
                tipo,
                valor.setScale(2, RoundingMode.HALF_EVEN),
                java.time.LocalDateTime.now(),
                descricao));
    }

    protected void imprimirInfosComuns() {
        System.out.println("Titular: " + cliente.nome());
        System.out.println("Agência: " + agencia);
        System.out.println("Número: " + numero);
        System.out.println("Saldo: R$ " + saldo.setScale(2, RoundingMode.HALF_EVEN));
    }

    protected void imprimirHistorico() {
        System.out.println("---- Histórico ----");
        if (historico.isEmpty()) {
            System.out.println("Nenhuma transação realizada.");
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        for (Transacao transacao : historico) {
            System.out.printf(
                    "%s | %-24s | R$ %s | %s%n",
                    transacao.dataHora().format(formatter),
                    transacao.tipo(),
                    transacao.valor(),
                    transacao.descricao());
        }
    }

    public List<Transacao> getHistorico() {
        return Collections.unmodifiableList(historico);
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public int getAgencia() {
        return agencia;
    }

    public int getNumero() {
        return numero;
    }

    public Cliente getCliente() {
        return cliente;
    }

    @Override
    public abstract void imprimirExtrato();
}
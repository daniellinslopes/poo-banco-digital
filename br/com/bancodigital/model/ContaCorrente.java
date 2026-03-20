package model;

import java.math.BigDecimal;

public class ContaCorrente extends Conta {
    private static final BigDecimal TAXA_SAQUE = new BigDecimal("1.50");

    public ContaCorrente(Cliente cliente) {
        super(cliente);
    }

    @Override
    public void sacar(BigDecimal valor) {
        validarValor(valor);
        BigDecimal total = valor.add(TAXA_SAQUE);
        validarSaldo(total);

        saldo = saldo.subtract(valor);
        saldo = saldo.subtract(TAXA_SAQUE);

        registrarTransacao(TipoTransacao.SAQUE, valor, "Saque em conta corrente");
        registrarTransacao(TipoTransacao.TAXA, TAXA_SAQUE, "Taxa de saque");
    }

    @Override
    public void imprimirExtrato() {
        System.out.println("\n=== EXTRATO CONTA CORRENTE ===");
        imprimirInfosComuns();
        imprimirHistorico();
    }
}
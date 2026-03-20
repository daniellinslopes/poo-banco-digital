package model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ContaPoupanca extends Conta {
    public ContaPoupanca(Cliente cliente) {
        super(cliente);
    }

    public void aplicarRendimento(BigDecimal taxaPercentual) {
        validarValor(taxaPercentual);

        BigDecimal rendimento = saldo
                .multiply(taxaPercentual)
                .divide(new BigDecimal("100"), 2, RoundingMode.HALF_EVEN);

        saldo = saldo.add(rendimento);
        registrarTransacao(TipoTransacao.RENDIMENTO, rendimento, "Rendimento aplicado de " + taxaPercentual + "%"

        );
    }

    @Override
    public void imprimirExtrato() {
        System.out.println("\n=== EXTRATO CONTA POUPANÇA ===");
        imprimirInfosComuns();
        imprimirHistorico();
    }
}
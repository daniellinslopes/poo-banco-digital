import model.Banco;
import model.Cliente;
import model.Conta;
import model.ContaCorrente;
import model.ContaPoupanca;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco("Banco Digital NekoEspher");

        Cliente cliente1 = new Cliente("José Ricardo");
        Cliente cliente2 = new Cliente("Amélia Silva");

        Conta ccJose = new ContaCorrente(cliente1);
        ContaPoupanca poupancaJose = new ContaPoupanca(cliente1);

        Conta ccAmelia = new ContaCorrente(cliente2);

        banco.adicionarConta(ccJose);
        banco.adicionarConta(poupancaJose);
        banco.adicionarConta(ccAmelia);

        ccJose.depositar(new BigDecimal("1000.00"));
        ccJose.transferir(new BigDecimal("250.00"), poupancaJose);
        poupancaJose.aplicarRendimento(new BigDecimal("2.5"));
        ccJose.sacar(new BigDecimal("100.00"));

        ccAmelia.depositar(new BigDecimal("500.00"));
        ccAmelia.transferir(new BigDecimal("120.00"), ccJose);

        banco.listarContas();

        ccJose.imprimirExtrato();
        poupancaJose.imprimirExtrato();
        ccAmelia.imprimirExtrato();
    }
}

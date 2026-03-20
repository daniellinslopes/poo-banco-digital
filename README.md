# 💳 Banco Digital em Java 21

![Java](https://img.shields.io/badge/Java-21-orange)
![POO](https://img.shields.io/badge/POO-Orientação%20a%20Objetos-blue)
![Status](https://img.shields.io/badge/status-concluído-green)
![License](https://img.shields.io/badge/license-MIT-lightgrey)

Projeto prático de **Banco Digital** desenvolvido em **Java 21**, com foco na aplicação dos conceitos de **Programação Orientada a Objetos (POO)** no contexto bancário.

Este projeto foi inspirado no desafio proposto pela DIO e tem como objetivo reforçar os pilares da orientação a objetos por meio da modelagem de contas bancárias, clientes, transações e operações financeiras.

---

## 📌 Objetivo

O objetivo deste projeto é aplicar, na prática, os conceitos fundamentais de **POO em Java**, construindo um sistema bancário simples, funcional e organizado, com possibilidade de expansão e melhorias.

---

## 🚀 Funcionalidades

- Criar clientes
- Criar contas bancárias
- Conta corrente
- Conta poupança
- Realizar depósitos
- Realizar saques
- Realizar transferências entre contas
- Exibir extrato
- Registrar histórico de transações
- Aplicar rendimento na poupança
- Cobrar taxa de saque na conta corrente
- Validar regras de negócio com exceções personalizadas

---

## 🧠 Conceitos de POO aplicados

### Abstração
A classe `Conta` representa a estrutura base de uma conta bancária, reunindo atributos e comportamentos comuns.

### Encapsulamento
Os dados das contas são protegidos e manipulados por métodos específicos, garantindo maior segurança e controle.

### Herança
As classes `ContaCorrente` e `ContaPoupanca` herdam de `Conta`, reaproveitando atributos e comportamentos comuns.

### Polimorfismo
O sistema permite tratar diferentes tipos de conta de forma genérica através da classe base e da interface de operações.

---

## 🛠️ Tecnologias utilizadas

- **Java 21**
- **Programação Orientada a Objetos**
- **BigDecimal** para operações monetárias
- **Record** para modelagem de dados simples
- **Enum** para tipos de transação
- **Exceções personalizadas**
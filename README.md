# ZygmaChain

> Uma blockchain educacional e modular construída em Java, com foco em arquitetura P2P, contratos inteligentes, segurança criptográfica, testes automatizados e infraestrutura com CI/CD e Docker.

---

## Status do Projeto

**Em desenvolvimento** — Infraestrutura básica, blockchain funcional, carteiras, transações e testes automatizados implementados.

---

## Estrutura do Projeto

```
ZygmaChain/  
├── src/
│   ├── blockchain/         # Blocos, Blockchain, Transações
│   ├── crypto/             # ECDSA, hashing, segurança
│   ├── wallet/             # Gerenciamento de carteiras
├── test/                   # Testes unitários e integração
├── .gitignore
├── README.md
├── LICENSE
├── build.gradle
└── Dockerfile
```

---

## Funcionalidades implementadas

- Estrutura básica do projeto com Gradle.
- Blockchain funcional:
  - Classe `Block` com `previousHash`, `timestamp`, `data`, `nonce` e `hash`.
  - Classe `Blockchain` com validação, mineração e encadeamento de blocos.
- Carteira digital (`Wallet`):
  - Geração de chaves ECDSA.
  - Endereço gerado por hash da chave pública.
  - Assinatura e verificação de mensagens.
- Transações (`Transaction`):
  - Criação, assinatura e validação de transações entre carteiras.
  - Teste de adulteração de transação.
- Utilitários criptográficos:
  - `HashUtils` para SHA-256.
  - `ECDSA` para geração de chaves, assinatura e verificação.
- Testes automatizados com JUnit:
  - Testes de bloco, blockchain, carteira, ECDSA e transações.
  - Garantia de integridade, assinatura e validação.

---

## Tecnologias e Boas Práticas

| Categoria        | Ferramentas                    |
| ---------------- | ------------------------------ |
| **Linguagem**    | Java 17                        |
| **Build Tool**   | Gradle                         |
| **Testes**       | JUnit 5                        |


---

## Como rodar os testes atualmente

No terminal, execute:

```bash
./gradlew test

```

Saída esperada:

```
BUILD SUCCESSFUL in Xs
N actionable tasks: N executed

```

----------

## Próximos Passos

-   Implementar rede P2P para comunicação entre nós.
    
-   Adicionar contratos inteligentes.
    
-   Integrar Docker e CI/CD.
    
-   Melhorar interface de usuário e relatórios.
    

----------
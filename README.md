# 🌐⛓️ ZygmaChain ⛓️🌐

> Uma blockchain educacional e modular construída em Java, com foco em arquitetura P2P, contratos inteligentes, segurança criptográfica, testes automatizados e infraestrutura com CI/CD e Docker.

---

## 🧱 Status do Projeto

🚧 **Em desenvolvimento** — Primeiros blocos e infraestrutura básica criados com sucesso.

---

## 🗂️ Estrutura do Projeto

```
ZygmaChain/  
├── src/  
│ ├── blockchain/ # Blocos, Blockchain, Transações  
├── test/ # Testes unitários e integração  
├── .gitignore  
├── README.md  
├── build.gradle 
└── Dockerfile
```

---

## ✅ Funcionalidades implementadas

- Estrutura básica do projeto com Gradle.
- Classe `Block` contendo:
  - `previousHash`, `timestamp`, `data`, `nonce` e `hash`.
  - Hash calculado usando algoritmo SHA-256.
- Utilitário `HashUtils` para hashing com SHA-256.
- Testes automatizados com JUnit:
  - Garantia de que hashes são determinísticos.
  - Verificação de alteração de hash ao mudar os dados.

---

## 🧠 Tecnologias e Boas Práticas

| Categoria        | Ferramentas                    |
| ---------------- | ------------------------------ |
| **Linguagem**    | Java 17                        |
| **Build Tool**   | Gradle                         |
| **Testes**       | JUnit 5                        |


---

## 🧪 Como rodar os testes atualmente

```bash
./gradlew test

```

Saída:

```
BUILD SUCCESSFUL in 39s
4 actionable tasks: 4 executed

```

----------

## 🚀 Próximos Passos

-   Implementar a classe `Blockchain`.
    
-   Criar lógica de validação e encadeamento de blocos.
    
-   Adicionar geração de carteira e chaves (ECDSA).
    
-   Começar a estrutura básica da rede P2P.
    

----------
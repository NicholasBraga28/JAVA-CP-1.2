
# Exercício DAO + Factory + Singleton — Entidade: Aluno

## Entidade escolhida
**Aluno**, com os atributos:
- `id` (chave primária, gerada automaticamente)
- `nome`
- `email`
- `curso`
- `notaMedia`

A tabela `alunos` é criada automaticamente no banco **SQLite** (arquivo `alunos.db`) na primeira execução — você não precisa criar nada manualmente em um servidor de banco.

## Estrutura de pacotes

```
DAO-Factory-Singleton-Aluno/
├── lib/
│   └── sqlite-jdbc-3.46.0.0.jar     (driver JDBC do SQLite — ver "Como baixar" abaixo)
├── src/
│   ├── model/
│   │   └── Aluno.java               (entidade)
│   ├── conexao/
│   │   └── ConexaoSingleton.java    (padrão Singleton)
│   ├── dao/
│   │   ├── AlunoDAO.java            (interface do DAO)
│   │   └── AlunoDAOImpl.java        (implementação do CRUD)
│   ├── factory/
│   │   └── DAOFactory.java          (padrão Factory)
│   └── main/
│       └── Main.java                (classe principal, demonstra o CRUD)
└── README.md
```

- `model` → a entidade.
- `conexao` → o Singleton que garante uma única conexão com o banco.
- `dao` → contrato (interface) + implementação real do acesso a dados.
- `factory` → centraliza a criação do DAO, injetando a conexão do Singleton.
- `main` → ponto de entrada que usa os três padrões juntos.

## Como cada padrão aparece aqui
- **Singleton**: `ConexaoSingleton.getInstancia()` sempre devolve a mesma conexão, criada apenas uma vez.
- **Factory**: `DAOFactory.criarAlunoDAO()` cria o `AlunoDAOImpl` já "pronto pra uso", passando a conexão do Singleton para dentro dele.
- **DAO**: `AlunoDAO` (interface) + `AlunoDAOImpl` (implementação) cobrem as 4 operações: salvar, buscar por id, listar todos, atualizar e deletar.

## Como baixar o driver do SQLite (obrigatório para rodar)
Este projeto usa SQLite porque não exige instalar um servidor de banco — só um arquivo `.jar` do driver.

1. Baixe o arquivo `sqlite-jdbc` (versão 3.46.0.0 ou mais recente) aqui:
   https://github.com/xerial/sqlite-jdbc/releases
   (procure o arquivo `sqlite-jdbc-3.46.0.0.jar` na seção "Assets" do release mais recente)
2. Coloque o `.jar` baixado dentro da pasta `lib/` deste projeto.

## Como executar

### Opção A — Pelo terminal
Na raiz do projeto (`DAO-Factory-Singleton-Aluno/`), rode:

```bash
# Compilar
javac -cp "lib/sqlite-jdbc-3.46.0.0.jar" -d out src/model/*.java src/conexao/*.java src/dao/*.java src/factory/*.java src/main/*.java

# Executar (Windows use ; no lugar de : no -cp)
java -cp "out:lib/sqlite-jdbc-3.46.0.0.jar" main.Main
```

No Windows (PowerShell/CMD), troque o `:` por `;`:
```bash
java -cp "out;lib/sqlite-jdbc-3.46.0.0.jar" main.Main
```

### Opção B — Pelo IntelliJ IDEA / Eclipse (mais fácil)
1. Crie um novo projeto Java e copie as pastas `src/` para dentro dele (mantendo os pacotes `model`, `conexao`, `dao`, `factory`, `main`).
2. Clique com o botão direito no projeto → *Open Module Settings* (ou *Build Path* no Eclipse) → adicione o `sqlite-jdbc-3.46.0.0.jar` como dependência/biblioteca externa.
3. Rode a classe `main.Main` (botão direito → Run).

Depois de rodar, um arquivo `alunos.db` vai aparecer na raiz do projeto — é o seu banco de dados, e pode abri-lo com o [DB Browser for SQLite](https://sqlitebrowser.org/) se quiser ver os dados visualmente.

## O que a Main faz ao rodar
1. Cria (salva) dois alunos.
2. Lista todos os alunos.
3. Busca um aluno específico pelo id.
4. Atualiza os dados desse aluno.
5. Deleta o outro aluno.
6. Lista novamente para mostrar o resultado final.
# JAVA-CP-1.2
=======


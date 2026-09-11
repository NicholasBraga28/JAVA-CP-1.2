package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Padrão SINGLETON.
 *
 * Garante que exista UMA ÚNICA instância de conexão com o banco de dados
 * compartilhada por toda a aplicação.
 *
 * Estamos usando SQLite (banco em arquivo, chamado "alunos.db") porque
 * não exige instalar um servidor de banco de dados separado — é a forma
 * mais simples de testar localmente.
 */
public class ConexaoSingleton {

    // A única instância da classe, guardada de forma estática
    private static ConexaoSingleton instancia;

    // A conexão JDBC que será compartilhada
    private Connection conexao;

    // URL do banco: será criado um arquivo "alunos.db" na raiz do projeto
    private static final String URL = "jdbc:sqlite:alunos.db";

    // Construtor PRIVADO: ninguém de fora pode fazer "new ConexaoSingleton()"
    private ConexaoSingleton() {
        try {
            conexao = DriverManager.getConnection(URL);
            criarTabelaSeNaoExistir();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados", e);
        }
    }

    /**
     * Método público e estático que devolve sempre a MESMA instância.
     * Se ainda não existir, cria uma vez só (lazy initialization).
     */
    public static synchronized ConexaoSingleton getInstancia() {
        if (instancia == null) {
            instancia = new ConexaoSingleton();
        }
        return instancia;
    }

    public Connection getConexao() {
        return conexao;
    }

    // Cria a tabela "alunos" automaticamente na primeira execução,
    // assim você não precisa criar o banco manualmente.
    private void criarTabelaSeNaoExistir() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS alunos (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT NOT NULL," +
                "email TEXT NOT NULL," +
                "curso TEXT NOT NULL," +
                "nota_media REAL NOT NULL" +
                ")";
        try (Statement stmt = conexao.createStatement()) {
            stmt.execute(sql);
        }
    }
}

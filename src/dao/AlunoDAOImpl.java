package dao;

import model.Aluno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementação concreta do DAO.
 * Aqui é onde de fato conversamos com o banco via JDBC (SQL puro),
 * usando a conexão que recebemos pronta (injetada) no construtor.
 */
public class AlunoDAOImpl implements AlunoDAO {

    private Connection conexao;

    // A conexão chega de fora (da Factory) em vez de ser criada aqui dentro.
    // Isso é o que chamamos de "injeção de dependência".
    public AlunoDAOImpl(Connection conexao) {
        this.conexao = conexao;
    }

    @Override
    public void salvar(Aluno aluno) {
        String sql = "INSERT INTO alunos (nome, email, curso, nota_media) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getEmail());
            stmt.setString(3, aluno.getCurso());
            stmt.setDouble(4, aluno.getNotaMedia());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar aluno", e);
        }
    }

    @Override
    public Aluno buscarPorId(int id) {
        String sql = "SELECT * FROM alunos WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearAluno(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar aluno por id", e);
        }
        return null; // não encontrado
    }

    @Override
    public List<Aluno> listarTodos() {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM alunos";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                alunos.add(mapearAluno(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar alunos", e);
        }
        return alunos;
    }

    @Override
    public void atualizar(Aluno aluno) {
        String sql = "UPDATE alunos SET nome = ?, email = ?, curso = ?, nota_media = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getEmail());
            stmt.setString(3, aluno.getCurso());
            stmt.setDouble(4, aluno.getNotaMedia());
            stmt.setInt(5, aluno.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar aluno", e);
        }
    }

    @Override
    public void deletar(int id) {
        String sql = "DELETE FROM alunos WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar aluno", e);
        }
    }

    // Método auxiliar: transforma uma linha do ResultSet em um objeto Aluno
    private Aluno mapearAluno(ResultSet rs) throws SQLException {
        return new Aluno(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("email"),
                rs.getString("curso"),
                rs.getDouble("nota_media")
        );
    }
}

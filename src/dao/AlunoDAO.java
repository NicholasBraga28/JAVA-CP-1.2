package dao;

import model.Aluno;
import java.util.List;

/**
 * Padrão DAO (Data Access Object) — a INTERFACE.
 * Define o "contrato": quais operações de acesso a dados existem,
 * sem se preocupar em como elas são implementadas.
 */
public interface AlunoDAO {

    void salvar(Aluno aluno);          // CREATE

    Aluno buscarPorId(int id);         // READ (um registro)

    List<Aluno> listarTodos();         // READ (todos os registros)

    void atualizar(Aluno aluno);       // UPDATE

    void deletar(int id);              // DELETE
}

package factory;

import conexao.ConexaoSingleton;
import dao.AlunoDAO;
import dao.AlunoDAOImpl;

/**
 * Padrão FACTORY.
 *
 * Centraliza a criação do DAO. Quem usa a Factory não precisa saber
 * de onde vem a conexão nem qual classe concreta implementa o DAO —
 * só pede "me dá um AlunoDAO pronto para uso".
 */
public class DAOFactory {

    public static AlunoDAO criarAlunoDAO() {
        // Pega a única instância de conexão (Singleton)
        // e injeta ela dentro da implementação do DAO.
        return new AlunoDAOImpl(ConexaoSingleton.getInstancia().getConexao());
    }
}

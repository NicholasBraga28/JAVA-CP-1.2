package main;

import dao.AlunoDAO;
import factory.DAOFactory;
import model.Aluno;

import java.util.List;

/**
 * Classe principal.
 * Demonstra o uso INTEGRADO dos três padrões:
 *  - Singleton (conexão única, usada por baixo dos panos)
 *  - Factory   (quem cria o DAO pronto pra uso)
 *  - DAO       (quem executa create / read / update / delete)
 */
public class Main {

    public static void main(String[] args) {

        // 1) Pedimos o DAO pronto para a Factory (ela cuida da conexão via Singleton)
        AlunoDAO alunoDAO = DAOFactory.criarAlunoDAO();

        System.out.println("===== CREATE (salvar) =====");
        Aluno aluno1 = new Aluno("Maria Silva", "maria.silva@email.com", "Engenharia de Software", 8.5);
        Aluno aluno2 = new Aluno("João Souza", "joao.souza@email.com", "Ciência da Computação", 7.2);
        alunoDAO.salvar(aluno1);
        alunoDAO.salvar(aluno2);
        System.out.println("Alunos salvos com sucesso!");

        System.out.println("\n===== READ (listar todos) =====");
        List<Aluno> alunos = alunoDAO.listarTodos();
        for (Aluno a : alunos) {
            System.out.println(a);
        }

        System.out.println("\n===== READ (buscar por id) =====");
        int idBuscado = alunos.get(0).getId();
        Aluno encontrado = alunoDAO.buscarPorId(idBuscado);
        System.out.println("Encontrado: " + encontrado);

        System.out.println("\n===== UPDATE (atualizar) =====");
        encontrado.setNotaMedia(9.8);
        encontrado.setCurso("Engenharia de Software - Pós");
        alunoDAO.atualizar(encontrado);
        Aluno atualizado = alunoDAO.buscarPorId(idBuscado);
        System.out.println("Depois de atualizar: " + atualizado);

        System.out.println("\n===== DELETE (deletar) =====");
        int idParaDeletar = alunos.get(1).getId();
        alunoDAO.deletar(idParaDeletar);
        System.out.println("Aluno de id " + idParaDeletar + " deletado.");

        System.out.println("\n===== LISTA FINAL =====");
        for (Aluno a : alunoDAO.listarTodos()) {
            System.out.println(a);
        }
    }
}

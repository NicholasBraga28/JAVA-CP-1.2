package model;

/**
 * Classe entidade Aluno.
 * Representa um registro da tabela "alunos" no banco de dados.
 * Atributos privados + getters e setters (encapsulamento).
 */
public class Aluno {

    private int id;
    private String nome;
    private String email;
    private String curso;
    private double notaMedia;

    // Construtor vazio (útil quando o ID ainda não existe, ex: antes de salvar)
    public Aluno() {
    }

    // Construtor sem id (para quando ainda vamos INSERIR um novo aluno)
    public Aluno(String nome, String email, String curso, double notaMedia) {
        this.nome = nome;
        this.email = email;
        this.curso = curso;
        this.notaMedia = notaMedia;
    }

    // Construtor completo (usado quando lemos um aluno que já existe no banco)
    public Aluno(int id, String nome, String email, String curso, double notaMedia) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.curso = curso;
        this.notaMedia = notaMedia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public double getNotaMedia() {
        return notaMedia;
    }

    public void setNotaMedia(double notaMedia) {
        this.notaMedia = notaMedia;
    }

    // Sobrescrevemos toString() só para facilitar a exibição no console (Main)
    @Override
    public String toString() {
        return "Aluno{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", curso='" + curso + '\'' +
                ", notaMedia=" + notaMedia +
                '}';
    }
}

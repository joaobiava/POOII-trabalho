package exemplo.trabalho1.model;

public class Disciplina {
    private int disciplina_id;
    private String nome;

    public Disciplina() {}

    public Disciplina(int disciplina_id, String nome) {
        this.disciplina_id = disciplina_id;
        this.nome = nome;
    }

    public int getDisciplina_id() {
        return disciplina_id;
    }

    public void setDisciplina_id(int disciplina_id) {
        this.disciplina_id = disciplina_id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Disciplina{" +
                "disciplina_id=" + disciplina_id +
                ", nome='" + nome + '\'' +
                '}';
    }
}

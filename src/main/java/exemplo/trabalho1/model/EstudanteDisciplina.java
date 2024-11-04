package exemplo.trabalho1.model;

public class EstudanteDisciplina {
    private Estudante estudante;
    private Disciplina disciplina;

    public EstudanteDisciplina() {}

    public EstudanteDisciplina(Disciplina disciplina, Estudante estudante) {
        this.disciplina = disciplina;
        this.estudante = estudante;
    }

    public Estudante getEstudante() {
        return estudante;
    }

    public void setEstudante(Estudante estudante) {
        this.estudante = estudante;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    @Override
    public String toString() {
        return "EstudanteDisciplina{" +
                "estudante=" + estudante +
                ", disciplina=" + disciplina +
                '}';
    }
}
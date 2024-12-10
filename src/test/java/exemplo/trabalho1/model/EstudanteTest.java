package exemplo.trabalho1.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EstudanteTest {

    @Test
    void getEstudanteID() {
        Estudante estudante1 = new Estudante();
        Estudante estudante2 = estudante1;

        estudante1.setEstudanteID(34);

        assertSame(estudante1, estudante2, "O Id deve ser o mesmo");
    }

    @Test
    void setEstudanteID() {
        Estudante estudante = new Estudante();
        estudante.setEstudanteID(-6);
        assertFalse(estudante.getEstudanteID() < 0, "O Id do estudante nao pode ser menor que 0");
    }

    @Test
    void getNome() {
        Estudante estudante = new Estudante();
        estudante.setNome("batata");

        assertNotNull(estudante.getNome());
    }

    @Test
    void setNome() {
        Estudante estudante = new Estudante();
        estudante.setNome("");
        assertFalse(estudante.getNome() != null && estudante.getNome().isEmpty(), "O estudante não deve ser válida se for null.");
    }
}
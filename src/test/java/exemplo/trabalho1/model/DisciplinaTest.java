package exemplo.trabalho1.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DisciplinaTest {

    @Test
    void getDisciplina_id() {
        Disciplina disciplina1 = new Disciplina();
        Disciplina disciplina2 = new Disciplina();

        disciplina2.setDisciplina_id(1);
        disciplina1.setDisciplina_id(1);

        assertNotSame(disciplina1.getDisciplina_id(), disciplina2.getDisciplina_id(), "Ambos devem se referir a id diferentes");
    }

    @Test
    void setDisciplina_id() {
        Disciplina disciplina = new Disciplina();
        disciplina.setDisciplina_id(1);

        assertTrue(disciplina.getDisciplina_id() == 1, "O Id deve ser igual a 1");

        disciplina.setDisciplina_id(-2);
        assertFalse(disciplina.getDisciplina_id() < 0, "Deve mostrar erro, a disciplina não pode ter id negativo");
    }

    @Test
    void getNome() {
        Disciplina disciplina1 = new Disciplina();
        disciplina1.setNome("abacate");
        Disciplina disciplina2 = disciplina1;

        assertNotSame(disciplina1.getNome(), disciplina2.getNome(), "Os nomes das disciplinas devem ser diferentes");
    }

    @Test
    void setNome() {
        Disciplina disciplina = new Disciplina();
        disciplina.setNome("xaruto");

        assertFalse(disciplina.getNome() != null && disciplina.getNome().isEmpty(), "A disciplina não deve ser válida se for nula.");
    }
}
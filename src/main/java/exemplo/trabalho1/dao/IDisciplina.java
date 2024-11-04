package exemplo.trabalho1.dao;

import exemplo.trabalho1.model.Disciplina;
import exemplo.trabalho1.model.Estudante;

import java.sql.SQLException;
import java.util.List;

public interface IDisciplina {
    public void inserir(Disciplina disciplina) throws SQLException;
    public void update(Disciplina disciplina) throws SQLException;
    public void delete(Disciplina disciplina) throws SQLException;
    public Disciplina consultar(int var) throws SQLException;
    public List<Disciplina> listar() throws SQLException;
    public Disciplina consultarPorNome(String nome) throws SQLException;
}

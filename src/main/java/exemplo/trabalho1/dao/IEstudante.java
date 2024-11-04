package exemplo.trabalho1.dao;

import java.sql.SQLException;
import java.util.List;

import exemplo.trabalho1.model.Estudante;

public interface IEstudante {
    public void inserir(Estudante estudante) throws SQLException;
    public void update(Estudante estudante) throws SQLException;
    public void delete(Estudante estudante) throws SQLException;
    public Estudante consultar(int var) throws SQLException;
    public List<Estudante> listar() throws SQLException;
    public Estudante consultarPorNome(String nome) throws SQLException;
}

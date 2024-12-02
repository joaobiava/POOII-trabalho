package exemplo.trabalho1.dao;

import exemplo.trabalho1.model.Disciplina;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaDAO implements IConst, IDisciplina{
    private String sql;

    private PreparedStatement prepararStatement(String sql) throws SQLException {
        Connection conexao = Conexao.getConexao(Conexao.stringDeConexao, Conexao.usuario, Conexao.senha);
        return conexao.prepareStatement(sql);
    }

    public void inserir(Disciplina disciplina) throws SQLException {
        sql = "INSERT INTO disciplina (nome) VALUES (?)";

        try (PreparedStatement pstmt = prepararStatement(sql)) {
            pstmt.setString(1, disciplina.getNome());
            pstmt.executeUpdate();
        }
    }

    public void update(Disciplina disciplina) throws SQLException {
        sql = "UPDATE disciplina SET nome = ? WHERE disciplina_id = ?";

        try (PreparedStatement pstmt = prepararStatement(sql))  {
            pstmt.setString(1, disciplina.getNome());
            pstmt.setInt(2, disciplina.getDisciplina_id());
            pstmt.executeUpdate();
        }
    }

    public void delete(Disciplina disciplina) throws SQLException {
        sql = "DELETE FROM disciplina WHERE disciplina_id = ?";

        try (PreparedStatement pstmt = prepararStatement(sql)) {

            pstmt.setInt(1, disciplina.getDisciplina_id());
            pstmt.executeUpdate();

        }
    }

    public Disciplina consultar(int disciplinaId) throws SQLException {
        sql = "select * from disciplina where disciplina_id = ?";

        try (PreparedStatement pstmt = prepararStatement(sql)) {
            pstmt.setInt(1, disciplinaId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()){
                Disciplina disciplina = new Disciplina();
                disciplina.setDisciplina_id(disciplinaId);
                disciplina.setDisciplina_id(rs.getInt("disciplina_id"));
                disciplina.setNome(rs.getString("nome"));
                return disciplina;
            } else {
                return null;
            }
        }
    }

    public Disciplina consultarPorNome(String nome) throws SQLException {
        sql = "select * from disciplina where nome ~* ?";

        try (PreparedStatement pstmt = prepararStatement(sql)) {
            pstmt.setString(1, nome);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()){
                Disciplina disciplina = new Disciplina();
                disciplina.setNome(nome);
                disciplina.setDisciplina_id(rs.getInt("disciplina_id"));
                disciplina.setNome(rs.getString("nome"));
                return disciplina;
            } else {
                return null;
            }
        }
    }

    public List<Disciplina> listar() throws SQLException {
        sql = "select * from disciplina";
        try (PreparedStatement pstmt = prepararStatement(sql);
            ResultSet rs = pstmt.executeQuery()){
            List<Disciplina> disciplinas = new ArrayList<>();

            while (rs.next()){
                Disciplina disciplina = new Disciplina();
                disciplina.setDisciplina_id(rs.getInt("disciplina_id"));
                disciplina.setNome(rs.getString("nome"));
                disciplinas.add(disciplina);
            }
            return disciplinas;
        }
    }

    public void adicionarEstudanteADisciplina(int estudanteId, int disciplinaId) throws SQLException {
        String sql = "INSERT INTO estudante_disciplina (estudante_id, disciplina_id) VALUES (?, ?)";
        try (PreparedStatement pstmt = prepararStatement(sql))  {
            pstmt.setInt(1, estudanteId);
            pstmt.setInt(2, disciplinaId);
            pstmt.executeUpdate();
        }
    }

    public boolean removerEstudanteDaDisciplina(int estudanteId, int disciplinaId) throws SQLException {
        String sql = "DELETE FROM estudante_disciplina WHERE estudante_id = ? AND disciplina_id = ?";
        try (PreparedStatement pstmt = prepararStatement(sql))  {
            pstmt.setInt(1, estudanteId);
            pstmt.setInt(2, disciplinaId);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }


}

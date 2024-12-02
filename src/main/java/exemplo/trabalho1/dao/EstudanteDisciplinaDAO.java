package exemplo.trabalho1.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import exemplo.trabalho1.model.Disciplina;
import exemplo.trabalho1.model.Estudante;
import exemplo.trabalho1.model.EstudanteDisciplina;

public class EstudanteDisciplinaDAO implements IConst {
    private String sql;

    private PreparedStatement prepararStatement(String sql) throws SQLException {
        Connection conexao = Conexao.getConexao(Conexao.stringDeConexao, Conexao.usuario, Conexao.senha);
        return conexao.prepareStatement(sql);
    }

    public void delete(EstudanteDisciplina estudanteDisciplina) throws SQLException {
        sql = "DELETE FROM estudante_disciplina WHERE estudante_id = ? AND disciplina_id = ?";

        try (PreparedStatement pstmt = prepararStatement(sql)) {
            pstmt.setInt(1, estudanteDisciplina.getEstudante().getEstudanteID());
            pstmt.setInt(2, estudanteDisciplina.getDisciplina().getDisciplina_id());
            pstmt.executeUpdate();
        }
    }

    public List<EstudanteDisciplina> listarTodos() throws SQLException {
        sql = "SELECT e.nome AS nomeEstudante, e.estudante_id, d.nome as nomeDisciplina, d.disciplina_id FROM estudante e " +
                "JOIN estudante_disciplina ed ON e.estudante_id = ed.estudante_id " +
                "JOIN disciplina d ON d.disciplina_id = ed.disciplina_id";

        try (PreparedStatement pstmt = prepararStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            List<EstudanteDisciplina> lista = new ArrayList<>();
            while (rs.next()) {
                Estudante estudante = new Estudante();
                estudante.setEstudanteID(rs.getInt("estudante_id"));
                estudante.setNome(rs.getString("nomeEstudante"));

                Disciplina disciplina = new Disciplina();
                disciplina.setDisciplina_id(rs.getInt("disciplina_id"));
                disciplina.setNome(rs.getString("nomeDisciplina"));

                lista.add(new EstudanteDisciplina(disciplina, estudante));
            }
            return lista;
        }
    }
}
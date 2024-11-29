package exemplo.trabalho1.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import exemplo.trabalho1.model.Disciplina;
import exemplo.trabalho1.model.Estudante;
import exemplo.trabalho1.model.EstudanteDisciplina;

public class EstudanteDisciplinaDAO implements IConst {
    private String sql;

    public void inserir(EstudanteDisciplina estudanteDisciplina) throws SQLException {
        sql = "INSERT INTO estudante_disciplina (estudante_id, disciplina_id) VALUES (?, ?)";

        try (Connection conexao = Conexao.getConexao(Conexao.stringDeConexao, Conexao.usuario, Conexao.senha);
             PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setInt(1, estudanteDisciplina.getEstudante().getEstudanteID());
            pstmt.setInt(2, estudanteDisciplina.getDisciplina().getDisciplina_id());
            pstmt.executeUpdate();
        }
    }

    public void delete(EstudanteDisciplina estudanteDisciplina) throws SQLException {
        sql = "DELETE FROM estudante_disciplina WHERE estudante_id = ? AND disciplina_id = ?";

        try (Connection conexao = Conexao.getConexao(Conexao.stringDeConexao, Conexao.usuario, Conexao.senha);
             PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setInt(1, estudanteDisciplina.getEstudante().getEstudanteID());
            pstmt.setInt(2, estudanteDisciplina.getDisciplina().getDisciplina_id());
            pstmt.executeUpdate();
        }
    }

    public List<Disciplina> listarDisciplinasPorEstudante(int estudanteId) throws SQLException {
        sql = "SELECT d.* FROM disciplina d " +
                "INNER JOIN estudante_disciplina ed ON d.disciplina_id = ed.disciplina_id " +
                "WHERE ed.estudante_id = ?";

        try (Connection conexao = Conexao.getConexao(Conexao.stringDeConexao, Conexao.usuario, Conexao.senha);
             PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setInt(1, estudanteId);
            ResultSet rs = pstmt.executeQuery();

            List<Disciplina> disciplinas = new ArrayList<>();
            while (rs.next()) {
                Disciplina disciplina = new Disciplina();
                disciplina.setDisciplina_id(rs.getInt("disciplina_id"));
                disciplina.setNome(rs.getString("nome"));
                disciplinas.add(disciplina);
            }
            return disciplinas;
        }
    }

    public List<Estudante> listarEstudantesPorDisciplina(int disciplinaId) throws SQLException {
        sql = "SELECT e.* FROM estudante e INNER JOIN estudante_disciplina ed ON e.estudante_id = ed.estudante_id WHERE ed.disciplina_id = ?";

        try (Connection conexao = Conexao.getConexao(Conexao.stringDeConexao, Conexao.usuario, Conexao.senha);
             PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setInt(1, disciplinaId);
            ResultSet rs = pstmt.executeQuery();

            List<Estudante> estudantes = new ArrayList<>();
            while (rs.next()) {
                Estudante estudante = new Estudante();
                estudante.setEstudanteID(rs.getInt("estudante_id"));
                estudante.setNome(rs.getString("nome"));
                estudantes.add(estudante);
            }
            return estudantes;
        }
    }

    public EstudanteDisciplina consultar(int estudanteId, int disciplinaId) throws SQLException {
        sql = "SELECT e.*, d.* FROM estudante e " +
                "JOIN estudante_disciplina ed ON e.estudante_id = ed.estudante_id " +
                "JOIN disciplina d ON d.disciplina_id = ed.disciplina_id " +
                "WHERE e.estudante_id = ? AND d.disciplina_id = ?";

        try (Connection conexao = Conexao.getConexao(Conexao.stringDeConexao, Conexao.usuario, Conexao.senha);
             PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setInt(1, estudanteId);
            pstmt.setInt(2, disciplinaId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Estudante estudante = new Estudante();
                estudante.setEstudanteID(rs.getInt("estudante_id"));
                estudante.setNome(rs.getString("nome"));

                Disciplina disciplina = new Disciplina();
                disciplina.setDisciplina_id(rs.getInt("disciplina_id"));
                disciplina.setNome(rs.getString("nome"));

                return new EstudanteDisciplina(disciplina, estudante);
            }
            return null; // Retorna null se não encontrar a relação
        }
    }

    public List<EstudanteDisciplina> listarTodos() throws SQLException {
        sql = "SELECT e.nome AS nomeEstudante, e.estudante_id, d.nome as nomeDisciplina, d.disciplina_id FROM estudante e " +
                "JOIN estudante_disciplina ed ON e.estudante_id = ed.estudante_id " +
                "JOIN disciplina d ON d.disciplina_id = ed.disciplina_id";

        try (Connection conexao = Conexao.getConexao(Conexao.stringDeConexao, Conexao.usuario, Conexao.senha);
             PreparedStatement pstmt = conexao.prepareStatement(sql);
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

    public List<EstudanteDisciplina> listarEstudantesPorDisciplinaId(int disciplinaId) throws SQLException {
        String sql = "SELECT * FROM estudante_disciplina WHERE disciplina_id = ?";
        List<EstudanteDisciplina> estudantesDisciplinas = new ArrayList<>();

        try (Connection conexao = Conexao.getConexao(Conexao.stringDeConexao, Conexao.usuario, Conexao.senha);
             PreparedStatement pstmt = conexao.prepareStatement(sql)) {

            pstmt.setInt(1, disciplinaId);
            ResultSet rs = pstmt.executeQuery();

            // Itera sobre o ResultSet e adiciona cada registro à lista
            while (rs.next()) {
                EstudanteDisciplina estudanteDisciplina = new EstudanteDisciplina();

                // Inicializa o objeto Estudante e Disciplina para evitar NullPointerException
                estudanteDisciplina.setEstudante(new Estudante());
                estudanteDisciplina.setDisciplina(new Disciplina());

                // Popula os dados do estudante e da disciplina
                estudanteDisciplina.getEstudante().setEstudanteID(rs.getInt("estudante_id"));
                estudanteDisciplina.getEstudante().setNome(rs.getString("nomeEstudante"));
                estudanteDisciplina.getDisciplina().setDisciplina_id(rs.getInt("disciplina_id"));
                estudanteDisciplina.getDisciplina().setNome(rs.getString("nomeDisciplina"));

                estudantesDisciplinas.add(estudanteDisciplina); // Adiciona o objeto à lista
            }
        }

        return estudantesDisciplinas; // Retorna a lista completa
    }
}
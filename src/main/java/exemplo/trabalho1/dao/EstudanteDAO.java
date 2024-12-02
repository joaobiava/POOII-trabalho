package exemplo.trabalho1.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import exemplo.trabalho1.model.*;
import exemplo.trabalho1.model.Estudante;

public class EstudanteDAO implements IEstudante, IConst {
    private String sql;

    private PreparedStatement prepararStatement(String sql) throws SQLException {
        Connection conexao = Conexao.getConexao(Conexao.stringDeConexao, Conexao.usuario, Conexao.senha);
        return conexao.prepareStatement(sql);
    }

    public void inserir(Estudante estudante) throws SQLException {
        sql = "INSERT INTO estudante (nome) VALUES (?)";

        try (PreparedStatement pstmt = prepararStatement(sql)) {
            pstmt.setString(1, estudante.getNome());
            pstmt.executeUpdate();
        }
    }

    public void update(Estudante estudante) throws SQLException {
        sql = "UPDATE estudante SET nome = ? WHERE estudante_id = ?";

        try (PreparedStatement pstmt = prepararStatement(sql)) {
            pstmt.setString(1, estudante.getNome());
            pstmt.setInt(2, estudante.getEstudanteID());
            pstmt.executeUpdate();
        }
    }

    public void delete(Estudante estudante) throws SQLException {
        sql = "DELETE FROM Estudante WHERE estudante_id = ?";

        try (PreparedStatement pstmt = prepararStatement(sql)){

            pstmt.setInt(1, estudante.getEstudanteID());
            pstmt.executeUpdate();

        }
    }

    public Estudante consultar(int estudanteId) throws SQLException {
        sql = "select * from estudante where estudante_Id = ?";

        try(PreparedStatement pstmt = prepararStatement(sql)){
            pstmt.setInt(1, estudanteId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()){
                Estudante estudante = new Estudante();
                estudante.setEstudanteID(estudanteId);
                estudante.setEstudanteID(rs.getInt("estudante_Id"));
                estudante.setNome(rs.getString("nome"));
                return estudante;
            } else {
                return null;
            }
        }
    }

    public Estudante consultarPorNome(String nome) throws SQLException {
        sql = "select * from estudante where nome ~* ?";

        try(PreparedStatement pstmt = prepararStatement(sql)){
            pstmt.setString(1, nome);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()){
                Estudante estudante = new Estudante();
                estudante.setNome(nome);
                estudante.setEstudanteID(rs.getInt("estudante_Id"));
                estudante.setNome(rs.getString("nome"));
                return estudante;
            } else {
                return null;
            }
        }
    }

    public List<Estudante> listar() throws SQLException {
        sql = "select * from estudante";
        try(PreparedStatement pstmt = prepararStatement(sql);
            ResultSet rs = pstmt.executeQuery()){
            List<Estudante> estudantes = new ArrayList<>();

            while (rs.next()){
                Estudante estudante = new Estudante();
                estudante.setEstudanteID(rs.getInt("estudante_Id"));
                estudante.setNome(rs.getString("nome"));
                estudantes.add(estudante);
            }
            return estudantes;
        }
    }
}

package exemplo.trabalho1.controller.estudanteDisciplina;

import exemplo.trabalho1.dao.EstudanteDisciplinaDAO;
import exemplo.trabalho1.model.EstudanteDisciplina;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

public class ConsultaEstudanteDisciplinaController {
    @FXML
    private TableView<EstudanteDisciplina> tabelaEstudantesDisciplinas; // Tabela para exibir relações Estudante-Disciplina

    @FXML
    private TableColumn<EstudanteDisciplina, String> colunaEstudanteId; // Coluna para o ID do estudante

    @FXML
    private TableColumn<EstudanteDisciplina, String> colunaEstudanteNome; // Coluna para o nome do estudante

    @FXML
    private TableColumn<EstudanteDisciplina, String> colunaDisciplinaId;

    @FXML
    private TableColumn<EstudanteDisciplina, String> colunaDisciplinaNome; // Coluna para o nome da disciplina

    private final EstudanteDisciplinaDAO estudanteDisciplinaDAO = new EstudanteDisciplinaDAO();

    @FXML
    private void initialize() {
        colunaDisciplinaNome.setCellValueFactory(dadoLinha -> {
            String disciplinaNome = dadoLinha.getValue().getDisciplina().getNome();
            return new SimpleStringProperty(disciplinaNome);
        });

        colunaDisciplinaId.setCellValueFactory(dadoLinha -> {
            String disciplinaId = Integer.toString(dadoLinha.getValue().getDisciplina().getDisciplina_id());
            return new SimpleStringProperty(disciplinaId);
        });

        colunaEstudanteNome.setCellValueFactory(dadoLinha -> {
            String estudanteNome = dadoLinha.getValue().getEstudante().getNome();
            return new SimpleStringProperty(estudanteNome);
        });

        colunaEstudanteId.setCellValueFactory(dadoLinha -> {
            String estudanteId = Integer.toString(dadoLinha.getValue().getEstudante().getEstudanteID());
            return new SimpleStringProperty(estudanteId);
        });
    }

    @FXML
    private void handleCarregarEstudantesDisciplinas() {
        try {
            // Lista todas as relações Estudante-Disciplina
            List<EstudanteDisciplina> estudantesDisciplinas = estudanteDisciplinaDAO.listarTodos();
            ObservableList<EstudanteDisciplina> observableEstudantesDisciplinas = FXCollections.observableArrayList(estudantesDisciplinas);
            tabelaEstudantesDisciplinas.setItems(observableEstudantesDisciplinas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

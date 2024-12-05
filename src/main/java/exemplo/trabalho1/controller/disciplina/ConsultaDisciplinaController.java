package exemplo.trabalho1.controller.disciplina;

import exemplo.trabalho1.dao.DisciplinaDAO;
import exemplo.trabalho1.model.Disciplina;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

public class ConsultaDisciplinaController {

    @FXML
    private TextField disciplinaIdField;

    @FXML
    private TableView<Disciplina> tabelaDisciplina;

    @FXML
    private TableColumn<Disciplina, Integer> colunaId;

    @FXML
    private TableColumn<Disciplina, String> colunaNome;

    private DisciplinaDAO disciplinaDAO = new DisciplinaDAO();

    @FXML
    public void initialize() {
        colunaId.setCellValueFactory(new PropertyValueFactory<>("disciplina_id"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
    }

    @FXML
    private void handleCarregarDisciplina() {
        try {
            List<Disciplina> disciplinas = disciplinaDAO.listar(); // Método que lista todos os estudantes
            ObservableList<Disciplina> observableDisciplinas = FXCollections.observableArrayList(disciplinas);
            tabelaDisciplina.setItems(observableDisciplinas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBuscarPorNome() {
        try {
            String nome = (disciplinaIdField.getText());
            Disciplina disciplina = disciplinaDAO.consultarPorNome(nome);
            if (disciplina != null) {
                tabelaDisciplina.setItems(FXCollections.observableArrayList(disciplina));
            } else {
                tabelaDisciplina.setItems(FXCollections.observableArrayList());
            }
        } catch (NumberFormatException e) {
            System.out.println("Nome Inválio");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package exemplo.trabalho1.controller.estudante;

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

import exemplo.trabalho1.dao.EstudanteDAO;
import exemplo.trabalho1.model.Estudante;

public class ConsultaEstudanteController {
    @FXML
    private TextField studentIdField;

    @FXML
    private TableView<Estudante> tabelaEstudantes;

    @FXML
    private TableColumn<Estudante, String> colunaId;

    @FXML
    private TableColumn<Estudante, String> colunaNome;

    private EstudanteDAO estudanteDAO = new EstudanteDAO();

    @FXML
    private void initialize() {
        colunaId.setCellValueFactory(dadoLinha -> {
            String estudanteId = Integer.toString(dadoLinha.getValue().getEstudanteID());
            return new SimpleStringProperty(estudanteId);
        });
        colunaId.setCellValueFactory(new PropertyValueFactory<>("estudanteID"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
    }

    @FXML
    private void handleCarregarEstudantes() {
        try {
            List<Estudante> estudantes = estudanteDAO.listar(); // Método que lista todos os estudantes
            ObservableList<Estudante> observableEstudantes = FXCollections.observableArrayList(estudantes);
            tabelaEstudantes.setItems(observableEstudantes);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBuscarPorNome() {
        try {
            String nome = (studentIdField.getText());
            Estudante estudante = estudanteDAO.consultarPorNome(nome);
            if (estudante != null) {
                tabelaEstudantes.setItems(FXCollections.observableArrayList(estudante));
            } else {
                tabelaEstudantes.setItems(FXCollections.observableArrayList());
            }
        } catch (NumberFormatException e) {
            System.out.println("Nome Inválio");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

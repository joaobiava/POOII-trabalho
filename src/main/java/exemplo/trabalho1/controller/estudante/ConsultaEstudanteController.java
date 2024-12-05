package exemplo.trabalho1.controller.estudante;

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

    private final EstudanteDAO estudanteDAO = new EstudanteDAO();

    @FXML
    private void initialize() {
        colunaId.setCellValueFactory(new PropertyValueFactory<>("estudanteID"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
    }

    private void atualizarTabela(List<Estudante> estudantes) {
        tabelaEstudantes.setItems(FXCollections.observableArrayList(estudantes));
    }

    @FXML
    private void handleCarregarEstudantes() {
        try {
            atualizarTabela(estudanteDAO.listar());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBuscarPorNome() {
        String nome = studentIdField.getText().trim();
        if (nome.isEmpty()) {
            System.err.println("O campo de nome não pode estar vazio.");
            return;
        }

        try {
            Estudante estudante = estudanteDAO.consultarPorNome(nome);
            atualizarTabela(estudante != null ? List.of(estudante) : List.of());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

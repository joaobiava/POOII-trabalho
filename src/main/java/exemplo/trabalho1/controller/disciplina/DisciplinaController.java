package exemplo.trabalho1.controller.disciplina;

import exemplo.trabalho1.dao.DisciplinaDAO;
import exemplo.trabalho1.model.Disciplina;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;

public class DisciplinaController {

    @FXML
    private Button btnConf;

    @FXML
    private TextField txtNomEst;

    @FXML
    private TextField idInput;

    @FXML
    private TextField nomeInput;

    @FXML
    private TextField disciplinaId;

    @FXML
    private TextField disciplinaIdField;

    @FXML
    private TableView<Disciplina> tabelaEstudantes;

    @FXML
    private TableColumn<Disciplina, Integer> colunaId;

    @FXML
    private TableColumn<Disciplina, String> colunaNome;

    private DisciplinaDAO disciplinaDAO = new DisciplinaDAO();

    @FXML
    public void initialize() {

    }

    @FXML
    private void btnIncluir(ActionEvent event) {
        Disciplina disciplina = new Disciplina();
        disciplina.setNome(txtNomEst.getText());

        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        try {
            disciplinaDAO.inserir(disciplina);
            Alert alert;
            alert = new Alert(Alert.AlertType.INFORMATION, "Você clicou no botão Confirmar", ButtonType.OK);
            alert.setTitle("Estudante cadastrado com sucesso!");
            alert.setHeaderText("Informação");
            alert.show();
        } catch (SQLException e1) {
            Alert alert;
            alert = new Alert(Alert.AlertType.INFORMATION, "Você clicou no botão Cancelar", ButtonType.OK);
            alert.setTitle("Estudante não foi cadastrado com sucesso!");
            alert.setHeaderText("Informação");
            alert.show();
            e1.printStackTrace();
        }
    }

    @FXML
    private void btnAlterar(ActionEvent event) {
        Disciplina disciplina = new Disciplina();
        disciplina.setNome(nomeInput.getText());
        disciplina.setDisciplina_id(Integer.parseInt(idInput.getText()));

        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        try {
            disciplinaDAO.update(disciplina);
            Alert alert;
            alert = new Alert(Alert.AlertType.INFORMATION, "Você clicou no botão Confirmar", ButtonType.OK);
            alert.setTitle("Disciplina Alterado com sucesso!");
            alert.setHeaderText("Informação");
            alert.show();
        } catch (SQLException e1) {
            Alert alert;
            alert = new Alert(Alert.AlertType.INFORMATION, "Você clicou no botão Cancelar", ButtonType.OK);
            alert.setTitle("Não foi possivel fazer alterações neste Disciplina");
            alert.setHeaderText("Informação");
            alert.show();
            e1.printStackTrace();
        }
    }

    @FXML
    private void btnDeletar(ActionEvent event) {
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        try {
            disciplinaDAO.delete(disciplinaDAO.consultar(Integer.parseInt(disciplinaId.getText())));
            Alert alert;
            alert = new Alert(Alert.AlertType.INFORMATION, "Você clicou no botão Confirmar", ButtonType.OK);
            alert.setTitle("Disciplina Alterado com sucesso!");
            alert.setHeaderText("Informação");
            alert.show();
        } catch (SQLException e1) {
            Alert alert;
            alert = new Alert(Alert.AlertType.INFORMATION, "Você clicou no botão Cancelar", ButtonType.OK);
            alert.setTitle("Não foi possivel fazer alterações neste Disciplina");
            alert.setHeaderText("Informação");
            alert.show();
            e1.printStackTrace();
        }
    }


    @FXML
    private void handleCarregarDisciplina() {
        try {
            List<Disciplina> estudantes = disciplinaDAO.listar(); // Método que lista todos os estudantes
            ObservableList<Disciplina> observableEstudantes = FXCollections.observableArrayList(estudantes);
            tabelaEstudantes.setItems(observableEstudantes);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBuscarPorId() {
        try {
            int disciplinaId = Integer.parseInt(disciplinaIdField.getText());
            Disciplina disciplina = disciplinaDAO.consultar(disciplinaId);
            if (disciplina != null) {
                tabelaEstudantes.setItems(FXCollections.observableArrayList(disciplina));
            } else {
                tabelaEstudantes.setItems(FXCollections.observableArrayList());
            }
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
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
                tabelaEstudantes.setItems(FXCollections.observableArrayList(disciplina));
            } else {
                tabelaEstudantes.setItems(FXCollections.observableArrayList());
            }
        } catch (NumberFormatException e) {
            System.out.println("Nome Inválio");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnVoltarOnAction(ActionEvent event) {
        // Obtém o stageAtual (janela que será fechada)
        Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // Fecha o Stage atual
        stageAtual.close();
    }
}

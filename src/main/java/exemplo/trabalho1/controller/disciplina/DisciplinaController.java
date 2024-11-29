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
    private TextField txtNomEst;

    @FXML
    private TextField idInput;

    @FXML
    private TextField nomeInput;

    @FXML
    private TextField disciplinaId;

    @FXML
    public void initialize() {}

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
    void btnVoltarOnAction(ActionEvent event) {
        // Obtém o stageAtual (janela que será fechada)
        Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // Fecha o Stage atual
        stageAtual.close();
    }
}
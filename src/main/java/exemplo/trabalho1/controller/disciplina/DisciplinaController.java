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

    private void exibirAlerta(String titulo, String mensagem, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo, mensagem, ButtonType.OK);
        alert.setTitle(titulo);
        alert.setHeaderText("Informação");
        alert.show();
    }


    @FXML
    private void btnIncluir(ActionEvent event) {
        Disciplina disciplina = new Disciplina();
        disciplina.setNome(txtNomEst.getText());

        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        try {
            disciplinaDAO.inserir(disciplina);
            exibirAlerta("Disciplina cadastrada com sucesso!", "Você clicou no botão Confirmar", Alert.AlertType.INFORMATION);
        } catch (SQLException e1) {
            exibirAlerta("Disciplina não cadastrada!", "A disciplina não foi cadastrada!", Alert.AlertType.INFORMATION);
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
            exibirAlerta("Disciplina alterada com sucesso!", "Você clicou no botão Confirmar", Alert.AlertType.INFORMATION);
        } catch (SQLException e1) {
            exibirAlerta("Disciplina não cadastrada!", "Você clicou no botão cancelar", Alert.AlertType.INFORMATION);
            e1.printStackTrace();
        }
    }

    @FXML
    private void btnDeletar(ActionEvent event) {
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        try {
            disciplinaDAO.delete(disciplinaDAO.consultar(Integer.parseInt(disciplinaId.getText())));
            exibirAlerta("Disciplina deletada com sucesso!", "Você clicou no botão Confirmar", Alert.AlertType.INFORMATION);
        } catch (SQLException e1) {
            exibirAlerta("Disciplina não deletada com sucesso!", "Você clicou no botão Cancelar", Alert.AlertType.INFORMATION);
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
package exemplo.trabalho1.controller.estudante;

import exemplo.trabalho1.dao.EstudanteDAO;
import exemplo.trabalho1.model.Estudante;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class EstudanteController {
    @FXML
    private Button btnConf;

    @FXML
    private TextField txtNomEst;

    @FXML
    private TextField idInput;

    @FXML
    private TextField nomeInput;

    @FXML
    private TextField estudanteId;

    @FXML
    public void initialize() {
    }

    @FXML
    private void btnAlterar(ActionEvent event) {
        Estudante estudante = new Estudante();
        estudante.setNome(nomeInput.getText());
        estudante.setEstudanteID(Integer.parseInt(idInput.getText()));

        EstudanteDAO estudanteDAO = new EstudanteDAO();
        try {
            estudanteDAO.update(estudante);
            Alert alert;
            alert = new Alert(Alert.AlertType.INFORMATION, "Você clicou no botão Confirmar", ButtonType.OK);
            alert.setTitle("Estudante Alterado com sucesso!");
            alert.setHeaderText("Informação");
            alert.show();
        } catch (SQLException e1) {
            Alert alert;
            alert = new Alert(Alert.AlertType.INFORMATION, "Você clicou no botão Cancelar", ButtonType.OK);
            alert.setTitle("Não foi possivel fazer alterações neste Estudante");
            alert.setHeaderText("Informação");
            alert.show();
            e1.printStackTrace();
        }
    }

    @FXML
    private void btnDelete(ActionEvent event) {
        EstudanteDAO estudanteDAO = new EstudanteDAO();
        try {
            estudanteDAO.delete(estudanteDAO.consultar(Integer.parseInt(estudanteId.getText())));
            Alert alert;
            alert = new Alert(Alert.AlertType.INFORMATION, "Você clicou no botão Confirmar", ButtonType.OK);
            alert.setTitle("Estudante Alterado com sucesso!");
            alert.setHeaderText("Informação");
            alert.show();
        } catch (SQLException e1) {
            Alert alert;
            alert = new Alert(Alert.AlertType.INFORMATION, "Você clicou no botão Cancelar", ButtonType.OK);
            alert.setTitle("Não foi possivel fazer alterações neste Estudante");
            alert.setHeaderText("Informação");
            alert.show();
            e1.printStackTrace();
        }
    }

    @FXML
    private void btnInserir(ActionEvent event) {
        Estudante estudante = new Estudante();
        estudante.setNome(txtNomEst.getText());

        EstudanteDAO estudanteDAO = new EstudanteDAO();
        try {
            estudanteDAO.inserir(estudante);
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
    void btnVoltarOnAction(ActionEvent event) {
        // Obtém o stageAtual (janela que será fechada)
        Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // Fecha o Stage atual
        stageAtual.close();
    }
}

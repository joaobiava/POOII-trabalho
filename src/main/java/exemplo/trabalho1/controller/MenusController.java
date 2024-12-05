package exemplo.trabalho1.controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenusController {

    private void carregarCena(String caminhoFXML, String titulo) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(caminhoFXML));
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            newStage.setTitle(titulo);
            newStage.setResizable(false);
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void alteraDisciplinaOnAction(ActionEvent event) {
        carregarCena("/exemplo/trabalho1/view/disciplina/alteraDisciplina.fxml", "Disciplina");
    }

    @FXML
    void incluiDisciplinaOnAction(ActionEvent event) {
        carregarCena("/exemplo/trabalho1/view/disciplina/incluiDisciplina.fxml", "Disciplina");
    }

    @FXML
    void deleteDisciplinaOnAction(ActionEvent event) {
        carregarCena("/exemplo/trabalho1/view/disciplina/deleteDisciplina.fxml", "Disciplina");
    }

    @FXML
    void consultaDisciplinaOnAction(ActionEvent event) {
        carregarCena("/exemplo/trabalho1/view/disciplina/consultaDisciplina.fxml", "Disciplina");
    }

    @FXML
    void incluiOnAction(ActionEvent event) {
        carregarCena("/exemplo/trabalho1/view/estudante/IncluiEstudante.fxml", "Estudante");
    }

    @FXML
    void alteraOnAction(ActionEvent event) {
        carregarCena("/exemplo/trabalho1/view/estudante/alteraEstudante.fxml", "Estudante");
    }

    @FXML
    void deleteOnAction(ActionEvent event) {
        carregarCena("/exemplo/trabalho1/view/estudante/deleteEstudante.fxml", "Estudante");
    }

    @FXML
    void consultaOnAction(ActionEvent event) {
        carregarCena("/exemplo/trabalho1/view/estudante/consultaEstudante.fxml", "Estudante");
    }

    @FXML
    void incluirEstudanteDisciplinaOnAction(ActionEvent event) {
        carregarCena("/exemplo/trabalho1/view/estudanteDisciplina/adiciona.fxml", "Incluir Estudante na Disciplina");
    }

    @FXML
    void alterarEstudanteDisciplinaOnAction(ActionEvent event) {
        carregarCena("/exemplo/trabalho1/view/estudanteDisciplina/altera.fxml", "Alterar Estudante na Disciplina");
    }

    @FXML
    void consultarEstudanteDisciplinaOnAction(ActionEvent event) {
        carregarCena("/exemplo/trabalho1/view/estudanteDisciplina/consulta.fxml", "Consultar Estudante na Disciplina");
    }
}

package exemplo.trabalho1.controller.estudante;

import exemplo.trabalho1.dao.EstudanteDAO;
import exemplo.trabalho1.model.Estudante;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class EstudanteController {

    @FXML
    private TextField txtNomEst;

    @FXML
    private TextField idInput;

    @FXML
    private TextField nomeInput;

    @FXML
    private TextField estudanteId;

    private final EstudanteDAO estudanteDAO = new EstudanteDAO();

    @FXML
    public void initialize() {}

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, mensagem, ButtonType.OK);
        alert.setTitle(titulo);
        alert.setHeaderText("Informação");
        alert.show();
    }

    @FXML
    private void btnAlterar(ActionEvent event) {
        try {
            Estudante estudante = criarEstudante(Integer.parseInt(idInput.getText()), nomeInput.getText());
            estudanteDAO.update(estudante);
            mostrarAlerta("Estudante Alterado com Sucesso", "As alterações foram salvas com sucesso.");
        } catch (SQLException e) {
            mostrarAlerta("Erro ao Alterar Estudante", "Não foi possível salvar as alterações.");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            mostrarAlerta("Erro de Entrada", "ID inválido.");
        }
    }

    @FXML
    private void btnDelete(ActionEvent event) {
        try {
            int id = Integer.parseInt(estudanteId.getText());
            estudanteDAO.delete(estudanteDAO.consultar(id));
            mostrarAlerta("Estudante Removido com Sucesso", "O estudante foi excluído.");
        } catch (SQLException e) {
            mostrarAlerta("Erro ao Remover Estudante", "Não foi possível excluir o estudante.");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            mostrarAlerta("Erro de Entrada", "ID inválido.");
        }
    }

    @FXML
    private void btnInserir(ActionEvent event) {
        try {
            Estudante estudante = criarEstudante(null, txtNomEst.getText());
            estudanteDAO.inserir(estudante);
            mostrarAlerta("Estudante Cadastrado com Sucesso", "O cadastro foi concluído.");
        } catch (SQLException e) {
            mostrarAlerta("Erro ao Cadastrar Estudante", "Não foi possível realizar o cadastro.");
            e.printStackTrace();
        }
    }

    @FXML
    private void btnVoltarOnAction(ActionEvent event) {
        Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageAtual.close();
    }

    private Estudante criarEstudante(Integer id, String nome) {
        Estudante estudante = new Estudante();
        estudante.setEstudanteID(id != null ? id : 0);
        estudante.setNome(nome);
        return estudante;
    }
}

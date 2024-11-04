module exemplo.trabalho1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires transitive java.sql;
    opens exemplo.trabalho1.controller to javafx.fxml;
    exports exemplo.trabalho1.controller;
    opens exemplo.trabalho1.model to javafx.fxml;
    exports exemplo.trabalho1.model;
    opens exemplo.trabalho1.main to javafx.fxml;
    exports exemplo.trabalho1.main;
    opens exemplo.trabalho1.dao to javafx.fxml;
    exports exemplo.trabalho1.dao;
    exports exemplo.trabalho1.controller.estudante;
    opens exemplo.trabalho1.controller.estudante to javafx.fxml;
    exports exemplo.trabalho1.controller.disciplina;
    opens exemplo.trabalho1.controller.disciplina to javafx.fxml;
    exports exemplo.trabalho1.controller.estudanteDisciplina;
    opens exemplo.trabalho1.controller.estudanteDisciplina to javafx.fxml;
}
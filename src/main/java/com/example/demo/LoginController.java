package com.example.demo;


import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class LoginController implements FxmlController {

    public static String styled;
    private final StageManager stageManager;


    @Autowired
    @Lazy
    public LoginController(StageManager stageManager) {

        this.stageManager = stageManager;
    }

    @FXML
    Label greetingLabel;

    @FXML
    StackPane stackPane;
    @FXML
    private Button loginButton, cancelButton;
    @FXML
    private TextField username_tf, password_tf;
    @FXML
    ChoiceBox styleBox;


    @Override
    public void initialize() {


        styleBox.setItems(FXCollections.observableArrayList(
                "Light", "Base", "Dark"));
        styleBox.getSelectionModel().select(1);

        loginButton.setOnAction(event -> loginButton.setStyle("-fx-background-color: orange") );



        //System.out.println(rbDE.getString("msg"));


        //	styleBox.setSelected(true);
        Platform.runLater(() -> {
            //pass_label.prefWidthProperty().bind(password_tf.widthProperty());


        });

    }





}

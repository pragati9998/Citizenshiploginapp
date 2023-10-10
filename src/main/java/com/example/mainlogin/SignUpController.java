package com.example.mainlogin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    @FXML
    private Button button_signup;
    @FXML
    private Button button_log_in;

    @FXML
    private TextField tf_username;

    @FXML
    private TextField tf_password;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_signup.setOnAction(event -> {
            if (!tf_username.getText().trim().isEmpty() && !tf_password.getText().trim().isEmpty()) {
                DBUtils.signUpUser(event, tf_username.getText(), tf_password.getText());
            } else {
                System.out.println("Please fill in all information");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please fill in all information to sign up!");
                alert.show();
            }
        });

        button_log_in.setOnAction(event -> {
            DBUtils.changeScene(event, "sample.fxml", "Log in!", null);
        });
    }
}

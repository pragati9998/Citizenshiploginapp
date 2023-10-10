package com.example.mainlogin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;

public class SignUpController implements Initializable {
    @FXML
    private Button button_signup;
    @FXML
    private Button button_log_in;
    @FXML
    private TextField tf_username;
    @FXML
    private PasswordField tf_password;
    @FXML
    private TextField tf_name;
    @FXML
    private DatePicker dp_dob;
    @FXML
    private ComboBox<String> cb_gender;
    @FXML
    private TextField tf_nationality;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize the gender ComboBox
        cb_gender.setItems(FXCollections.observableArrayList("Male", "Female", "Other"));

        button_signup.setOnAction(event -> {
            String username = tf_username.getText();
            String password = tf_password.getText();
            String name = tf_name.getText();
            Date dob = Date.valueOf(dp_dob.getValue());
            String gender = cb_gender.getValue();
            String nationality = tf_nationality.getText();

            if (!username.trim().isEmpty() && !password.trim().isEmpty() && !name.trim().isEmpty() && dob != null && gender != null && !nationality.trim().isEmpty()) {
                // Call the signUpUser method from DBUtils to register the user
                DBUtils.signUpUser(event, username, password, name, dob, gender, nationality);
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

package com.example.mainlogin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sample.fxml")));
       stage.setTitle("Log in!");
       stage.setScene(new Scene(root, 600, 400));
       stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
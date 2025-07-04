package com.example.hellofx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/hellofx/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        stage.setTitle("Satellite Mission Control");
        stage.setScene(scene);
        stage.setHeight(600);
        stage.setWidth(500);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

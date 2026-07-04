package br.com;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private static final String APPLICATION_TITLE = "Folder Organizer";
    private static final String FXML_PATH = "/fxml/folder-organizer.fxml";
    private static final int WINDOW_WIDTH = 720;
    private static final int WINDOW_HEIGHT = 420;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML_PATH));

        Scene scene = new Scene(fxmlLoader.load(), WINDOW_WIDTH, WINDOW_HEIGHT);
        stage.setTitle(APPLICATION_TITLE);
        stage.setScene(scene);
        stage.show();
    }
}

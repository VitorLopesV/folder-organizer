package br.com;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Classe principal da aplicação Folder Organizer.
 */
public class Main extends Application {

    /** Título exibido na janela principal. */
    private static final String APPLICATION_TITLE = "Folder Organizer";

    /** Caminho do arquivo FXML da tela principal. */
    private static final String FXML_PATH = "/fxml/folder-organizer.fxml";

    /** Largura inicial da janela principal. */
    private static final int WINDOW_WIDTH = 720;

    /** Altura inicial da janela principal. */
    private static final int WINDOW_HEIGHT = 420;

    /**
     * Inicializa a aplicação JavaFX.
     *
     * @param args Argumentos informados na execução da aplicação.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Carrega a tela principal via FXML e exibe a janela da aplicação.
     *
     * @param stage Janela principal gerenciada pelo JavaFX.
     * @throws IOException Quando o arquivo FXML não puder ser carregado.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML_PATH));

        Scene scene = new Scene(fxmlLoader.load(), WINDOW_WIDTH, WINDOW_HEIGHT);
        stage.setTitle(APPLICATION_TITLE);
        stage.setScene(scene);
        stage.show();
    }
}

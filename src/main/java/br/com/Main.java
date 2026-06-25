package br.com;

import br.com.controller.FolderOrganizerController;
import br.com.model.FolderOrganizerConfig;
import br.com.service.FolderOrganizerService;
import br.com.view.ConsoleView;

import java.nio.file.Path;

public class Main {

    private static final Path SOURCE_FOLDER = Path.of("C:\\Users\\vitor\\Desktop\\Teste");

    public static void main(String[] args) {
        FolderOrganizerConfig config = FolderOrganizerConfig.defaultConfig(SOURCE_FOLDER);
        FolderOrganizerService service = new FolderOrganizerService();
        ConsoleView view = new ConsoleView();
        FolderOrganizerController controller = new FolderOrganizerController(service, view);

        controller.organize(config);
    }
}

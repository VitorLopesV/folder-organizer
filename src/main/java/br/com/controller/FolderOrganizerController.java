package br.com.controller;

import br.com.model.FolderOrganizerConfig;
import br.com.model.OrganizationResult;
import br.com.service.FolderOrganizerService;
import br.com.view.FolderOrganizerView;

/**
 * Controlador responsável por intermediar a organização de arquivos entre serviço e visualização.
 */
public class FolderOrganizerController {

    /** Serviço que executa a organização física dos arquivos. */
    private final FolderOrganizerService service;

    /** Visualização usada para apresentar mensagens e resultados ao usuário. */
    private final FolderOrganizerView view;

    /**
     * Cria o controlador com as dependências necessárias.
     *
     * @param service Serviço de organização de arquivos.
     * @param view    Visualização responsável por apresentar os resultados.
     */
    public FolderOrganizerController(FolderOrganizerService service, FolderOrganizerView view) {
        this.service = service;
        this.view = view;
    }

    /**
     * Organiza a pasta configurada e encaminha o resultado para a visualização.
     *
     * @param config Configuração com a pasta de origem a ser organizada.
     */
    public void organize(FolderOrganizerConfig config) {
        try {
            view.showSourceFolder(config.sourceFolder());
            OrganizationResult result = service.organize(config);
            view.showResult(result);
        } catch (RuntimeException exception) {
            view.showError(exception.getMessage());
        }
    }
}

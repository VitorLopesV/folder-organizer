package br.com.controller;

import br.com.model.FolderOrganizerConfig;
import br.com.model.OrganizationResult;
import br.com.service.FolderOrganizerService;
import br.com.view.FolderOrganizerView;

public class FolderOrganizerController {

    private final FolderOrganizerService service;
    private final FolderOrganizerView view;

    public FolderOrganizerController(FolderOrganizerService service, FolderOrganizerView view) {
        this.service = service;
        this.view = view;
    }

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

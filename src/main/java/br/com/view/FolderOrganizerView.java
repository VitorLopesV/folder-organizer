package br.com.view;

import br.com.model.OrganizationResult;

import java.nio.file.Path;

public interface FolderOrganizerView {

    void showSourceFolder(Path sourceFolder);

    void showResult(OrganizationResult result);

    void showError(String message);

}

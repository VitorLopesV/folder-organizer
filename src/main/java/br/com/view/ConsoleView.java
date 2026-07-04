package br.com.view;

import br.com.model.FileMoveResult;
import br.com.model.OrganizationResult;

import java.nio.file.Path;

public class ConsoleView implements FolderOrganizerView {

    @Override
    public void showSourceFolder(Path sourceFolder) {
        System.out.println("Pasta que sera organizada: " + sourceFolder);
    }

    @Override
    public void showResult(OrganizationResult result) {
        if (result.sourceFolderCreated()) {
            System.out.println("Pasta criada: " + result.sourceFolder());
            return;
        }

        result.fileResults().forEach(this::showFileResult);
        System.out.println("Resumo: " + result.movedCount() + " arquivo(s) movido(s), "
                + result.failedCount() + " falha(s).");
    }

    @Override
    public void showError(String message) {
        System.err.println("Erro: " + message);
    }

    private void showFileResult(FileMoveResult result) {
        if (result.success()) {
            System.out.println("Movido: " + result.source().getFileName()
                    + " -> " + result.category().getFolderName());
            return;
        }

        System.err.println("Falha ao mover " + result.source().getFileName()
                + ": " + result.errorMessage());
    }
}

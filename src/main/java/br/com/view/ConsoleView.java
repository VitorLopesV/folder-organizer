package br.com.view;

import br.com.model.FileMoveResult;
import br.com.model.OrganizationResult;

public class ConsoleView {

    public void showResult(OrganizationResult result) {
        if (result.sourceFolderCreated()) {
            System.out.println("Pasta criada: " + result.sourceFolder());
            return;
        }

        result.fileResults().forEach(this::showFileResult);
        System.out.println("Resumo: " + result.movedCount() + " arquivo(s) movido(s), "
                + result.failedCount() + " falha(s).");
    }

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

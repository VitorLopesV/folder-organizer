package br.com.view;

import br.com.model.FileMoveResult;
import br.com.model.OrganizationResult;

import java.nio.file.Path;

/**
 * Visualização em console para exibir o resultado da organização de arquivos.
 */
public class ConsoleView implements FolderOrganizerView {

    /**
     * Exibe a pasta de origem que será organizada.
     *
     * @param sourceFolder Pasta de origem.
     */
    @Override
    public void showSourceFolder(Path sourceFolder) {
        System.out.println("Pasta que sera organizada: " + sourceFolder);
    }

    /**
     * Exibe o resultado geral da organização.
     *
     * @param result Resultado da organização.
     */
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

    /**
     * Exibe uma mensagem de erro no console.
     *
     * @param message Mensagem de erro.
     */
    @Override
    public void showError(String message) {
        System.err.println("Erro: " + message);
    }

    /**
     * Exibe o resultado individual de um arquivo processado.
     *
     * @param result Resultado da movimentação do arquivo.
     */
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

package br.com.view;

import br.com.model.OrganizationResult;

import java.nio.file.Path;

/**
 * Contrato de visualização para apresentar o andamento e o resultado da organização de arquivos.
 */
public interface FolderOrganizerView {

    /**
     * Exibe a pasta de origem que será organizada.
     *
     * @param sourceFolder Pasta de origem.
     */
    void showSourceFolder(Path sourceFolder);

    /**
     * Exibe o resultado da organização.
     *
     * @param result Resultado gerado pelo serviço.
     */
    void showResult(OrganizationResult result);

    /**
     * Exibe uma mensagem de erro.
     *
     * @param message Mensagem de erro.
     */
    void showError(String message);

}

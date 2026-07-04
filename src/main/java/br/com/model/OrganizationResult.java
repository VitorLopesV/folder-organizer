package br.com.model;

import java.nio.file.Path;
import java.util.List;

/**
 * Resultado geral da execução do organizador de arquivos.
 *
 * @param sourceFolder         Pasta de origem processada.
 * @param sourceFolderCreated  Indica se a pasta de origem foi criada durante a execução.
 * @param fileResults          Resultados individuais de cada arquivo processado.
 */
public record OrganizationResult(Path sourceFolder, boolean sourceFolderCreated, List<FileMoveResult> fileResults) {

    /**
     * Conta os arquivos movidos com sucesso.
     *
     * @return Quantidade de arquivos movidos.
     */
    public long movedCount() {
        return fileResults.stream()
                .filter(FileMoveResult::success)
                .count();
    }

    /**
     * Conta os arquivos que falharam durante a movimentação.
     *
     * @return Quantidade de falhas.
     */
    public long failedCount() {
        return fileResults.stream()
                .filter(result -> !result.success())
                .count();
    }
}

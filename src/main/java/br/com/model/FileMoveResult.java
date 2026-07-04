package br.com.model;

import java.nio.file.Path;

/**
 * Resultado da tentativa de mover um arquivo para a pasta de sua categoria.
 *
 * @param source       Caminho original do arquivo.
 * @param destination  Caminho final do arquivo quando a movimentação for concluída.
 * @param category     Categoria atribuída ao arquivo.
 * @param success      Indica se a movimentação foi concluída com sucesso.
 * @param errorMessage Mensagem de erro quando a movimentação falhar.
 */
public record FileMoveResult(Path source, Path destination, FileCategory category, boolean success, String errorMessage) {

    /**
     * Cria um resultado de movimentação concluída com sucesso.
     *
     * @param source      Caminho original do arquivo.
     * @param destination Caminho final do arquivo.
     * @param category    Categoria atribuída ao arquivo.
     * @return Resultado de sucesso.
     */
    public static FileMoveResult success(Path source, Path destination, FileCategory category) {
        return new FileMoveResult(source, destination, category, true, null);
    }

    /**
     * Cria um resultado de falha na movimentação.
     *
     * @param source       Caminho original do arquivo.
     * @param category     Categoria atribuída ao arquivo.
     * @param errorMessage Mensagem descrevendo a falha.
     * @return Resultado de falha.
     */
    public static FileMoveResult failure(Path source, FileCategory category, String errorMessage) {
        return new FileMoveResult(source, null, category, false, errorMessage);
    }
}

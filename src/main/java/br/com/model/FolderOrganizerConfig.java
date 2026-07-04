package br.com.model;

import java.nio.file.Path;

/**
 * Configuração usada para executar a organização dos arquivos.
 *
 * @param sourceFolder Pasta de origem que será organizada.
 */
public record FolderOrganizerConfig(Path sourceFolder) {

    /**
     * Cria uma configuração padrão usando a pasta de origem informada.
     *
     * @param sourceFolder Pasta de origem que será organizada.
     * @return Configuração padrão da aplicação.
     */
    public static FolderOrganizerConfig defaultConfig(Path sourceFolder) {
        return new FolderOrganizerConfig(sourceFolder);
    }
}

package br.com.model;

import java.nio.file.Path;

public record FolderOrganizerConfig(Path sourceFolder) {

    public static FolderOrganizerConfig defaultConfig(Path sourceFolder) {
        return new FolderOrganizerConfig(sourceFolder);
    }
}

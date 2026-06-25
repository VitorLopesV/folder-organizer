package br.com.model;

import java.nio.file.Path;

public record FileMoveResult(Path source, Path destination, FileCategory category, boolean success, String errorMessage) {

    public static FileMoveResult success(Path source, Path destination, FileCategory category) {
        return new FileMoveResult(source, destination, category, true, null);
    }

    public static FileMoveResult failure(Path source, FileCategory category, String errorMessage) {
        return new FileMoveResult(source, null, category, false, errorMessage);
    }
}

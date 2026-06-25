package br.com.model;

import java.nio.file.Path;
import java.util.List;

public record OrganizationResult(Path sourceFolder, boolean sourceFolderCreated, List<FileMoveResult> fileResults) {

    public long movedCount() {
        return fileResults.stream()
                .filter(FileMoveResult::success)
                .count();
    }

    public long failedCount() {
        return fileResults.stream()
                .filter(result -> !result.success())
                .count();
    }
}

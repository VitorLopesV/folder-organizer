package br.com.model;

import java.util.Locale;
import java.util.Set;

public enum FileCategory {
    IMAGES("Imagens", Set.of("jpg", "jpeg", "png", "gif", "bmp", "webp", "svg", "heic")),
    DOCUMENTS("Documentos", Set.of("pdf", "doc", "docx", "txt", "rtf", "odt", "xls", "xlsx", "ppt", "pptx", "csv")),
    VIDEOS("Videos", Set.of("mp4", "mkv", "avi", "mov", "wmv", "webm")),
    MUSIC("Musicas", Set.of("mp3", "wav", "flac", "aac", "ogg", "m4a")),
    ARCHIVES("Compactados", Set.of("zip", "rar", "7z", "tar", "gz")),
    EXECUTABLES("Executaveis", Set.of("exe", "msi", "bat", "cmd")),
    OTHERS("Outros", Set.of());

    private final String folderName;
    private final Set<String> extensions;

    FileCategory(String folderName, Set<String> extensions) {
        this.folderName = folderName;
        this.extensions = extensions;
    }

    public String getFolderName() {
        return folderName;
    }

    public static FileCategory fromExtension(String extension) {
        String normalizedExtension = extension.toLowerCase(Locale.ROOT);

        for (FileCategory category : values()) {
            if (category.extensions.contains(normalizedExtension)) {
                return category;
            }
        }

        return OTHERS;
    }
}

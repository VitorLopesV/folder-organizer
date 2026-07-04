package br.com.model;

import java.util.Locale;
import java.util.Set;

/**
 * Categorias disponíveis para agrupar arquivos por extensão.
 */
public enum FileCategory {

    /** Arquivos de imagem. */
    IMAGES("Imagens", Set.of("jpg", "jpeg", "png", "gif", "bmp", "webp", "svg", "heic")),

    /** Arquivos de documento e planilhas. */
    DOCUMENTS("Documentos", Set.of("pdf", "doc", "docx", "txt", "rtf", "odt", "xls", "xlsx", "ppt", "pptx", "csv")),

    /** Arquivos de vídeo. */
    VIDEOS("Videos", Set.of("mp4", "mkv", "avi", "mov", "wmv", "webm")),

    /** Arquivos de áudio. */
    MUSIC("Musicas", Set.of("mp3", "wav", "flac", "aac", "ogg", "m4a")),

    /** Arquivos compactados. */
    ARCHIVES("Compactados", Set.of("zip", "rar", "7z", "tar", "gz")),

    /** Arquivos executáveis e scripts do Windows. */
    EXECUTABLES("Executaveis", Set.of("exe", "msi", "bat", "cmd")),

    /** Arquivos sem categoria específica. */
    OTHERS("Outros", Set.of());

    /** Nome da pasta onde a categoria será armazenada. */
    private final String folderName;

    /** Extensões associadas à categoria. */
    private final Set<String> extensions;

    /**
     * Cria uma categoria com a pasta de destino e suas extensões.
     *
     * @param folderName Nome da pasta da categoria.
     * @param extensions Extensões aceitas pela categoria.
     */
    FileCategory(String folderName, Set<String> extensions) {
        this.folderName = folderName;
        this.extensions = extensions;
    }

    /**
     * Retorna o nome da pasta associada à categoria.
     *
     * @return Nome da pasta de destino.
     */
    public String getFolderName() {
        return folderName;
    }

    /**
     * Resolve a categoria correspondente a uma extensão de arquivo.
     *
     * @param extension Extensão do arquivo sem ponto.
     * @return Categoria correspondente ou {@link #OTHERS} quando não houver associação.
     */
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

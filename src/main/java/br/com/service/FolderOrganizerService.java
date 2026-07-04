package br.com.service;

import br.com.model.FileCategory;
import br.com.model.FileMoveResult;
import br.com.model.FolderOrganizerConfig;
import br.com.model.OrganizationResult;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Serviço responsável por organizar arquivos em subpastas por categoria.
 */
public class FolderOrganizerService {

    /**
     * Organiza a pasta configurada, criando-a quando ela ainda não existir.
     *
     * @param config Configuração com a pasta que será organizada.
     * @return Resultado da organização executada.
     */
    public OrganizationResult organize(FolderOrganizerConfig config) {
        Path sourceFolder = config.sourceFolder();

        try {
            if (!Files.exists(sourceFolder)) {
                Files.createDirectories(sourceFolder);
                return new OrganizationResult(sourceFolder, true, List.of());
            }

            if (!Files.isDirectory(sourceFolder)) {
                throw new IllegalArgumentException("O caminho configurado nao e uma pasta: " + sourceFolder);
            }

            return new OrganizationResult(sourceFolder, false, organizeFiles(sourceFolder));
        } catch (IOException exception) {
            throw new UncheckedIOException("Falha ao organizar a pasta: " + exception.getMessage(), exception);
        }
    }

    /**
     * Organiza todos os arquivos regulares encontrados na pasta de origem.
     *
     * @param sourceFolder Pasta que contém os arquivos a serem organizados.
     * @return Lista de resultados individuais de movimentação.
     * @throws IOException Quando a leitura da pasta falhar.
     */
    private List<FileMoveResult> organizeFiles(Path sourceFolder) throws IOException {
        List<FileMoveResult> results = new ArrayList<>();

        try (var files = Files.list(sourceFolder)) {
            files.filter(Files::isRegularFile)
                    .map(this::moveToCategoryFolder)
                    .forEach(results::add);
        }

        return List.copyOf(results);
    }

    /**
     * Move um arquivo para a pasta correspondente à sua categoria.
     *
     * @param file Arquivo que será movido.
     * @return Resultado da tentativa de movimentação.
     */
    private FileMoveResult moveToCategoryFolder(Path file) {
        FileCategory category = resolveCategory(file);

        try {
            Path destinationFolder = file.getParent().resolve(category.getFolderName());
            Files.createDirectories(destinationFolder);

            Path destination = uniqueDestination(destinationFolder.resolve(file.getFileName()));
            Files.move(file, destination, StandardCopyOption.ATOMIC_MOVE);

            return FileMoveResult.success(file, destination, category);
        } catch (IOException exception) {
            return FileMoveResult.failure(file, category, exception.getMessage());
        }
    }

    /**
     * Resolve a categoria do arquivo a partir da extensão.
     *
     * @param file Arquivo que terá a categoria resolvida.
     * @return Categoria correspondente à extensão do arquivo.
     */
    private FileCategory resolveCategory(Path file) {
        return FileCategory.fromExtension(extensionOf(file));
    }

    /**
     * Obtém a extensão do arquivo sem o ponto.
     *
     * @param file Arquivo usado para extrair a extensão.
     * @return Extensão normalizada ou texto vazio quando não houver extensão.
     */
    private String extensionOf(Path file) {
        String fileName = file.getFileName().toString();
        int dotIndex = fileName.lastIndexOf('.');

        if (dotIndex <= 0 || dotIndex == fileName.length() - 1) {
            return "";
        }

        return fileName.substring(dotIndex + 1).toLowerCase(Locale.ROOT);
    }

    /**
     * Gera um caminho de destino ainda não utilizado.
     *
     * @param destination Caminho de destino desejado.
     * @return Caminho disponível, com sufixo numérico quando necessário.
     */
    private Path uniqueDestination(Path destination) {
        if (!Files.exists(destination)) {
            return destination;
        }

        String fileName = destination.getFileName().toString();
        int dotIndex = fileName.lastIndexOf('.');
        String baseName = dotIndex > 0 ? fileName.substring(0, dotIndex) : fileName;
        String extension = dotIndex > 0 ? fileName.substring(dotIndex) : "";

        int counter = 1;
        Path candidate;
        do {
            candidate = destination.getParent().resolve(baseName + " (" + counter + ")" + extension);
            counter++;
        } while (Files.exists(candidate));

        return candidate;
    }
}

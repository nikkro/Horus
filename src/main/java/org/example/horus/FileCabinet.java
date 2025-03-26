package org.example.horus;
import java.util.*;
import java.util.stream.Stream;

public class FileCabinet implements Cabinet {
    private List<Folder> folders;

    public FileCabinet(List<Folder> folders) {
        this.folders = folders != null ? folders : new ArrayList<>();
    }



    /**
     * @return first folder by name that occurred in stream.
     * **/
    @Override
    public Optional<Folder> findFolderByName(String name) {
        return !isFolderNameValid(name) ? Optional.empty() :
            getAllFoldersStream()
                .filter(folder -> folder.getName().equals(name))
                .findFirst();
    }

    /**
     * @return a list of folders matching the specified size, or an empty list if the size is invalid
     */
    @Override
    public List<Folder> findFoldersBySize(String size) {
        return !isFolderSizeValid(size) ? Collections.emptyList() :
            getAllFoldersStream()
                .filter(folder -> folder.getSize().equals(FolderSize.valueOf(size.toUpperCase())))
                .toList();
    }

    @Override
    public int count() {
        return (int) getAllFoldersStream().count();
    }

    /**
     * @return a stream of all folders, including nested folders if present.
     */
    public Stream<Folder> getAllFoldersStream() {
        return Optional.ofNullable(folders)
            .stream()
            .flatMap(List::stream)
            .flatMap(this::flattenFolder);
    }

    /**
     * @return a stream containing the folder and its nested folders (if apply)
     */
    private Stream<Folder> flattenFolder(Folder folder) {
        if(folder instanceof MultiFolder multiFolder){
            return Stream.concat(
                Stream.of(folder),
                multiFolder.getFolders().stream().flatMap(this::flattenFolder));
        }
        return Stream.of(folder);
    }

    private boolean isFolderNameValid(String name){
        return name != null && !name.isBlank();
    }

    private boolean isFolderSizeValid(String size){
        return size != null && !size.isBlank() && FolderSize.isSizeValidValue(size);
    }

}
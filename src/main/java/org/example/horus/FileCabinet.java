package org.example.horus;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class FileCabinet implements Cabinet {
    private List<Folder> folders;

    public FileCabinet(List<Folder> folders) {
        this.folders = folders != null ? folders : new ArrayList<>();
    }


    @Override
    public Optional<Folder> findFolderByName(String name) {
        return !isFolderNameValid(name) ? Optional.empty() :
            Optional.ofNullable(folders)
                .stream()
                .flatMap(List::stream)
                .flatMap(folder -> folder instanceof MultiFolder
                    ? Stream.concat(Stream.of(folder), ((MultiFolder) folder).getFolders().stream())
                    : Stream.of(folder))
                .filter(folder -> folder.getName().equals(name))
                .findFirst();
    }

    @Override
    public List<Folder> findFoldersBySize(String size) {
        return List.of();
    }

    @Override
    public int count() {
        return 0;
    }

    private boolean isFolderNameValid(String name){
        return name != null && !name.isBlank();
    }
}
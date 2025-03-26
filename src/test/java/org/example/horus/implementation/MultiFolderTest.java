package org.example.horus.implementation;

import org.example.horus.Folder;
import org.example.horus.FolderSize;
import org.example.horus.MultiFolder;

import java.util.List;

public class MultiFolderTest extends FolderTest implements MultiFolder {
  private final List<Folder> folders;

  public MultiFolderTest(String name, FolderSize size, List<Folder> folders) {
    super(name,size);
    this.folders = folders;
  }

  @Override
  public List<Folder> getFolders() {
    return folders;
  }
}

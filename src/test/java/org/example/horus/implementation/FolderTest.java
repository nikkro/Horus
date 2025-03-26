package org.example.horus.implementation;

import org.example.horus.Folder;
import org.example.horus.FolderSize;

public class FolderTest implements Folder {
  private String name;
  private FolderSize size;


  public FolderTest(String name, FolderSize size){
    this.name = name;
    this.size = size;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public FolderSize getSize() {
    return size;
  }
}

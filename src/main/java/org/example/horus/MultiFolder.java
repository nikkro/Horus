package org.example.horus;

import java.util.List;

public interface MultiFolder extends Folder {
  List<Folder> getFolders();
}
package org.example.horus;

import java.util.List;

interface MultiFolder extends Folder {
  List<Folder> getFolders();
}
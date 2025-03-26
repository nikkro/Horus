package org.example.horus;

public enum FolderSize {
  SMALL, MEDIUM, LARGE;

  public static boolean isSizeValidValue(String size) {
    try {
      FolderSize.valueOf(size.toUpperCase());
      return true;
    } catch (IllegalArgumentException e) {
      return false;
    }
  }
}
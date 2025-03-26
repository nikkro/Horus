package org.example.horus;

import org.example.horus.implementation.FolderTest;
import org.example.horus.implementation.MultiFolderTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class FileCabinetTest {

  private FileCabinet fileCabinet;

  @BeforeEach
  void setUp() {
    FolderTest folder1 = new FolderTest("folder1", FolderSize.SMALL);
    FolderTest folder2 = new FolderTest("folder2", FolderSize.LARGE);
    FolderTest folder3 = new FolderTest("folder3", FolderSize.LARGE);
    MultiFolderTest multiFolder = new MultiFolderTest("multiFolder", FolderSize.MEDIUM, List.of(folder1, folder3));
    fileCabinet = new FileCabinet(List.of(folder1, folder2, multiFolder));
  }

  @Test
  void findFolderByNameReturnsCorrectFolder() {
    Optional<Folder> result = fileCabinet.findFolderByName("folder1");

    assertTrue(result.isPresent());
    assertEquals("folder1", result.get().getName());
  }

  @Test
  void findFolderByNameReturnsEmptyForInvalidName() {
    Optional<Folder> result = fileCabinet.findFolderByName("invalidName");

    assertFalse(result.isPresent());
  }

  @Test
  void findFoldersBySizeReturnsCorrectFolders() {
    List<Folder> result = fileCabinet.findFoldersBySize("SMALL");

    assertEquals(2, result.size());
    assertEquals(FolderSize.SMALL, result.get(0).getSize());
    assertEquals(FolderSize.SMALL, result.get(1).getSize());
  }

  @Test
  void findFoldersBySizeReturnsEmptyListForInvalidSize() {
    List<Folder> result = fileCabinet.findFoldersBySize("INVALID");

    assertTrue(result.isEmpty());
  }

  @Test
  void countReturnsCorrectNumberOfFolders() {
    int result = fileCabinet.count();

    assertEquals(5, result);
  }

  @Test
  void countReturnsZeroForEmptyCabinet() {
    FileCabinet emptyCabinet = new FileCabinet(new ArrayList<>());

    int result = emptyCabinet.count();

    assertEquals(0, result);
  }

  @Test
  void findFolderByNameInMultiFolderReturnsCorrectFolder() {
    Optional<Folder> result = fileCabinet.findFolderByName("folder3");

    assertTrue(result.isPresent());
    assertEquals("folder3", result.get().getName());
  }
}

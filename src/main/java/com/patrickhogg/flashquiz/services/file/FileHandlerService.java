package com.patrickhogg.flashquiz.services.file;

import com.patrickhogg.flashquiz.objects.Deck;
import javafx.collections.ObservableList;

import java.io.File;
import java.util.List;

/**
 * @author Patrick Hogg
 */
public interface FileHandlerService {

    void saveDeckToFile(Deck deck);
    Deck loadDeckFromFile(File file);
    ObservableList<Deck> loadDecksFromFiles();
    List<File> getFilesFromPath(String pathName);
    File getFileFromPath(String pathName, String fileName);
    void deleteFileFromPath(String pathName, String fileName);

    void deleteFile(String fileName);

}

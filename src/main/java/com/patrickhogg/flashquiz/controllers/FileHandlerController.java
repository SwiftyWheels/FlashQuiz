package com.patrickhogg.flashquiz.controllers;

import com.patrickhogg.flashquiz.objects.Deck;
import com.patrickhogg.flashquiz.services.file.FileHandlerServiceImpl;
import javafx.collections.ObservableList;

/**
 * @author Patrick Hogg
 */
public class FileHandlerController {

    FileHandlerServiceImpl fileHandlerService;

    public FileHandlerServiceImpl getFileHandlerService() {
        return fileHandlerService;
    }

    public void setFileHandlerService(
            FileHandlerServiceImpl fileHandlerService) {
        this.fileHandlerService = fileHandlerService;
    }

    public void saveDeckToFile(Deck deck) {
        fileHandlerService.saveDeckToFile(deck);
    }

    public ObservableList<Deck> loadDecksFromFiles(){
        return fileHandlerService.loadDecksFromFiles();
    }

    public void deleteFile(String fileName) {
        getFileHandlerService().deleteFile(fileName);
    }
}

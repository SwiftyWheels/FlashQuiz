package com.patrickhogg.flashquiz.services.file;

import com.patrickhogg.flashquiz.objects.Card;
import com.patrickhogg.flashquiz.objects.Deck;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Patrick Hogg
 */
public class FileHandlerServiceImpl implements FileHandlerService {

    private final String FILE_PATH = "src/main/resources/decks";

    @Override
    public void saveDeckToFile(Deck deck) {
        File fileToSave = getFileFromPath(FILE_PATH,
                                          deck.getBtnViewDeck().getText());
        // Open a new buffered writer, and write to the file name students
        // .txt, with the append argument set to true
        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter(FILE_PATH + deck.getBtnViewDeck().getText()))) {
            for (Card card : deck.getCardList()) {
                // Write to the file with the students name and grade delimited
                // by a colon.
                bufferedWriter.write(card.getQuestionText() + "@@@"
                                     + card.getQuestionAnswer() + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ObservableList<Deck> loadDecksFromFiles() {
        // Empty ArrayList that will contain references to students
        ObservableList<Deck> deckList = FXCollections.observableArrayList();
        // Create a new file from the path given
        List<File> fileList = getFilesFromPath(FILE_PATH);
        for (File file : fileList) {
            Deck deck = loadDeckFromFile(file);
            deckList.add(deck);
        }
        // Return the students
        return deckList;
    }

    @Override
    public Deck loadDeckFromFile(File file) {
        Deck deck = new Deck(file.getName());
        if (file.exists()) {
            // Buffered Reader with resources, this will read the file
            try (BufferedReader bufferedReader = new BufferedReader(
                    new FileReader(file))) {
                // Keep track of the line that the buffered reader is
                // currently reading
                String line;
                // Loop while the line contains data
                while ((line = bufferedReader.readLine()) != null) {
                    // Create a String array that will hold the student info
                    // and fill it with the data from the file
                    // Which is delimited by a colon. Bart:55 = {Bart,55}
                    String[] cardInfo = line.split("@@@");
                    // Set the student name to the first element in the
                    // studentInfo array
                    String cardQuestion = cardInfo[0];
                    String cardAnswer = cardInfo[1];

                    Card card = new Card(cardQuestion, cardAnswer);
                    // Add the new student to the students arrayList
                    deck.getCardList().add(card);
                }
            } catch (Exception e) { // Catch any exception
                e.printStackTrace(); // Print the stacktrace
            }
        } else { // The file doesn't exist, so we can't load the students
            System.err.println("Can't load deck, file doesn't exist!");
        }
        return deck;
    }

    @Override
    public List<File> getFilesFromPath(String pathName) {
        List<File> fileList = new ArrayList<>();
        File folder = new File(pathName);
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        fileList.add(file);
                    }
                }
            }
        }
        return fileList;
    }

    @Override
    public File getFileFromPath(String pathName, String fileName) {
        for (File file : getFilesFromPath(pathName)) {
            if (file.getName().equals(fileName)) {
                return file;
            }
        }
        return null;
    }

    @Override
    public void deleteFileFromPath(String pathName, String fileName) {
        File fileToDelete = getFileFromPath(pathName, fileName);
        if (fileToDelete != null && fileToDelete.exists()
            && !fileToDelete.isDirectory()) {
            fileToDelete.delete();
        }
    }

    @Override
    public void deleteFile(String fileName) {
        deleteFileFromPath(FILE_PATH, fileName);
    }
}

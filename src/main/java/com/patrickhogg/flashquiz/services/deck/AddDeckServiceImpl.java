package com.patrickhogg.flashquiz.services.deck;

import com.patrickhogg.flashquiz.controllers.AddDeckController;
import com.patrickhogg.flashquiz.controllers.MainScreenController;
import com.patrickhogg.flashquiz.objects.Deck;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.TextAlignment;

import java.io.IOException;

/**
 * @author Patrick Hogg
 */
public class AddDeckServiceImpl implements AddDeckService {
    AddDeckController addDeckController;
    MainScreenController mainScreenController;

    public AddDeckServiceImpl(AddDeckController addDeckController,
                              MainScreenController mainScreenController) {
        this.addDeckController = addDeckController;
        this.mainScreenController = mainScreenController;
    }

    public AddDeckController getAddDeckController() {
        return addDeckController;
    }

    public void setAddDeckController(AddDeckController addDeckController) {
        this.addDeckController = addDeckController;
    }

    public MainScreenController getMainScreenController() {
        return mainScreenController;
    }

    public void setMainScreenController(
            MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }

    @Override
    public void addDeck(String deckTitle) {
        Deck deck = new Deck(deckTitle);
        if (!getMainScreenController().getDeckList().contains(deck)) {
            addDeckToGrid(deck);
            getMainScreenController().getMainScreenService()
                                     .getFileHandlerController()
                                     .getFileHandlerService().saveDeckToFile(deck);
            getAddDeckController().getTxtDeckTitle().clear();
            getAddDeckController().displaySuccessMessage();
        }else{
            getAddDeckController().displayErrorMessage();
        }
    }

    @Override
    public void addDeckToGrid(Deck deck) {
        if (!getMainScreenController().getDeckList().contains(deck)) {
            int numRows =
                    getMainScreenController().getGridDeckContainer().getRowCount()
                    + 1;

            Button deckButton = deck.getBtnViewDeck();
            deck.getLblCardAmount().setText(
                    Integer.toString(deck.getCardList().size()));
            Label deckLabel = deck.getLblCardAmount();
            RowConstraints rowConstraints = new RowConstraints(10);
            rowConstraints.setPercentHeight(-1);
            rowConstraints.setVgrow(Priority.ALWAYS);

            getMainScreenController().getGridDeckContainer().getRowConstraints()
                                     .add(rowConstraints);

            getMainScreenController().getDeckList().add(deck);

            getMainScreenController().getGridDeckContainer().add(deckButton, 0,
                                                                 numRows);
            getMainScreenController().getGridDeckContainer().add(deckLabel, 1,
                                                                 numRows);

            deckButton.setMaxWidth(Double.MAX_VALUE);
            deckButton.setTextAlignment(TextAlignment.CENTER);

            getMainScreenController().getGridDeckContainer().setVgap(10);
            getMainScreenController().getGridDeckContainer().setHgap(10);

            GridPane.setHalignment(deckButton, HPos.CENTER);
            GridPane.setHalignment(deckLabel, HPos.CENTER);

            getMainScreenController().getGridDeckContainer().getRowConstraints()
                                     .add(rowConstraints);
            deckButton.setOnAction(actionEvent -> {
                try {
                    getMainScreenController().getMainScreenService().openDeck(
                            getMainScreenController().getMainScreenService()
                                                     .getDeckByName(
                                                             deckButton.getText()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
}

package com.patrickhogg.flashquiz.services.deck;

import com.patrickhogg.flashquiz.controllers.MainScreenController;
import com.patrickhogg.flashquiz.controllers.RemoveDeckController;
import com.patrickhogg.flashquiz.objects.Deck;
import javafx.scene.layout.GridPane;

/**
 * @author Patrick Hogg
 */
public class RemoveDeckServiceImpl implements RemoveDeckService {
    RemoveDeckController removeDeckController;
    MainScreenController mainScreenController;

    public RemoveDeckServiceImpl(RemoveDeckController removeDeckController,
                                 MainScreenController mainScreenController) {
        this.removeDeckController = removeDeckController;
        this.mainScreenController = mainScreenController;
    }

    public RemoveDeckController getRemoveDeckController() {
        return removeDeckController;
    }

    public void setRemoveDeckController(
            RemoveDeckController removeDeckController) {
        this.removeDeckController = removeDeckController;
    }

    public MainScreenController getMainScreenController() {
        return mainScreenController;
    }

    public void setMainScreenController(
            MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }

    @Override
    public void removeDeck(Deck deck) {
        GridPane gridPane = getMainScreenController().getGridDeckContainer();
        getMainScreenController().getDeckList().remove(deck);
        gridPane.getChildren().clear();
        gridPane.getRowConstraints().clear();
        getMainScreenController().getDeckList().clear();

        getMainScreenController().getMainScreenService()
                                 .getFileHandlerController().deleteFile(
                                         deck.getBtnViewDeck().getText());

        getMainScreenController().getMainScreenService().initDecks();
    }
}


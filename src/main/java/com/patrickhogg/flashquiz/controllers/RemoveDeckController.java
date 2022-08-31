package com.patrickhogg.flashquiz.controllers;

import com.patrickhogg.flashquiz.objects.Deck;
import com.patrickhogg.flashquiz.services.deck.RemoveDeckServiceImpl;
import com.patrickhogg.flashquiz.services.main.MainScreenServiceImpl;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

/**
 * @author Patrick Hogg
 */
public class RemoveDeckController {

    public ChoiceBox<Deck> choiceDeckChoice;
    public Button btnAddCard;
    public Label lblErrorLabel;

    MainScreenServiceImpl mainScreenService;
    RemoveDeckServiceImpl removeDeckService;

    public ChoiceBox<Deck> getChoiceDeckChoice() {
        return choiceDeckChoice;
    }

    public void setChoiceDeckChoice(ChoiceBox<Deck> choiceDeckChoice) {
        this.choiceDeckChoice = choiceDeckChoice;
    }

    public Button getBtnAddCard() {
        return btnAddCard;
    }

    public void setBtnAddCard(Button btnAddCard) {
        this.btnAddCard = btnAddCard;
    }

    public Label getLblErrorLabel() {
        return lblErrorLabel;
    }

    public void setLblErrorLabel(Label lblErrorLabel) {
        this.lblErrorLabel = lblErrorLabel;
    }

    public MainScreenServiceImpl getMainScreenService() {
        return mainScreenService;
    }

    public void setMainScreenService(MainScreenServiceImpl mainScreenService) {
        this.mainScreenService = mainScreenService;
    }

    public RemoveDeckServiceImpl getRemoveDeckService() {
        return removeDeckService;
    }

    public void setRemoveDeckService(RemoveDeckServiceImpl removeDeckService) {
        this.removeDeckService = removeDeckService;
    }

    public void onBtnRemoveDeckAction() {
        Deck deckToRemove = getChoiceDeckChoice().getSelectionModel()
                                                 .getSelectedItem();
        if (deckToRemove != null) {
            removeDeckService.removeDeck(deckToRemove);
            displaySuccessMessage();
        }else{
            displayErrorMessage();
        }
    }

    private void displaySuccessMessage(){
        getLblErrorLabel().setVisible(true);
        getLblErrorLabel().setStyle("-fx-text-fill: rgb(0, 255, 0)");
        getLblErrorLabel().setText("Successfully removed deck!");
    }

    private void displayErrorMessage(){
        getLblErrorLabel().setVisible(true);
        getLblErrorLabel().setStyle("-fx-text-fill: rgb(255, 0, 0)");
        getLblErrorLabel().setText("Error: Can't remove deck!");
    }
}

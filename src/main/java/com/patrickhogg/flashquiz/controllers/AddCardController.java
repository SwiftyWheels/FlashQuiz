package com.patrickhogg.flashquiz.controllers;

import com.patrickhogg.flashquiz.objects.Deck;
import com.patrickhogg.flashquiz.services.main.MainScreenServiceImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 * @author Patrick Hogg
 */
public class AddCardController {
    @FXML public TextArea txtAreaDeckQuestion;
    @FXML public TextArea txtAreaDeckAnswer;
    @FXML public Button btnAddCard;
    @FXML public ChoiceBox<Deck> choiceDeckChoice;

    public Label lblErrorMessage;
    private MainScreenServiceImpl mainScreenService;

    public TextArea getTxtAreaDeckQuestion() {
        return txtAreaDeckQuestion;
    }

    public void setTxtAreaDeckQuestion(TextArea txtAreaDeckQuestion) {
        this.txtAreaDeckQuestion = txtAreaDeckQuestion;
    }

    public TextArea getTxtAreaDeckAnswer() {
        return txtAreaDeckAnswer;
    }

    public void setTxtAreaDeckAnswer(TextArea txtAreaDeckAnswer) {
        this.txtAreaDeckAnswer = txtAreaDeckAnswer;
    }

    public Button getBtnAddCard() {
        return btnAddCard;
    }

    public void setBtnAddCard(Button btnAddCard) {
        this.btnAddCard = btnAddCard;
    }

    public ChoiceBox<Deck> getChoiceDeckChoice() {
        return choiceDeckChoice;
    }

    public void setChoiceDeckChoice(ChoiceBox<Deck> choiceDeckChoice) {
        this.choiceDeckChoice = choiceDeckChoice;
    }

    public Label getLblErrorLabel() {
        return lblErrorMessage;
    }

    public void setLblErrorLabel(Label lblErrorLabel) {
        this.lblErrorMessage = lblErrorLabel;
    }

    public MainScreenServiceImpl getMainScreenService() {
        return mainScreenService;
    }

    public void setMainScreenService(MainScreenServiceImpl mainScreenService) {
        this.mainScreenService = mainScreenService;
    }

    @FXML
    public void onBtnAddCardAction() {
        Deck deckToAddCardTo = getChoiceDeckChoice().getSelectionModel()
                                                    .getSelectedItem();
        if (deckToAddCardTo != null) {
            getMainScreenService().getAddCardService().addCardToDeck(
                    getChoiceDeckChoice().getSelectionModel()
                                         .getSelectedItem());
        }else{
            displayErrorMessage();
        }
    }

    public void displaySuccessMessage() {
        getLblErrorLabel().setVisible(true);
        getLblErrorLabel().setStyle("-fx-text-fill: rgb(0, 255, 0)");
        getLblErrorLabel().setText("Successfully added card!");
    }

    public void displayErrorMessage() {
        getLblErrorLabel().setVisible(true);
        getLblErrorLabel().setStyle("-fx-text-fill: rgb(255, 0, 0)");
        getLblErrorLabel().setText("Error: Can't add card!");
    }
}

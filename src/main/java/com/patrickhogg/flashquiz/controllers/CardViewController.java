package com.patrickhogg.flashquiz.controllers;

import com.patrickhogg.flashquiz.objects.Card;
import com.patrickhogg.flashquiz.services.main.MainScreenServiceImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author Patrick Hogg
 */
public class CardViewController {
    @FXML public VBox vBoxCardQuestion;
    @FXML public Label lblCardQuestion;
    @FXML public VBox vBoxCardAnswer;
    @FXML public Label lblCardAnswer;
    @FXML public Button btnGoBack;
    @FXML public Button btnShowAnswer;
    @FXML public Button btnNextCard;
    @FXML public Button btnRemoveCard;
    public Label lblCardIndex;

    Card card;

    MainScreenServiceImpl mainScreenService;


    public VBox getvBoxCardQuestion() {
        return vBoxCardQuestion;
    }

    public void setvBoxCardQuestion(VBox vBoxCardQuestion) {
        this.vBoxCardQuestion = vBoxCardQuestion;
    }

    public Label getLblCardQuestion() {
        return lblCardQuestion;
    }

    public void setLblCardQuestion(Label lblCardQuestion) {
        this.lblCardQuestion = lblCardQuestion;
    }

    public VBox getvBoxCardAnswer() {
        return vBoxCardAnswer;
    }

    public void setvBoxCardAnswer(VBox vBoxCardAnswer) {
        this.vBoxCardAnswer = vBoxCardAnswer;
    }

    public Label getLblCardAnswer() {
        return lblCardAnswer;
    }

    public void setLblCardAnswer(Label lblCardAnswer) {
        this.lblCardAnswer = lblCardAnswer;
    }

    public Button getBtnGoBack() {
        return btnGoBack;
    }

    public void setBtnGoBack(Button btnGoBack) {
        this.btnGoBack = btnGoBack;
    }

    public Button getBtnShowAnswer() {
        return btnShowAnswer;
    }

    public void setBtnShowAnswer(Button btnShowAnswer) {
        this.btnShowAnswer = btnShowAnswer;
    }

    public Button getBtnNextCard() {
        return btnNextCard;
    }

    public void setBtnNextCard(Button btnNextCard) {
        this.btnNextCard = btnNextCard;
    }

    public Button getBtnRemoveCard() {
        return btnRemoveCard;
    }

    public void setBtnRemoveCard(Button btnRemoveCard) {
        this.btnRemoveCard = btnRemoveCard;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Label getLblCardIndex() {
        return lblCardIndex;
    }

    public void setLblCardIndex(Label lblCardIndex) {
        this.lblCardIndex = lblCardIndex;
    }

    public MainScreenServiceImpl getMainScreenService() {
        return mainScreenService;
    }

    public void setMainScreenService(MainScreenServiceImpl mainScreenService) {
        this.mainScreenService = mainScreenService;
    }

    @FXML
    public void onBtnGoBackAction() {
        getMainScreenService().getCardViewService().goBackToMainScreen(
                (Stage) getBtnGoBack().getScene().getWindow());
    }

    @FXML
    public void onBtnShowAnswerAction() {
        getMainScreenService().getCardViewService().showAnswer();
    }

    @FXML
    public void onBtnNextCardAction() {
        Stage stage = (Stage) getBtnGoBack().getScene().getWindow();
        String questionText = getLblCardQuestion().getText();

        getMainScreenService().getCardViewService().nextCard(stage.getTitle(),
                                                             questionText);
    }

    @FXML
    public void onBtnRemoveCard() {
        Stage stage = (Stage) getBtnGoBack().getScene().getWindow();
        String questionText = getLblCardQuestion().getText();

        getMainScreenService().getCardViewService().removeCard(stage.getTitle(),
                                                             questionText);
    }
}


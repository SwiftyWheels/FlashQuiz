package com.patrickhogg.flashquiz.controllers;

import com.patrickhogg.flashquiz.objects.Deck;
import com.patrickhogg.flashquiz.services.main.MainScreenServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class MainScreenController {
    @FXML public Button btnAddCard;
    @FXML public GridPane gridDeckContainer;
    @FXML public Button btnCreateDeck;
    @FXML public Label lblCardAmount;
    @FXML public Button btnViewDeck;

    ObservableList<Deck> deckList = FXCollections.observableArrayList();
    MainScreenServiceImpl mainScreenService;


    public Button getBtnAddCard() {
        return btnAddCard;
    }

    public void setBtnAddCard(Button btnAddCard) {
        this.btnAddCard = btnAddCard;
    }

    public GridPane getGridDeckContainer() {
        return gridDeckContainer;
    }

    public void setGridDeckContainer(GridPane gridDeckContainer) {
        this.gridDeckContainer = gridDeckContainer;
    }

    public Button getBtnCreateDeck() {
        return btnCreateDeck;
    }

    public void setBtnCreateDeck(Button btnCreateDeck) {
        this.btnCreateDeck = btnCreateDeck;
    }

    public Label getLblCardAmount() {
        return lblCardAmount;
    }

    public void setLblCardAmount(Label lblCardAmount) {
        this.lblCardAmount = lblCardAmount;
    }

    public Button getBtnViewDeck() {
        return btnViewDeck;
    }

    public void setBtnViewDeck(Button btnViewDeck) {
        this.btnViewDeck = btnViewDeck;
    }

    public ObservableList<Deck> getDeckList() {
        return deckList;
    }

    public void setDeckList(ObservableList<Deck> deckList) {
        this.deckList = deckList;
    }

    public MainScreenServiceImpl getMainScreenService() {
        return mainScreenService;
    }

    public void setMainScreenService(MainScreenServiceImpl mainScreenService) {
        this.mainScreenService = mainScreenService;
    }

    @FXML
    public void onButtonCreateDeckAction() {
        try {
            getMainScreenService().openCreateDeck();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onButtonAddCardAction() {
        try {
            getMainScreenService().openAddCard();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onButtonRemoveDeckAction() {
        try {
            getMainScreenService().openRemoveDeck();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

package com.patrickhogg.flashquiz.objects;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Patrick Hogg
 */
public class Deck {
    @FXML public Label lblCardAmount = new Label();
    @FXML public Button btnViewDeck = new Button();

    List<Card> cardList = new ArrayList<>();

    public Deck(String deckTitle) {
        // Setting the text properties of the deck object
        btnViewDeck.setText(deckTitle);
        lblCardAmount.setText(Integer.toString(cardList.size()));

        //Styling the deck button
        btnViewDeck.setStyle("-fx-background-color: rgb(89, 89, 89);"
                             + "-fx-text-fill:  rgb(255, 255, 255);");

        //Styling the deck amount label
        lblCardAmount.setStyle("-fx-text-fill:  rgb(255, 255, 255);");
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

    public List<Card> getCardList() {
        return cardList;
    }

    public void setCardList(List<Card> cardList) {
        this.cardList = cardList;
    }

    public boolean addCard(Card card) {
        return getCardList().add(card);
    }

    public boolean removeCard(Card card) {
        return getCardList().remove(card);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Deck deck)) {
            return false;
        }
        return Objects.equals(btnViewDeck.getText(), deck.btnViewDeck.getText());
    }

    @Override
    public int hashCode() {
        return Objects.hash(lblCardAmount, btnViewDeck, cardList);
    }

    @Override
    public String toString() {
        return getBtnViewDeck().getText();
    }
}

package com.patrickhogg.flashquiz.services.card;

import com.patrickhogg.flashquiz.controllers.CardViewController;
import com.patrickhogg.flashquiz.objects.Card;
import com.patrickhogg.flashquiz.objects.Deck;
import com.patrickhogg.flashquiz.services.main.MainScreenServiceImpl;
import javafx.stage.Stage;

/**
 * @author Patrick Hogg
 */
public class CardViewServiceImpl implements CardViewService {

    CardViewController cardViewController;
    MainScreenServiceImpl mainScreenService;

    public CardViewServiceImpl(CardViewController cardViewController,
                               MainScreenServiceImpl mainScreenService) {
        this.cardViewController = cardViewController;
        this.mainScreenService = mainScreenService;
    }

    public CardViewController getCardViewController() {
        return cardViewController;
    }

    public void setCardViewController(CardViewController cardViewController) {
        this.cardViewController = cardViewController;
    }

    public MainScreenServiceImpl getMainScreenService() {
        return mainScreenService;
    }

    public void setMainScreenService(MainScreenServiceImpl mainScreenService) {
        this.mainScreenService = mainScreenService;
    }

    @Override
    public void goBackToMainScreen(Stage stage) {
        stage.close();
    }

    @Override
    public void showAnswer() {
        getCardViewController().getLblCardAnswer().setVisible(true);
    }

    @Override
    public void nextCard(String deckTitle, String questionText) {
        Deck deck = getMainScreenService().getDeckByName(deckTitle);
        Card currentCard = getCardByQuestion(deck, questionText);

        int nextCardIndex = deck.getCardList().indexOf(currentCard) + 1;
        int currentIndex = nextCardIndex + 1;

        if (nextCardIndex != deck.getCardList().size()) {
            Card nextCard = deck.getCardList().get(nextCardIndex);
            getCardViewController().getLblCardQuestion().setStyle(
                    "-fx-text-fill: rgb(255, 255, 255)");

            getCardViewController().setCard(nextCard);
            getCardViewController().getLblCardQuestion().setText(
                    nextCard.getQuestionText());
            getCardViewController().getLblCardAnswer().setText(
                    nextCard.getQuestionAnswer());
            getCardViewController().getLblCardAnswer().setVisible(false);
            getCardViewController().getLblCardIndex().setText(
                    currentIndex + "/" + deck.getCardList().size());
        } else {
            getCardViewController().getLblCardQuestion().setText(
                    "Congratulations! You've reached the end of the deck!");
            getCardViewController().getLblCardQuestion().setStyle(
                    "-fx-text-fill: rgb(0, 255, 0)");
            getCardViewController().getLblCardAnswer().setText("");
        }

    }

    @Override
    public void removeCard(String deckTitle, String questionText) {
        Deck deck = getMainScreenService().getDeckByName(deckTitle);
        Card currentCard = getCardByQuestion(deck, questionText);

        deck.removeCard(currentCard);
        getMainScreenService().updateDeckAmount(deck);
        nextCard(deckTitle, questionText);
        getMainScreenService().getFileHandlerController().saveDeckToFile(deck);
    }

    @Override
    public Card getCardByQuestion(Deck deck, String question) {
        for (Card card : deck.getCardList()) {
            if (card.getQuestionText().equalsIgnoreCase(question)) {
                return card;
            }
        }
        return null;
    }
}


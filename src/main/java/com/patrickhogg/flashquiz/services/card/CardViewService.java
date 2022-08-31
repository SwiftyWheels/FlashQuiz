package com.patrickhogg.flashquiz.services.card;

import com.patrickhogg.flashquiz.objects.Card;
import com.patrickhogg.flashquiz.objects.Deck;
import javafx.stage.Stage;

/**
 * @author Patrick Hogg
 */
public interface CardViewService {
    void goBackToMainScreen(Stage stage);
    void showAnswer();
    void nextCard(String deckTitle, String questionText);

    void removeCard(String deckTitle, String questionText);
    Card getCardByQuestion(Deck deck, String question);
}

package com.patrickhogg.flashquiz.services.main;

import com.patrickhogg.flashquiz.objects.Deck;

import java.io.IOException;

/**
 * @author Patrick Hogg
 */
public interface MainScreenService {
    void openCreateDeck() throws IOException;
    void openRemoveDeck() throws IOException;
    void openAddCard() throws IOException;
    void initDecks();
    void openDeck(Deck deck) throws IOException;
    Deck getDeckByName(String name);
    void updateDeckAmount(Deck deck);
}

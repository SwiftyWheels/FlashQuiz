package com.patrickhogg.flashquiz.services.deck;

import com.patrickhogg.flashquiz.objects.Deck;

/**
 * @author Patrick Hogg
 */
public interface AddDeckService {

    void addDeck(String deckTitle);

    void addDeckToGrid(Deck deck);

}

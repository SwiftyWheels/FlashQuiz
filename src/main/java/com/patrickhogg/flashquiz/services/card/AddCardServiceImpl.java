package com.patrickhogg.flashquiz.services.card;

import com.patrickhogg.flashquiz.controllers.AddCardController;
import com.patrickhogg.flashquiz.objects.Card;
import com.patrickhogg.flashquiz.objects.Deck;
import com.patrickhogg.flashquiz.services.main.MainScreenServiceImpl;

/**
 * @author Patrick Hogg
 */
public class AddCardServiceImpl implements AddCardService {

    AddCardController addCardController;
    MainScreenServiceImpl mainScreenService;

    public AddCardServiceImpl(AddCardController addCardController,
                              MainScreenServiceImpl mainScreenService) {
        this.addCardController = addCardController;
        this.mainScreenService = mainScreenService;
    }

    public AddCardController getAddCardController() {
        return addCardController;
    }

    public void setAddCardController(AddCardController addCardController) {
        this.addCardController = addCardController;
    }

    public MainScreenServiceImpl getMainScreenService() {
        return mainScreenService;
    }

    public void setMainScreenService(MainScreenServiceImpl mainScreenService) {
        this.mainScreenService = mainScreenService;
    }

    @Override
    public void addCardToDeck(Deck deck) {
        String cardQuestion = getAddCardController().getTxtAreaDeckQuestion()
                                                    .getText();
        String cardAnswer = getAddCardController().getTxtAreaDeckAnswer()
                                                  .getText();
        Card card = new Card(cardQuestion, cardAnswer);
        if (!deck.getCardList().contains(card)) {
            deck.addCard(card);
            getMainScreenService().updateDeckAmount(deck);
            getMainScreenService().getFileHandlerController().saveDeckToFile(
                    deck);
            getAddCardController().getTxtAreaDeckQuestion().clear();
            getAddCardController().getTxtAreaDeckAnswer().clear();
            getAddCardController().displaySuccessMessage();
        } else {
            getAddCardController().displayErrorMessage();
        }
    }
}

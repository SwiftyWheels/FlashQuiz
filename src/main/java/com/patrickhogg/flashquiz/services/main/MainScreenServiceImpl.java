package com.patrickhogg.flashquiz.services.main;

import com.patrickhogg.flashquiz.controllers.*;
import com.patrickhogg.flashquiz.objects.Deck;
import com.patrickhogg.flashquiz.services.card.AddCardServiceImpl;
import com.patrickhogg.flashquiz.services.card.CardViewServiceImpl;
import com.patrickhogg.flashquiz.services.deck.AddDeckServiceImpl;
import com.patrickhogg.flashquiz.services.deck.RemoveDeckServiceImpl;
import com.patrickhogg.flashquiz.services.file.FileHandlerServiceImpl;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Patrick Hogg
 */
public class MainScreenServiceImpl implements MainScreenService {
    MainScreenController mainScreenController;
    CardViewController cardViewController = new CardViewController();
    AddDeckController addDeckController = new AddDeckController();
    AddCardController addCardController = new AddCardController();
    FileHandlerController fileHandlerController = new FileHandlerController();
    AddDeckServiceImpl addDeckService;
    AddCardServiceImpl addCardService;
    CardViewServiceImpl cardViewService;
    FileHandlerServiceImpl fileHandlerService;

    public MainScreenServiceImpl(MainScreenController mainScreenController) {

        // Initializing Controllers
        this.mainScreenController = mainScreenController;

        // Initializing Services
        this.addDeckService = new AddDeckServiceImpl(addDeckController,
                                                     mainScreenController);
        this.addCardService = new AddCardServiceImpl(addCardController, this);
        this.cardViewService = new CardViewServiceImpl(cardViewController,
                                                       this);

        // Initializing the file handler service
        this.fileHandlerService = new FileHandlerServiceImpl();

        // Setting the main screen service to the controllers
        this.cardViewController.setMainScreenService(this);
        this.addDeckController.setMainScreenService(this);
        this.addCardController.setMainScreenService(this);

        // Setting the fileHandlerController service to the fileHandlerService
        this.fileHandlerController.setFileHandlerService(fileHandlerService);
    }

    public MainScreenController getMainScreenController() {
        return mainScreenController;
    }

    public void setMainScreenController(
            MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }

    public CardViewController getCardViewController() {
        return cardViewController;
    }

    public void setCardViewController(CardViewController cardViewController) {
        this.cardViewController = cardViewController;
    }

    public AddDeckController getAddDeckController() {
        return addDeckController;
    }

    public void setAddDeckController(AddDeckController addDeckController) {
        this.addDeckController = addDeckController;
    }

    public AddCardController getAddCardController() {
        return addCardController;
    }

    public void setAddCardController(AddCardController addCardController) {
        this.addCardController = addCardController;
    }

    public FileHandlerController getFileHandlerController() {
        return fileHandlerController;
    }

    public void setFileHandlerController(
            FileHandlerController fileHandlerController) {
        this.fileHandlerController = fileHandlerController;
    }

    public AddDeckServiceImpl getAddDeckService() {
        return addDeckService;
    }

    public void setAddDeckService(AddDeckServiceImpl addDeckService) {
        this.addDeckService = addDeckService;
    }

    public AddCardServiceImpl getAddCardService() {
        return addCardService;
    }

    public void setAddCardService(AddCardServiceImpl addCardService) {
        this.addCardService = addCardService;
    }

    public CardViewServiceImpl getCardViewService() {
        return cardViewService;
    }

    public void setCardViewService(CardViewServiceImpl cardViewService) {
        this.cardViewService = cardViewService;
    }

    public FileHandlerServiceImpl getFileHandlerService() {
        return fileHandlerService;
    }

    public void setFileHandlerService(
            FileHandlerServiceImpl fileHandlerService) {
        this.fileHandlerService = fileHandlerService;
    }

    @Override
    public void openCreateDeck() throws IOException {
        FXMLLoader loader = new FXMLLoader(
                MainScreenServiceImpl.class.getResource(
                        "/com/patrickhogg/flashquiz/add-deck-view.fxml"));
        loader.setController(getAddDeckController());
        Scene mainScene = new Scene(loader.load());
        Stage stage = new Stage();

        stage.setTitle("Add Deck");
        stage.setScene(mainScene);
        stage.show();
    }

    @Override
    public void openRemoveDeck() throws IOException {
        FXMLLoader loader = new FXMLLoader(
                MainScreenServiceImpl.class.getResource(
                        "/com/patrickhogg/flashquiz/remove-deck-view.fxml"));
        Scene mainScene = new Scene(loader.load());
        Stage stage = new Stage();
        RemoveDeckController removeDeckController = loader.getController();
        removeDeckController.setMainScreenService(this);
        removeDeckController.setRemoveDeckService(
                new RemoveDeckServiceImpl(removeDeckController,
                                          getMainScreenController()));
        loader.setController(removeDeckController);

        stage.setTitle("Remove Decks");
        stage.setScene(mainScene);
        stage.show();

        removeDeckController.getChoiceDeckChoice().setItems(
                getMainScreenController().getDeckList());
    }

    @Override
    public void openAddCard() throws IOException {
        FXMLLoader loader = new FXMLLoader(
                MainScreenServiceImpl.class.getResource(
                        "/com/patrickhogg/flashquiz/add-card-view.fxml"));

        loader.setController(getAddCardController());
        Scene mainScene = new Scene(loader.load());
        Stage stage = new Stage();

        stage.setTitle("Add Deck");
        stage.setScene(mainScene);
        stage.show();
        getAddCardController().getChoiceDeckChoice().setItems(
                getMainScreenController().getDeckList());
    }

    @Override
    public void initDecks() {
        ObservableList<Deck> decks
                = getFileHandlerController().loadDecksFromFiles();

        if (decks != null) {
            for (Deck deck : decks) {
                getAddDeckService().addDeckToGrid(deck);
            }
        }

    }

    @Override
    public void openDeck(Deck deck) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                MainScreenServiceImpl.class.getResource(
                        "/com/patrickhogg/flashquiz/card-view.fxml"));

        loader.setController(getCardViewController());
        Scene mainScene = new Scene(loader.load());
        Stage stage = new Stage();

        stage.setTitle(deck.getBtnViewDeck().getText());
        stage.setScene(mainScene);

        if (deck.getCardList() != null && !deck.getCardList().isEmpty()) {
            stage.show();

            getCardViewController().setCard(deck.getCardList().get(0));
            getCardViewController().getLblCardQuestion().setText(
                    getCardViewController().getCard().getQuestionText());
            getCardViewController().getLblCardAnswer().setText(
                    getCardViewController().getCard().getQuestionAnswer());
            getCardViewController().getLblCardIndex().setText("1/" + deck.getCardList().size());
        }else{
            deck.getLblCardAmount().setText("Error: Deck is empty!");
            deck.getLblCardAmount().setStyle("-fx-text-fill: rgb(255, 0, 0)");
        }
    }

    @Override
    public Deck getDeckByName(String name) {
        for (Deck deck : getMainScreenController().getDeckList()) {
            if (deck.getBtnViewDeck().getText().equalsIgnoreCase(name)) {
                return deck;
            }
        }
        return null;
    }

    @Override
    public void updateDeckAmount(Deck deck) {
        deck.getLblCardAmount().setStyle("-fx-text-fill: rgb(255, 255, 255)");
        deck.getLblCardAmount().setText(
                Integer.toString(deck.getCardList().size()));
    }
}

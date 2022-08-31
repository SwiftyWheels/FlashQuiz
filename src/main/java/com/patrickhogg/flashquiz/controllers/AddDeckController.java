package com.patrickhogg.flashquiz.controllers;

import com.patrickhogg.flashquiz.services.main.MainScreenServiceImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * @author Patrick Hogg
 */
public class AddDeckController {
    @FXML public TextField txtDeckTitle;
    @FXML public Button btnAddDeck;

    public Label lblErrorMessage;
    MainScreenServiceImpl mainScreenService;

    public TextField getTxtDeckTitle() {
        return txtDeckTitle;
    }

    public void setTxtDeckTitle(TextField txtDeckTitle) {
        this.txtDeckTitle = txtDeckTitle;
    }

    public Button getBtnAddDeck() {
        return btnAddDeck;
    }

    public void setBtnAddDeck(Button btnAddDeck) {
        this.btnAddDeck = btnAddDeck;
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
    public void addDeckAction() {
        if (getTxtDeckTitle().getText() != null
            && getTxtDeckTitle().getText().length() > 0) {
            getMainScreenService().getAddDeckService().addDeck(
                    getTxtDeckTitle().getText());
        }
    }

    public void displaySuccessMessage(){
        getLblErrorLabel().setVisible(true);
        getLblErrorLabel().setStyle("-fx-text-fill: rgb(0, 255, 0)");
        getLblErrorLabel().setText("Successfully added deck!");
    }

    public void displayErrorMessage(){
        getLblErrorLabel().setVisible(true);
        getLblErrorLabel().setStyle("-fx-text-fill: rgb(255, 0, 0)");
        getLblErrorLabel().setText("Error: Can't add deck!");
    }

}

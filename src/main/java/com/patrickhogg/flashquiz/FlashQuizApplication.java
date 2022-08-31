package com.patrickhogg.flashquiz;

import com.patrickhogg.flashquiz.controllers.*;
import com.patrickhogg.flashquiz.services.main.MainScreenServiceImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FlashQuizApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                FlashQuizApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 707, 637);
        stage.setTitle("Flash Quiz");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        MainScreenController mainScreenController = fxmlLoader.getController();
        MainScreenServiceImpl mainScreenService = new MainScreenServiceImpl(
                mainScreenController);

        mainScreenController.setMainScreenService(mainScreenService);
        mainScreenService.initDecks();
    }

    public static void main(String[] args) {
        launch();
    }
}
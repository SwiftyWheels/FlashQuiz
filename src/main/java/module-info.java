module com.patrickhogg.flashquiz {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.patrickhogg.flashquiz to javafx.fxml;
    exports com.patrickhogg.flashquiz;
    exports com.patrickhogg.flashquiz.controllers;
    exports com.patrickhogg.flashquiz.objects;
    exports com.patrickhogg.flashquiz.services.card;
    exports com.patrickhogg.flashquiz.services.deck;
    exports com.patrickhogg.flashquiz.services.main;
    exports com.patrickhogg.flashquiz.services.file;
    opens com.patrickhogg.flashquiz.controllers to javafx.fxml;
}
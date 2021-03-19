module prajyot.learning.ui {

    requires javafx.fxml;
    requires javafx.controls;
    requires prajyot.learning.db;

    exports prajyot.learning.ui to javafx.graphics, javafx.fxml;
    opens prajyot.learning.ui to javafx.fxml;
}
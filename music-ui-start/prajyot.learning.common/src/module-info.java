module prajyot.learning.common {
    requires javafx.base;
    requires java.sql;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;

    exports prajyot.learning.common;

    opens prajyot.learning.common to javafx.base;


}
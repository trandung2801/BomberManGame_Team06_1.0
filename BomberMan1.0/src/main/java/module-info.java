module Main {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    opens Main to javafx.fxml;
    exports Main;
}
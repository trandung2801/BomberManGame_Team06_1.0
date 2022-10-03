module Main {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.desktop;
    opens Main to javafx.fxml;
    exports Main;
}
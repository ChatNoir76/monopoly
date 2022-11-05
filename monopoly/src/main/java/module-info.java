module fr.chatnoir.monopoly {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.desktop;

    opens fr.chatnoir.monopoly to javafx.fxml;
    exports fr.chatnoir.monopoly;
}

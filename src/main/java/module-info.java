module com.hamitmizrak.ecodation_techistanbul_blockchain {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.hamitmizrak.ecodation_techistanbul_blockchain to javafx.fxml;
    exports com.hamitmizrak.ecodation_techistanbul_blockchain;
}
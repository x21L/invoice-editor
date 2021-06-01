module com.codersbay.invoiceeditor {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;

    opens com.codersbay.invoiceeditor to javafx.fxml;
    opens com.codersbay.invoiceeditor.controller to javafx.fxml;
    exports com.codersbay.invoiceeditor;
    exports com.codersbay.invoiceeditor.controller;
}

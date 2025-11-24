module org.example.examenfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;


    opens org.example.examenfx to javafx.fxml;
    exports org.example.examenfx;
}
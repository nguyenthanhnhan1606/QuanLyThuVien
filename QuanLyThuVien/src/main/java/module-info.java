module com.ktpm.quanlythuvien {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires java.logging;
    requires javafx.base;
    requires javafx.graphics;
    
    opens com.ktpm.quanlythuvien to javafx.fxml;
    exports com.ktpm.quanlythuvien;
    exports com.ktpm.pojo;
    exports com.ktpm.services;
    exports com.ktpm.utils;
}

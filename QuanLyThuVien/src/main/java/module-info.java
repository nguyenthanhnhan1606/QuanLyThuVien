module com.ktpm.quanlythuvien {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    
    opens com.ktpm.quanlythuvien to javafx.fxml;
    exports com.ktpm.quanlythuvien;
    exports com.ktpm.pojo;
    exports com.ktpm.services;
    exports com.ktpm.utils;
}

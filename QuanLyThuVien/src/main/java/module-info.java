module com.nguyenhoangvu.quanlythuvien {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    
    opens com.ktpm.quanlythuvien to javafx.fxml;
    exports com.ktpm.quanlythuvien;
}

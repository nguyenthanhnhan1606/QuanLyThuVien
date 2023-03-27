package com.ktpm.quanlythuvien;

import com.ktpm.pojo.User;
import java.io.IOException;
import java.text.ParseException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class PrimaryController {
    @FXML private TextField tf;
    @FXML
     private void switchToSecondary() throws ParseException{
       this.tf.setText("");
    }
}

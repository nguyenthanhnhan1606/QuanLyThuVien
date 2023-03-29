/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktpm.quanlythuvien;

import com.ktpm.services.UserService;
import com.ktpm.utils.MessageBox;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

/**
 *
 * @author Admin
 */
public class DangNhapController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private TextField password;
    UserService u = new UserService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void login(ActionEvent evt) {
        try {
            if (u.checkLogin(this.username.getText().trim(), this.password.getText().trim())) {
                App.setRoot("User");
            } else {
                MessageBox.getBox("Thông báo", "Tài khoản hoặc mật khẩu không đúng!!!", Alert.AlertType.ERROR).show();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DangNhapController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DangNhapController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void exit(ActionEvent evt) {
        Alert a = MessageBox.getBox("Thông báo",
                "Bạn có muốn thoát không?",
                Alert.AlertType.CONFIRMATION);
        a.showAndWait().ifPresent(res -> {
            if (res == ButtonType.OK) {
                System.exit(0);
            }
        });
    }

    public void dangKy(ActionEvent evt) throws IOException {
        App.setRoot("DangKy");
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktpm.quanlythuvien;

import com.ktpm.pojo.User;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Admin
 */
public class DangNhapController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    UserService u = new UserService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void login(ActionEvent evt) throws IOException {
        try {
            if (u.checkLoginAdmin(this.username.getText().trim(), this.password.getText().trim())) {
                Stage stage = (Stage) ((Node) evt.getSource()).getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin.fxml"));
                Parent manageView = loader.load();
                Scene scene = new Scene(manageView);
                stage.setScene(scene);
                stage.show();
            } else {
                if (u.checkLogin(this.username.getText().trim(), this.password.getText().trim())) {
                    User ur = u.getU(this.username.getText(), this.password.getText());
                    Stage stage = (Stage) ((Node) evt.getSource()).getScene().getWindow();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("User.fxml"));
                    Parent manageView = loader.load();
                    Scene scene = new Scene(manageView);
                    UserController controller = loader.getController();
                    controller.thongTin(ur);
                    stage.setScene(scene);
                    stage.show();
                } else {
                    MessageBox.getBox("Thông báo", "Tài khoản hoặc mật khẩu không đúng!!!", Alert.AlertType.ERROR).show();
                }
            }
        } catch (SQLException ex) {
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
        Stage stage = (Stage) ((Node) evt.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DangKy.fxml"));
        Parent manageView = loader.load();
        Scene scene = new Scene(manageView);
        stage.setScene(scene);
        stage.show();
    }
}

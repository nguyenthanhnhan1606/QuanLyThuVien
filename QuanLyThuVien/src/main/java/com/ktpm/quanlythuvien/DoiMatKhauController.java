/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktpm.quanlythuvien;

import com.ktpm.pojo.User;
import com.ktpm.services.UserService;
import com.ktpm.utils.MessageBox;
import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

/**
 *
 * @author THANH NHAN
 */
public class DoiMatKhauController {

    private User us;
    public UserService user = new UserService();

    public void setUser(User u) {
        this.us = u;
    }

    @FXML
    private PasswordField password;
    @FXML
    private PasswordField newPassword;
    @FXML
    private PasswordField cfNewPassword;

    public void doiMk(ActionEvent evt) throws SQLException, IOException {
        if (this.password.getText().trim().equals(us.getPassword())) {
            if (this.newPassword.getText().equals(this.cfNewPassword.getText())) {
                if (user.updatePass(us.getId(), this.newPassword.getText())) {
                    MessageBox.getBox("Thông báo", "Đổi mật khẩu thành công", Alert.AlertType.INFORMATION).show();
                    Stage stage = (Stage) ((Node) evt.getSource()).getScene().getWindow();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("DangNhap.fxml"));
                    Parent manageView = loader.load();
                    Scene scene = new Scene(manageView);
                    stage.setScene(scene);
                    stage.show();
                } else {
                    MessageBox.getBox("Thông báo", "Mật khẩu phải từ 6-45 kí tự. Bao gồm chữ hoa, thường và kí tự đặc biệt", Alert.AlertType.ERROR).show();

                }
            } else {
                MessageBox.getBox("Thông báo", "Mật khẩu mới và xác nhận mật khẩu không khớp!!!", Alert.AlertType.ERROR).show();

            }

        } else {
            MessageBox.getBox("Thông báo", "Mật khẩu cũ không đúng!!", Alert.AlertType.ERROR).show();

        }
    }

    public void userTT(ActionEvent evt) throws IOException, SQLException {
        Stage stage = (Stage) ((Node) evt.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserThongTin.fxml"));
        Parent manageView = loader.load();
        Scene scene = new Scene(manageView);
        UserThongTinController controller = loader.getController();
        controller.setUser(us);
        stage.setScene(scene);
        stage.show();
    }

}

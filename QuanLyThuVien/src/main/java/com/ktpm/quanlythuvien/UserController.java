/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktpm.quanlythuvien;

import com.ktpm.pojo.User;
import com.ktpm.pojo.data2;
import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Admin
 */
public class UserController {

    private User u;

    public void thongTin(User us) {
        this.u = us;
    }

    public void userTT(ActionEvent evt) throws IOException, SQLException {
        Stage stage = (Stage) ((Node) evt.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserThongTin.fxml"));
        Parent manageView = loader.load();
        Scene scene = new Scene(manageView);
        UserThongTinController controller = loader.getController();
        controller.setUser(u);
        stage.setScene(scene);
        stage.show();
    }

    public void userMuon(ActionEvent evt) throws IOException, SQLException {
        Stage stage = (Stage) ((Node) evt.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserMuonSach.fxml"));
        Parent manageView = loader.load();
        Scene scene = new Scene(manageView);
        UserMuonSachController controller = loader.getController();
        controller.setUser(u);
        stage.setScene(scene);
        stage.show();
    }

    public void userLs(ActionEvent evt) throws IOException, SQLException {
        data2.setId(u.getId());
        Stage stage = (Stage) ((Node) evt.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LichSuMuon.fxml"));
        Parent manageView = loader.load();
        Scene scene = new Scene(manageView);
        UserLichSuController controller = loader.getController();
        controller.setUser(u);
        stage.setScene(scene);
        stage.show();
    }

    public void dangXuat(ActionEvent evt) throws IOException {
        Stage stage = (Stage) ((Node) evt.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DangNhap.fxml"));
        Parent manageView = loader.load();
        Scene scene = new Scene(manageView);
        stage.setScene(scene);
        stage.show();
    }

}

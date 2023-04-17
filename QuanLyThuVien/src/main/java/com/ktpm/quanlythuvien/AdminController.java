/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktpm.quanlythuvien;

import com.ktpm.pojo.User;
import com.ktpm.utils.MessageBox;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 *
 * @author Admin
 */
public class AdminController {

    private User us;
    
    
    public void setUser(User u)
    {
        this.us=u;
    }
    public void thoat(ActionEvent evt) {
        Alert a = MessageBox.getBox("Thông báo",
                "Bạn có muốn thoát không?",
                Alert.AlertType.CONFIRMATION);
        a.showAndWait().ifPresent(res -> {
            if (res == ButtonType.OK) {
                try {
                    Stage stage = (Stage) ((Node) evt.getSource()).getScene().getWindow();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("DangNhap.fxml"));
                    Parent manageView = loader.load();
                    Scene scene = new Scene(manageView);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(QuanLySachController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    public void quanLySach(ActionEvent evt) throws IOException {
        Stage stage = (Stage) ((Node) evt.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("QuanLySach.fxml"));
        Parent manageView = loader.load();
        Scene scene = new Scene(manageView);
        QuanLySachController controller = loader.getController();
        controller.setUser(us);
        stage.setScene(scene);
        stage.show();
    }

    public void quanLyDG(ActionEvent evt) throws IOException {
        Stage stage = (Stage) ((Node) evt.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("QuanLyDocGia.fxml"));
        Parent manageView = loader.load();
        Scene scene = new Scene(manageView);
        DanhSachBanDocController controller = loader.getController();
        controller.setUser(us);
        stage.setScene(scene);
        stage.show();
    }

     public void baoCaoTK(ActionEvent evt) throws IOException {
        Stage stage = (Stage) ((Node) evt.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BaoCaoTK.fxml"));
        Parent manageView = loader.load();
        Scene scene = new Scene(manageView);
        BaoCaoTKController controller = loader.getController();
        controller.setUser(us);
        stage.setScene(scene);
        stage.show();
    }
    public void xacNhanSach(ActionEvent evt) throws IOException {
        Stage stage = (Stage) ((Node) evt.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("QuanLyXacNhanMuon.fxml"));
        Parent manageView = loader.load();
        Scene scene = new Scene(manageView);
        XacNhanSachController controller = loader.getController();
        controller.setUser(us);
        stage.setScene(scene);
        stage.show();
    }

    public void quanLyTraSach(ActionEvent evt) throws IOException {
        Stage stage = (Stage) ((Node) evt.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("QuanLyTraSach.fxml"));
        Parent manageView = loader.load();
        Scene scene = new Scene(manageView);
        QuanLyTraSachController controller = loader.getController();
        controller.setUser(us);
        stage.setScene(scene);
        stage.show();
    }
    
    public void quanLyMuonSach(ActionEvent evt) throws IOException {
        Stage stage = (Stage) ((Node) evt.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("QuanLyMuonSach.fxml"));
        Parent manageView = loader.load();
        Scene scene = new Scene(manageView);
        QuanLyMuonSachController controller = loader.getController();
        controller.setUser(us);
        stage.setScene(scene);
        stage.show();
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktpm.quanlythuvien;

import com.ktpm.utils.MessageBox;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Admin
 */
public class AdminController {
    
    public void thoat(ActionEvent evt){
        Alert a = MessageBox.getBox("Thông báo",
                "Bạn có muốn thoát không?",
                Alert.AlertType.CONFIRMATION);
        a.showAndWait().ifPresent(res -> {
            if (res == ButtonType.OK) {
                try {
                    App.setRoot("DangNhap");
                } catch (IOException ex) {
                    Logger.getLogger(QuanLySachController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }
    
    public void quanLySach(ActionEvent evt) throws IOException{
        App.setRoot("QuanLySach");
    }
    
    public void quanLyDG(ActionEvent evt) throws IOException{
        App.setRoot("QuanLyDocGia");
    }
    
    public void quanLyMT(ActionEvent evt) throws IOException{
        App.setRoot("QuanLyMuonTraSach");
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktpm.utils;

import com.ktpm.quanlythuvien.App;
import java.io.IOException;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

/**
 *
 * @author THANH NHAN
 */
public class MessageBox {
/**
 *
 * @author admin
 */
    public static Alert getBox(String title,String header, String content, Alert.AlertType type) {
        Alert a = new Alert(type);
        a.setTitle(title);
        a.setHeaderText(header);
        a.setContentText(content);
        return a;
    }
     public static Alert getBox1(String title, String content, Alert.AlertType type) throws IOException {
        Alert a = new Alert(type);
        a.setTitle(title);
        a.setContentText(content);
         ButtonType bty= new ButtonType("YES",ButtonBar.ButtonData.YES);
        ButtonType btn= new ButtonType("No",ButtonBar.ButtonData.NO);
        ButtonType btc= new ButtonType("CANCEL",ButtonBar.ButtonData.CANCEL_CLOSE);
        a.getButtonTypes().setAll(bty,btn,btc);
        
         Optional<ButtonType> result = a.showAndWait();
       
        return a;
    }
}


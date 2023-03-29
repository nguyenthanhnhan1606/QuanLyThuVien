/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktpm.utils;

import com.ktpm.quanlythuvien.App;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import static javafx.scene.control.ButtonType.YES;

/**
 *
 * @author THANH NHAN
 */
public class MessageBox {
/**
 *
 * @author admin
 */
    public static Alert getBox(String title, String header, Alert.AlertType type) {
        Alert a = new Alert(type);
        a.setTitle(title);
        a.setHeaderText(header);
        return a;
    }
}


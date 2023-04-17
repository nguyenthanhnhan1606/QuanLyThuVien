/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktpm.quanlythuvien;

import com.ktpm.pojo.BoPhan;
import com.ktpm.pojo.DoiTuong;
import com.ktpm.pojo.User;
import com.ktpm.services.BoPhanService;
import com.ktpm.services.DoiTuongService;
import com.ktpm.services.PasswordService;
import com.ktpm.services.UserService;
import com.ktpm.utils.MessageBox;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Admin
 */
public class UserThongTinController {

    private User us;
    UserService user = new UserService();

    @FXML
    TextField maDocGia;
    @FXML
    TextField ten;
    @FXML
    TextField gioitinh;
    @FXML
    DatePicker ngaysinh;
    @FXML
    ComboBox<DoiTuong> cbdoituong;
    @FXML
    ComboBox<BoPhan> cbboPhan;
    @FXML
    TextField hanthe;
    @FXML
    TextField email;
    @FXML
    TextField diachi;
    @FXML
    TextField sdt;

    public void setUser(User u) {
        this.us = u;
        loadBP();
        loadDT();
        LocalDate date = Instant.ofEpochMilli(u.getNgaysinh().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate date1 = Instant.ofEpochMilli(u.getHanthe().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate futureDate = date1.plusYears(-4);
        Date t = Date.valueOf(futureDate);
        this.maDocGia.setText(String.valueOf(u.getId()));
        this.ten.setText(u.getTen());
        this.gioitinh.setText(u.getGioitinh());
        this.ngaysinh.setValue(date);
        this.cbdoituong.getSelectionModel().select(u.getUser_doituong() - 1);
        this.cbboPhan.getSelectionModel().select(u.getUser_bophan() - 1);
        this.hanthe.setText(t.toString() + " ==>> " + date1.toString());
        this.email.setText(u.getEmail());
        this.diachi.setText(u.getDiachi());
        this.sdt.setText(u.getSdt());
    }

    public void update(ActionEvent evt) throws SQLException, NoSuchAlgorithmException {
        PasswordService p = new PasswordService("email");
        PasswordService p1 = new PasswordService(1);
        User u = new User(Integer.parseInt(this.maDocGia.getText()), "", "", this.ten.getText(), this.gioitinh.getText(), us.getHanthe(), Date.valueOf(this.ngaysinh.getValue()), this.email.getText(), this.diachi.getText(), this.sdt.getText(), this.cbboPhan.getSelectionModel().getSelectedItem().getMaBP(), this.cbdoituong.getSelectionModel().getSelectedItem().getMaDT(), 1, "", "");
        if (user.checkUpdate(u)) {
            MessageBox.getBox("Thông báo", "Không được để trống ô nào!!!", Alert.AlertType.ERROR).show();
        } else {
            if (p.checkEmail(this.email.getText())) {
                if (p1.checkSdt(this.sdt.getText()) || this.sdt.getText().isEmpty()) {
                    try {
                        if (user.update(u)) {
                            MessageBox.getBox("Thông báo", "Bạn đã thêm cập nhật lại thông tin thành công!!!", Alert.AlertType.INFORMATION).show();

                        }
                    } catch (SQLException ex) {
                        MessageBox.getBox("Thông báo", "Cập nhật lại thông tin thất bại!!!", Alert.AlertType.ERROR).show();
                        Logger.getLogger(QuanLySachController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    MessageBox.getBox("Thông báo", "Số điện thoại không hợp lệ", Alert.AlertType.INFORMATION).show();
                }
            } else {
                MessageBox.getBox("Thông báo", "Email chưa đúng", Alert.AlertType.INFORMATION).show();
            }

        }
    }

    public void thoat(ActionEvent evt) throws IOException, SQLException {
        User ur = user.getU(this.us.getUsername(), this.us.getPassword());
        Stage stage = (Stage) ((Node) evt.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("User.fxml"));
        Parent manageView = loader.load();
        Scene scene = new Scene(manageView);
        UserController controller = loader.getController();
        controller.thongTin(ur);
        stage.setScene(scene);
        stage.show();
    }

    public void doiMatKhau(ActionEvent evt) throws IOException, SQLException {
        User ur = user.getU(this.us.getUsername(), this.us.getPassword());
        Stage stage = (Stage) ((Node) evt.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DoiMatKhau.fxml"));
        Parent manageView = loader.load();
        Scene scene = new Scene(manageView);
        DoiMatKhauController controller = loader.getController();
        controller.setUser(ur);
        stage.setScene(scene);
        stage.show();
    }

    public void loadBP() {
        BoPhanService b = new BoPhanService();
        try {
            this.cbboPhan.setItems(FXCollections.observableList(b.getBoPhan()));
            cbboPhan.getSelectionModel().selectFirst();
        } catch (SQLException ex) {
            Logger.getLogger(DangKyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadDT() {
        DoiTuongService d = new DoiTuongService();
        try {
            this.cbdoituong.setItems(FXCollections.observableList(d.getDoiTuong()));
            cbdoituong.getSelectionModel().selectFirst();
        } catch (SQLException ex) {
            Logger.getLogger(DangKyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

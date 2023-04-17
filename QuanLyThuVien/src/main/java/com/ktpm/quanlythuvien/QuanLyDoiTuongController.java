/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktpm.quanlythuvien;

import com.ktpm.pojo.DoiTuong;
import com.ktpm.pojo.TheLoaiSach;
import com.ktpm.pojo.User;
import com.ktpm.services.DoiTuongService;
import com.ktpm.services.TheLoaiService;
import com.ktpm.utils.MessageBox;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author THANH NHAN
 */
public class QuanLyDoiTuongController implements Initializable {

    @FXML
    TextField maDT;
    @FXML
    TextField loaiDT;
    @FXML
    TextField search;
    @FXML
    TableView<DoiTuong> tbDT;

    public static DoiTuongService dt = new DoiTuongService();
    private User us;

    public void setUser(User u) {
        this.us = u;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadTableColumns();
            loadTableData(null);
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyDanhMucController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.search.textProperty().addListener(e -> {
            try {
                this.loadTableData(this.search.getText());
            } catch (SQLException ex) {
                Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    private void loadTableColumns() {
        TableColumn colID = new TableColumn("Mã đối tượng");
        colID.setCellValueFactory(new PropertyValueFactory("maDT"));

        TableColumn colName = new TableColumn("Loại đối tượng");
        colName.setCellValueFactory(new PropertyValueFactory("loaiDT"));
        colName.setPrefWidth(250);

        this.tbDT.getColumns().addAll(colID, colName);
    }

    private void loadTableData(String kw) throws SQLException {
        List<DoiTuong> doituong = dt.getDoiTuong(kw);
        this.tbDT.getItems().clear();
        this.tbDT.setItems(FXCollections.observableList(doituong));

    }

    public void load(MouseEvent evt) {
        DoiTuong d = tbDT.getSelectionModel().getSelectedItem();
        if (d != null) {
            this.maDT.setText(String.valueOf(d.getMaDT()));
            this.loaiDT.setText(d.getLoaiDT());
        } else {
            MessageBox.getBox("Thông báo", "Bạn chưa click vào ô cần!!", Alert.AlertType.ERROR).show();

        }

    }

    public void addDoiTuong(ActionEvent evt) throws SQLException {
        if (!this.loaiDT.getText().isEmpty()) {
            DoiTuong d = new DoiTuong(this.loaiDT.getText());
            if (dt.addDoiTuong(d)) {
                clear();
                loadTableData(null);
                MessageBox.getBox("Thông báo", "Thêm thành công!!", Alert.AlertType.INFORMATION).show();
            } else {
                MessageBox.getBox("Thông báo", "Thêm không thành công do loại đối tượng đã tồn tại!!", Alert.AlertType.ERROR).show();
            }
        }
    }

    public void updateDoiTuong(ActionEvent evt) throws SQLException {
        if (!this.loaiDT.getText().isEmpty()) {
            DoiTuong d = new DoiTuong(Integer.parseInt(this.maDT.getText()), this.loaiDT.getText());
            if (dt.update(d)) {
                clear();
                loadTableData(null);
                MessageBox.getBox("Thông báo", "Sửa thành công!!", Alert.AlertType.INFORMATION).show();
            } else {
                MessageBox.getBox("Thông báo", "Sửa không thành công!!", Alert.AlertType.ERROR).show();
            }
        }
    }

    public void deleteDoiTuong(ActionEvent evt) throws SQLException {
        if (dt.delete(Integer.parseInt(this.maDT.getText()))) {
            clear();
            loadTableData(null);
            MessageBox.getBox("Thông báo", "Xóa thành công!!", Alert.AlertType.INFORMATION).show();
        } else {
            MessageBox.getBox("Thông báo", "Xóa không thành công!!", Alert.AlertType.ERROR).show();
        }
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

    public void clear() {
        this.maDT.setText("");
        this.loaiDT.setText("");
    }
}

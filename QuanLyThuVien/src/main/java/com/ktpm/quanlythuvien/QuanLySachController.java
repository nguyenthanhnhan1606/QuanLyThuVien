/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktpm.quanlythuvien;

import com.ktpm.pojo.Sach;
import com.ktpm.pojo.TheLoaiSach;
import com.ktpm.services.SachService;
import com.ktpm.services.TheLoaiService;
import com.ktpm.utils.MessageBox;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Admin
 */
public class QuanLySachController implements Initializable {

    static SachService s = new SachService();
    @FXML
    TableView<Sach> tbSach;

    @FXML
    private TextField tenSach;
    @FXML
    private TextField tacGia;
    @FXML
    private DatePicker namXB;
    @FXML
    private TextField moTa;
    @FXML
    private TextField viTri;
    @FXML
    private ComboBox<TheLoaiSach> cbTheLoaiSach;
    @FXML
    private DatePicker ngayNhap;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.loadTL();
        this.loadTableColumns();
        this.loadTableData();
    }

    private void loadTableColumns() {
        TableColumn colID = new TableColumn("Mã sách");
        colID.setCellValueFactory(new PropertyValueFactory("maSach"));

        TableColumn colName = new TableColumn("Tên sách");
        colName.setCellValueFactory(new PropertyValueFactory("tenSach"));
        colName.setPrefWidth(250);

        TableColumn colAuthor = new TableColumn("Tên tác giả");
        colAuthor.setCellValueFactory(new PropertyValueFactory("tenTacGia"));
        colAuthor.setPrefWidth(100);

        TableColumn colExport = new TableColumn("Năm XB");
        colExport.setCellValueFactory(new PropertyValueFactory("namXB"));

        TableColumn colDescription = new TableColumn("Mô tả");
        colDescription.setCellValueFactory(new PropertyValueFactory("moTa"));
        colDescription.setPrefWidth(200);

        TableColumn colPosition = new TableColumn("Vị trí");
        colPosition.setCellValueFactory(new PropertyValueFactory("viTri"));

        TableColumn colExport1 = new TableColumn("Năm nhập sách");
        colExport1.setCellValueFactory(new PropertyValueFactory("ngayNhapSach"));

        TableColumn colCate = new TableColumn("Thể loại");
        colCate.setCellValueFactory(new PropertyValueFactory("sach_tl"));
        colCate.setPrefWidth(100);

        this.tbSach.getColumns().addAll(colID, colName, colAuthor, colExport, colDescription, colPosition,colExport1, colCate);
    }

    public void loadTL() {
        TheLoaiService tl = new TheLoaiService();
        try {
            this.cbTheLoaiSach.setItems(FXCollections.observableList(tl.getTheLoai()));
            cbTheLoaiSach.getSelectionModel().selectFirst();
        } catch (SQLException ex) {
            Logger.getLogger(DangKyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public void addSachs(ActionEvent evt) throws SQLException, IOException, NoSuchAlgorithmException {
//
//        Date namXB = Date.valueOf(this.namXB.getValue());
//
//        Sach s = new Sach(this.tenSach.getText(), this.tacGia.getText(), namXB, this.moTa.getText(), this.viTri.getText(), this.cbTheLoaiSach.getSelectionModel().getSelectedItem().getMaTLS());
//        SachService sach = new SachService();
//        try {
//            if (sach.addSach(s)) {
//                clearForm();
//                MessageBox.getBox("Thông báo", "Bạn đã thêm sách thành công!!!", Alert.AlertType.INFORMATION).show();
//
//            }
//        } catch (SQLException ex) {
//            MessageBox.getBox("Thông báo", "Thêm sách thất bại!!!", Alert.AlertType.ERROR).show();
//            Logger.getLogger(QuanLySachController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    private void loadTableData() {
        try {
            this.tbSach.setItems(FXCollections.observableList(s.getSachs()));
        } catch (SQLException ex) {
            Logger.getLogger(QuanLySachController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void clearForm() {
        this.tenSach.clear();
        this.tacGia.clear();
        this.namXB.setValue(null);
        cbTheLoaiSach.getSelectionModel().selectFirst();
        this.moTa.clear();
        this.viTri.clear();
    }

}

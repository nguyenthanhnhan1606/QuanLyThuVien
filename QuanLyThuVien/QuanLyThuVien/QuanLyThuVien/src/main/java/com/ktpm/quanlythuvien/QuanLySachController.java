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
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Admin
 */
public class QuanLySachController implements Initializable {

    static SachService s = new SachService();
    @FXML
    TableView<Sach> tbSach;
    @FXML
    private TextField maSach;
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

    @FXML
    private TextField search;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            
            this.namXB.setValue(LocalDate.now());
            this.ngayNhap.setValue(LocalDate.now());
            this.loadTL();
            this.loadTableColumns();
            this.loadTableData(null);
        } catch (SQLException ex) {
            Logger.getLogger(QuanLySachController.class.getName()).log(Level.SEVERE, null, ex);
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

        this.tbSach.getColumns().addAll(colID, colName, colAuthor, colExport, colDescription, colPosition, colExport1, colCate);
    }

    public void delete(ActionEvent evt) throws SQLException {
        Sach sa = tbSach.getSelectionModel().getSelectedItem();
        Alert a = MessageBox.getBox("Question",
                "Are you sure to delete this question?",
                Alert.AlertType.CONFIRMATION);
        a.showAndWait().ifPresent(res -> {
            if (res == ButtonType.OK) {
                try {
                    if (s.deleteSach(sa.getMaSach())) {
                        clearForm();
                        MessageBox.getBox("Question", "Delete successful", Alert.AlertType.INFORMATION).show();
                        this.loadTableData(null);
                    } else {
                        MessageBox.getBox("Question", "Delete failed", Alert.AlertType.WARNING).show();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(QuanLySachController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public void update(ActionEvent evt) throws SQLException {

        Date namXB1 = Date.valueOf(this.namXB.getValue());
        Date ngayNhap1 = Date.valueOf(this.ngayNhap.getValue());
        Sach s = new Sach(Integer.parseInt(this.maSach.getText()), this.tenSach.getText(), this.tacGia.getText(), namXB1, this.moTa.getText(), this.viTri.getText(), ngayNhap1, this.cbTheLoaiSach.getSelectionModel().getSelectedItem().getMaTLS());
        SachService sach = new SachService();
        try {
            if (sach.update(s)) {
                loadTableData(null);
                clearForm();
                MessageBox.getBox("Thông báo", "Bạn đã thêm cập nhật lại sách thành công!!!", Alert.AlertType.INFORMATION).show();

            }
        } catch (SQLException ex) {
            MessageBox.getBox("Thông báo", "Cập nhật lại sách thất bại!!!", Alert.AlertType.ERROR).show();
            Logger.getLogger(QuanLySachController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void Load(MouseEvent evt) {
        Sach sa = tbSach.getSelectionModel().getSelectedItem();
        LocalDate date= Instant.ofEpochMilli(sa.getNamXB().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate date1= Instant.ofEpochMilli(sa.getNgayNhapSach().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        this.maSach.setText(String.valueOf(sa.getMaSach()));
        this.tenSach.setText(sa.getTenSach());
        this.tacGia.setText(sa.getTenTacGia());
        this.namXB.setValue(date);
        this.moTa.setText(sa.getMoTa());
        this.viTri.setText(sa.getViTri());
        this.cbTheLoaiSach.getSelectionModel().select(sa.getSach_tl() - 1);
        this.ngayNhap.setValue(date1);

    }

    public void loadTL() {
        TheLoaiService tl = new TheLoaiService();
        try {
            this.cbTheLoaiSach.setItems(FXCollections.observableList(tl.getTheLoai()));
            cbTheLoaiSach.getSelectionModel().selectFirst();
        } catch (SQLException ex) {
            Logger.getLogger(DangKyController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.search.promptTextProperty().addListener(e -> {
            try {
                this.loadTableData(this.search.getText());
            } catch (SQLException ex) {
                Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public void addSachs(ActionEvent evt) throws SQLException, IOException, NoSuchAlgorithmException {
        Date namXB1 = Date.valueOf(this.namXB.getValue());
        Date ngayNhap1 = Date.valueOf(this.ngayNhap.getValue());
        if (this.tacGia.getText().isEmpty() || this.moTa.getText().isEmpty() || this.tenSach.getText().isEmpty() || this.viTri.getText().isEmpty()) {
            MessageBox.getBox("Lỗi", "Không được để trống ô nào!!", Alert.AlertType.ERROR).show();
        } else {
            Sach sa = new Sach(this.tenSach.getText(), this.tacGia.getText(), namXB1, this.moTa.getText(), this.viTri.getText(), ngayNhap1, this.cbTheLoaiSach.getSelectionModel().getSelectedItem().getMaTLS());
            SachService sach = new SachService();
            try {
                if (sach.addSach(sa)) {
                    loadTableData(null);
                    clearForm();
                    MessageBox.getBox("Thông báo", "Bạn đã thêm sách thành công!!!", Alert.AlertType.INFORMATION).show();

                }
            } catch (SQLException ex) {
                MessageBox.getBox("Thông báo", "Thêm sách thất bại!!!", Alert.AlertType.ERROR).show();
                Logger.getLogger(QuanLySachController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void loadTableData(String kw) throws SQLException {
        List<Sach> sa = s.getSachs(kw);
        this.tbSach.getItems().clear();
        this.tbSach.setItems(FXCollections.observableList(sa));

    }

    public void thoatQLS(ActionEvent evt){
        Alert a = MessageBox.getBox("Thông báo",
                "Bạn có muốn quay lại trang chủ không?",
                Alert.AlertType.CONFIRMATION);
        a.showAndWait().ifPresent(res -> {
            if (res == ButtonType.OK) {
                try {
                    App.setRoot("Admin");
                } catch (IOException ex) {
                    Logger.getLogger(QuanLySachController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    public void clearForm() {
        this.maSach.clear();
        this.tenSach.clear();
        this.tacGia.clear();
        this.namXB.setValue(null);
        cbTheLoaiSach.getSelectionModel().selectFirst();
        this.moTa.clear();
        this.viTri.clear();
    }

}

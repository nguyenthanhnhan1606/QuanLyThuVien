/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktpm.quanlythuvien;

import com.ktpm.pojo.ChiTietPM;
import com.ktpm.pojo.PhieuMuonSach;
import com.ktpm.pojo.Sach;
import com.ktpm.pojo.TheLoaiSach;
import com.ktpm.pojo.User;
import com.ktpm.pojo.data;
import com.ktpm.services.ChiTietPmService;
import com.ktpm.services.PhieuMuonService;
import com.ktpm.services.SachService;
import com.ktpm.services.TheLoaiService;
import com.ktpm.utils.MessageBox;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
public class UserCtpmController implements Initializable {

    private User us;
    static SachService s = new SachService();
    static PhieuMuonService pm = new PhieuMuonService();
    static ChiTietPmService ct = new ChiTietPmService();

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

    public void setUser(User u) {
        this.us = u;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadTL();
            loadTableColumns();
            loadTableData();
        } catch (SQLException ex) {
            Logger.getLogger(UserCtpmController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        colCate.setCellValueFactory(new PropertyValueFactory("tenTl"));
        colCate.setPrefWidth(100);

       
        this.tbSach.getColumns().addAll(colID, colName, colAuthor, colExport, colDescription, colPosition, colExport1, colCate);
    }

    private void loadTableData() {
        this.tbSach.setItems(FXCollections.observableList(data.sa));
    }

    public void loadTL() throws SQLException {
        TheLoaiService tl = new TheLoaiService();
        this.cbTheLoaiSach.setItems(FXCollections.observableList(tl.getTheLoai()));
    }

    public void Load(MouseEvent evt) {
        Sach sa = tbSach.getSelectionModel().getSelectedItem();
        LocalDate date = Instant.ofEpochMilli(sa.getNamXB().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate date1 = Instant.ofEpochMilli(sa.getNgayNhapSach().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        this.maSach.setText(String.valueOf(sa.getMaSach()));
        this.tenSach.setText(sa.getTenSach());
        this.tacGia.setText(sa.getTenTacGia());
        this.namXB.setValue(date);
        this.moTa.setText(sa.getMoTa());
        this.viTri.setText(sa.getViTri());
        this.cbTheLoaiSach.getSelectionModel().select(sa.getSach_tl() - 1);
        this.ngayNhap.setValue(date1);
    }

    public void delete(ActionEvent evt) throws SQLException {
        Sach sa = tbSach.getSelectionModel().getSelectedItem();
        if (sa != null) {
            Alert a = MessageBox.getBox("Question",
                    "Are you sure to delete this question?",
                    Alert.AlertType.CONFIRMATION);
            a.showAndWait().ifPresent(res -> {
                if (res == ButtonType.OK) {
                    if (data.sa.remove(sa)) {
                        MessageBox.getBox("Question", "Delete successful", Alert.AlertType.INFORMATION).show();
                        this.loadTableData();
                    } else {
                        MessageBox.getBox("Question", "Delete failed", Alert.AlertType.WARNING).show();
                    }
                }
            });
        } else {
            MessageBox.getBox("Question", "Bạn chưa chọn sách cần xóa", Alert.AlertType.WARNING).show();
        }

    }

    public void datMuon(ActionEvent evt) throws SQLException {
        if (data.sa.size() >= 1) {
            Date date = Date.valueOf(LocalDate.now());
            LocalDate localDate = LocalDate.now();
            int nam = localDate.getYear();
            int thang = localDate.getMonthValue();
            int ngay = localDate.getDayOfMonth();
            LocalDate futureDate = LocalDate.now().plusMonths(1);
            Date date1 = Date.valueOf(futureDate);
            PhieuMuonSach pms = new PhieuMuonSach(date, date1, us.getId(), data.sa.size(), "Chờ lấy sách");
            if (pm.addPhieuMuon(pms)) {
                PhieuMuonSach phieu = pm.getPM(nam, thang, ngay, us.getId());
                for (int i = 0; i < data.sa.size(); i++) {
                    ChiTietPM ctpm = new ChiTietPM(data.sa.get(i).getMaSach(), phieu.getId());
                    if (ct.addChiTiet(ctpm)) {
                        s.updateTT(data.sa.get(i).getMaSach());
                    } else {
                        MessageBox.getBox("Thông báo", "đặt không thành công", Alert.AlertType.WARNING).show();
                    }

                }
                data.sa.clear();
                loadTableData();
                MessageBox.getBox("Thông báo", "Đặt thành công!!Bạn hãy lấy sách trong vòng 48h", Alert.AlertType.INFORMATION).show();
            } else {
                MessageBox.getBox("Thông báo", "Đặt không thành công", Alert.AlertType.WARNING).show();
            }
        } else {
            MessageBox.getBox("Thông báo", "Đặt không thành công. Bạn chưa chọn sách để mượn", Alert.AlertType.WARNING).show();
        }

    }

    public void Thoat(ActionEvent evt) throws IOException, SQLException {
        Stage stage = (Stage) ((Node) evt.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserMuonSach.fxml"));
        Parent manageView = loader.load();
        Scene scene = new Scene(manageView);
        UserMuonSachController controller = loader.getController();
        controller.setUser(us);
        stage.setScene(scene);
        stage.show();
    }
}

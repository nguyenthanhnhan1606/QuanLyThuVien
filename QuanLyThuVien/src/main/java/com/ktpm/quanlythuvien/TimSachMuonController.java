/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktpm.quanlythuvien;

import com.ktpm.pojo.Sach;
import com.ktpm.pojo.TheLoaiSach;
import com.ktpm.pojo.User;
import com.ktpm.pojo.data;
import com.ktpm.services.PhieuMuonService;
import com.ktpm.services.SachService;
import com.ktpm.services.TheLoaiService;
import com.ktpm.services.UserService;
import com.ktpm.utils.MessageBox;
import java.io.IOException;
import java.net.URL;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
public class TimSachMuonController implements Initializable {

    static UserService user = new UserService();
    static SachService s = new SachService();
    static PhieuMuonService pm = new PhieuMuonService();
    static data d = new data();
    private User us;

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

    public void setUser(User u) {
        this.us = u;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadTL();
            this.loadTableColumns();
            this.loadTableData(null);
        } catch (SQLException ex) {
            Logger.getLogger(UserMuonSachController.class.getName()).log(Level.SEVERE, null, ex);
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

    private void loadTableData(String kw) throws SQLException {
        List<Sach> sa = s.getSachs(kw);
        this.tbSach.getItems().clear();
        this.tbSach.setItems(FXCollections.observableList(sa));

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

    public void themVaoPM(ActionEvent evt) throws SQLException {
        long t = System.currentTimeMillis();
        long t1 = us.getHanthe().getTime();
        if (t1 - t >= 0) {
            if (pm.kiemTraMuon(us.getId())) {
                Sach sac = new Sach(Integer.parseInt(this.maSach.getText()),
                        this.tenSach.getText(),
                        this.tacGia.getText(),
                        Date.valueOf(this.namXB.getValue()),
                        this.moTa.getText(),
                        this.viTri.getText(),
                        Date.valueOf(this.ngayNhap.getValue()),
                        this.cbTheLoaiSach.getSelectionModel().getSelectedItem().getMaTLS(),
                        "Chưa đặt");
                if (d.kts1(sac)) {
                    if (data.sa1.size() < 5) {
                        if (data.sa1.add(sac)) {
                            MessageBox.getBox("Thông báo", "Bạn đã thêm sách thành công!!!", Alert.AlertType.INFORMATION).show();
                        } else {
                            MessageBox.getBox("Thông báo", "không thành công!!!", Alert.AlertType.ERROR).show();
                        }
                    } else {
                        MessageBox.getBox("Thông báo", "Sách mượn đã đạt tối đa", Alert.AlertType.ERROR).show();
                    }

                } else {
                    MessageBox.getBox("Thông báo", "Sách này đã được thêm!!!", Alert.AlertType.ERROR).show();

                }

            } else {
                MessageBox.getBox("Thông báo", "Bạn phải trả sách mới tiếp tục đặt!!!", Alert.AlertType.ERROR).show();
            }

        } else {
            MessageBox.getBox("Thông báo", "Thẻ bạn đã hết hạn!!!", Alert.AlertType.INFORMATION).show();
        }

    }

    public void thoat(ActionEvent evt) throws IOException {
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



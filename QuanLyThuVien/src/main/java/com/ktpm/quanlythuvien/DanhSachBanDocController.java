/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktpm.quanlythuvien;

import com.ktpm.pojo.BoPhan;
import com.ktpm.pojo.DoiTuong;
import com.ktpm.pojo.Sach;
import com.ktpm.pojo.TheLoaiSach;
import com.ktpm.pojo.User;
import com.ktpm.services.BoPhanService;
import com.ktpm.services.DoiTuongService;
import com.ktpm.services.SachService;
import com.ktpm.services.UserService;
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
 * @author Admin
 */
public class DanhSachBanDocController implements Initializable {

    private User us;

    public void setUser(User u) {
        this.us = u;
    }
    static UserService user = new UserService();

    @FXML
    TableView<User> tbUser;
    @FXML
    private TextField maDocGia;
    @FXML
    private TextField ten;
    @FXML
    private TextField gioiTinh;
    @FXML
    private DatePicker hanthe;
    @FXML
    private DatePicker ngaysinh;
    @FXML
    private TextField email;
    @FXML
    private TextField diachi;
    @FXML
    private TextField sdt;
    @FXML
    private ComboBox<BoPhan> cbBoPhan;
    @FXML
    private ComboBox<DoiTuong> cbDoiTuong;
    @FXML
    private TextField search;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            loadBP();
            loadDT();
            loadTableColumns();
            loadTableData();
        } catch (SQLException ex) {
            Logger.getLogger(DanhSachBanDocController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadTableColumns() {
        TableColumn colID = new TableColumn("Mã Đọc giả");
        colID.setCellValueFactory(new PropertyValueFactory("id"));

        TableColumn colName = new TableColumn("Họ và tên");
        colName.setCellValueFactory(new PropertyValueFactory("ten"));
        colName.setPrefWidth(200);

        TableColumn colGt = new TableColumn("Giới tính");
        colGt.setCellValueFactory(new PropertyValueFactory("gioitinh"));
        colGt.setPrefWidth(100);

        TableColumn colp = new TableColumn("Ngày sinh");
        colp.setCellValueFactory(new PropertyValueFactory("ngaysinh"));

        TableColumn colDt = new TableColumn("Đối tượng");
        colDt.setCellValueFactory(new PropertyValueFactory("user_doituong"));
        colDt.setPrefWidth(200);

        TableColumn colBp = new TableColumn("Bộ phận");
        colBp.setCellValueFactory(new PropertyValueFactory("user_bophan"));

        TableColumn colExport1 = new TableColumn("Hạn thẻ");
        colExport1.setCellValueFactory(new PropertyValueFactory("hanthe"));

        TableColumn col1 = new TableColumn("Email");
        col1.setCellValueFactory(new PropertyValueFactory("email"));
        col1.setPrefWidth(100);

        TableColumn col2 = new TableColumn("Địa chỉ");
        col2.setCellValueFactory(new PropertyValueFactory("diachi"));
        col2.setPrefWidth(100);

        TableColumn col3 = new TableColumn("sdt");
        col3.setCellValueFactory(new PropertyValueFactory("sdt"));
        col3.setPrefWidth(100);

        this.tbUser.getColumns().addAll(colID, colName, colGt, colp, colDt, colBp, colExport1, col1, col2, col3);
    }

    private void loadTableData() throws SQLException {
        List<User> u = user.getUser();
        this.tbUser.setItems(FXCollections.observableList(u));

    }

    public void loadBP() {
        BoPhanService b = new BoPhanService();
        try {
            this.cbBoPhan.setItems(FXCollections.observableList(b.getBoPhan()));
            cbBoPhan.getSelectionModel().selectFirst();
        } catch (SQLException ex) {
            Logger.getLogger(DangKyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadDT() {
        DoiTuongService d = new DoiTuongService();
        try {
            this.cbDoiTuong.setItems(FXCollections.observableList(d.getDoiTuong()));
            cbDoiTuong.getSelectionModel().selectFirst();
        } catch (SQLException ex) {
            Logger.getLogger(DangKyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Load(MouseEvent evt) {
        User us = tbUser.getSelectionModel().getSelectedItem();
        LocalDate date = Instant.ofEpochMilli(us.getNgaysinh().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate date1 = Instant.ofEpochMilli(us.getHanthe().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        this.maDocGia.setText(String.valueOf(us.getId()));
        this.ten.setText(us.getTen());
        this.gioiTinh.setText(us.getGioitinh());
        this.ngaysinh.setValue(date);
        this.cbDoiTuong.getSelectionModel().select(us.getUser_doituong() - 1);
        this.cbBoPhan.getSelectionModel().select(us.getUser_bophan() - 1);
        this.hanthe.setValue(date1);
        this.email.setText(us.getEmail());
        this.diachi.setText(us.getDiachi());
        this.sdt.setText(us.getSdt());
    }

    public void update(ActionEvent evt) throws SQLException, NoSuchAlgorithmException {

        Date date1 = Date.valueOf(this.ngaysinh.getValue());
        Date date2 = Date.valueOf(this.hanthe.getValue());
        User u = new User(Integer.parseInt(this.maDocGia.getText()), "", "", this.ten.getText(), this.gioiTinh.getText(), date2, date1, this.email.getText(), this.diachi.getText(), this.sdt.getText(), this.cbBoPhan.getSelectionModel().getSelectedItem().getMaBP(), this.cbDoiTuong.getSelectionModel().getSelectedItem().getMaDT(), 1);
        if (user.checkUpdate(u)) {
            MessageBox.getBox("Thông báo", "Không được để trống ô nào!!!", Alert.AlertType.ERROR).show();
        } else {
            try {
                if (user.update(u)) {
                    loadTableData();
                    MessageBox.getBox("Thông báo", "Bạn đã thêm cập nhật lại sách thành công!!!", Alert.AlertType.INFORMATION).show();

                }
            } catch (SQLException ex) {
                MessageBox.getBox("Thông báo", "Cập nhật lại sách thất bại!!!", Alert.AlertType.ERROR).show();
                Logger.getLogger(QuanLySachController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void delete(ActionEvent evt) throws SQLException {
        User u = tbUser.getSelectionModel().getSelectedItem();
        Alert a = MessageBox.getBox("Question",
                "Are you sure to delete this question?",
                Alert.AlertType.CONFIRMATION);
        a.showAndWait().ifPresent(res -> {
            if (res == ButtonType.OK) {
                try {
                    if (user.deleteUser(u.getId())) {
                        MessageBox.getBox("Question", "Delete successful", Alert.AlertType.INFORMATION).show();
                        this.loadTableData();
                    } else {
                        MessageBox.getBox("Question", "Delete failed", Alert.AlertType.WARNING).show();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(QuanLySachController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public void thoatQLDG(ActionEvent evt) throws SQLException, IOException {
        User ur = user.getU(this.us.getUsername(), this.us.getPassword());
        Stage stage = (Stage) ((Node) evt.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin.fxml"));
        Parent manageView = loader.load();
        Scene scene = new Scene(manageView);
        AdminController controller = loader.getController();
        controller.setUser(ur);
        stage.setScene(scene);
        stage.show();

    }

    public void DangKy(ActionEvent evt) throws IOException {

        Stage stage = (Stage) ((Node) evt.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DangKy.fxml"));
        Parent manageView = loader.load();
        Scene scene = new Scene(manageView);
        stage.setScene(scene);
        stage.show();

    }
}

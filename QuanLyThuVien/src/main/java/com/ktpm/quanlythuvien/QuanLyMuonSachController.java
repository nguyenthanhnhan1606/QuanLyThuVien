/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktpm.quanlythuvien;

import com.ktpm.pojo.ChiTietPM;
import com.ktpm.pojo.PhieuMuonSach;
import com.ktpm.pojo.Sach;
import com.ktpm.pojo.User;
import com.ktpm.pojo.data;
import static com.ktpm.quanlythuvien.UserMuonSachController.user;
import com.ktpm.services.ChiTietPmService;
import com.ktpm.services.PhieuMuonService;
import com.ktpm.services.SachService;
import com.ktpm.utils.MessageBox;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author THANH NHAN
 */
public class QuanLyMuonSachController implements Initializable {

    private User us;
    public static SachService s = new SachService();
    static PhieuMuonService pm = new PhieuMuonService();
    static ChiTietPmService ct = new ChiTietPmService();

    @FXML
    TableView<Sach> tbSach;
    @FXML
    TextField maDG;
    @FXML
    DatePicker ngaymuon;
    @FXML
    DatePicker hantra;

    public void setUser(User u) {
        this.us = u;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            setData();
            loadTableColumns();
            loadTableData();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyMuonSachController.class.getName()).log(Level.SEVERE, null, ex);
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
        colCate.setCellValueFactory(new PropertyValueFactory("sach_tl"));
        colCate.setPrefWidth(100);

        this.tbSach.getColumns().addAll(colID, colName, colAuthor, colExport, colDescription, colPosition, colExport1, colCate);
    }

    private void loadTableData() throws SQLException {
        this.tbSach.setItems(FXCollections.observableList(data.sa1));
    }

    public void setData() {
        Date date = Date.valueOf(LocalDate.now());
        LocalDate futureDate = LocalDate.now().plusMonths(1);
        Date date1 = Date.valueOf(futureDate);
        this.ngaymuon.setValue(LocalDate.parse(date.toString()));
        this.hantra.setValue(LocalDate.parse(date1.toString()));
    }

    public void datMuon(ActionEvent evt) throws SQLException {
        if (!this.maDG.getText().isEmpty()) {
            if (data.sa1.size() >= 1) {
                Date date =Date.valueOf(this.ngaymuon.getValue());
                LocalDate localDate = this.ngaymuon.getValue();
                int nam = localDate.getYear();
                int thang = localDate.getMonthValue();
                int ngay = localDate.getDayOfMonth();
                PhieuMuonSach pms = new PhieuMuonSach(date, Date.valueOf(this.hantra.getValue()), Integer.parseInt(this.maDG.getText().trim()), data.sa1.size(), "Đang mượn sách");
                if (pm.addPhieuMuon(pms)) {
                    PhieuMuonSach phieu = pm.getPMTT(nam, thang, ngay, Integer.parseInt(this.maDG.getText().trim()));
                    for (int i = 0; i < data.sa1.size(); i++) {
                        ChiTietPM ctpm = new ChiTietPM(data.sa1.get(i).getMaSach(), phieu.getId());
                        if (ct.addChiTiet(ctpm)) {
                            s.updateTT(data.sa1.get(i).getMaSach());
                        } else {
                            MessageBox.getBox("Thông báo", "Không thành công", Alert.AlertType.WARNING).show();
                        }
                    }
                    data.sa1.clear();
                    loadTableData();
                    MessageBox.getBox("Thông báo", "Thành công!!", Alert.AlertType.INFORMATION).show();
                } else {
                    MessageBox.getBox("Thông báo", "Không thành công", Alert.AlertType.WARNING).show();
                }

            } else {
                MessageBox.getBox("Thông báo", "Danh sách mượn đang trống", Alert.AlertType.WARNING).show();

            }
        } else {
            MessageBox.getBox("Thông báo", "Không được để trống ô mã đọc giả", Alert.AlertType.WARNING).show();

        }

    }

    public void xoa(ActionEvent evt) throws IOException, SQLException {
        Sach sa = tbSach.getSelectionModel().getSelectedItem();
        data.sa1.remove(sa);
        loadTableData();
    }

    public void timSach(ActionEvent evt) throws IOException, SQLException {
        User ur = user.getU(this.us.getUsername(), this.us.getPassword());
        Stage stage = (Stage) ((Node) evt.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TimSachMuon.fxml"));
        Parent manageView = loader.load();
        Scene scene = new Scene(manageView);
        TimSachMuonController controller = loader.getController();
        controller.setUser(ur);
        stage.setScene(scene);
        stage.show();
    }

    public void thoat(ActionEvent evt) throws IOException, SQLException {
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
}

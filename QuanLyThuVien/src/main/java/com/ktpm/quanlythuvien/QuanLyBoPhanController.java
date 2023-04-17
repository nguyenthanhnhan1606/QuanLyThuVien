/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktpm.quanlythuvien;

import com.ktpm.pojo.BoPhan;
import com.ktpm.pojo.User;
import com.ktpm.services.BoPhanService;
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
public class QuanLyBoPhanController implements Initializable {

    @FXML
    TextField maBP;
    @FXML
    TextField tenBP;
    @FXML
    TextField search;
    @FXML
    TableView<BoPhan> tbBp;

    public static BoPhanService bp = new BoPhanService();
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
        TableColumn colID = new TableColumn("Mã bộ phận");
        colID.setCellValueFactory(new PropertyValueFactory("maBP"));

        TableColumn colName = new TableColumn("Tên bộ phận");
        colName.setCellValueFactory(new PropertyValueFactory("tenBP"));
        colName.setPrefWidth(250);

        this.tbBp.getColumns().addAll(colID, colName);
    }

    private void loadTableData(String kw) throws SQLException {
        List<BoPhan> bophan = bp.getBoPhan(kw);
        this.tbBp.getItems().clear();
        this.tbBp.setItems(FXCollections.observableList(bophan));

    }

    public void load(MouseEvent evt) {
        BoPhan b = tbBp.getSelectionModel().getSelectedItem();
        if (b != null) {
            this.maBP.setText(String.valueOf(b.getMaBP()));
            this.tenBP.setText(b.getTenBP());
        } else {
            MessageBox.getBox("Thông báo", "Bạn chưa click vào ô cần!!", Alert.AlertType.ERROR).show();

        }

    }

    public void addBoPhan(ActionEvent evt) throws SQLException {
        if (!this.tenBP.getText().isEmpty()) {
            BoPhan b = new BoPhan(this.tenBP.getText());
            if (bp.addBoPhan(b)) {
                clear();
                loadTableData(null);
                MessageBox.getBox("Thông báo", "Thêm thành công!!", Alert.AlertType.INFORMATION).show();
            } else {
                MessageBox.getBox("Thông báo", "Thêm không thành công!!", Alert.AlertType.ERROR).show();
            }
        }
    }

    public void updateBoPhan(ActionEvent evt) throws SQLException {
        if (!this.tenBP.getText().isEmpty()) {
            BoPhan b = new BoPhan(Integer.parseInt(this.maBP.getText()), this.tenBP.getText());
            if (bp.update(b)) {
                clear();
                loadTableData(null);
                MessageBox.getBox("Thông báo", "Sửa thành công!!", Alert.AlertType.INFORMATION).show();
            } else {
                MessageBox.getBox("Thông báo", "Sửa không thành công!!", Alert.AlertType.ERROR).show();
            }
        }
    }

    public void deleteBoPhan(ActionEvent evt) throws SQLException {
        if (bp.delete(Integer.parseInt(this.maBP.getText()))) {
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
        this.maBP.setText("");
        this.tenBP.setText("");
    }
}
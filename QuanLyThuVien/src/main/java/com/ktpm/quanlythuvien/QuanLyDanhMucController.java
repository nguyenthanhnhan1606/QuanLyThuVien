/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktpm.quanlythuvien;

import com.ktpm.pojo.TheLoaiSach;
import com.ktpm.pojo.User;
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
public class QuanLyDanhMucController implements Initializable {

    @FXML
    TextField maDM;
    @FXML
    TextField tenDM;
    @FXML
    TextField search;
    @FXML
    TableView<TheLoaiSach> tbDM;

    public static TheLoaiService tl = new TheLoaiService();
    private User us;
    
    public void setUser(User u){
        this.us=u;
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
        TableColumn colID = new TableColumn("Mã danh mục");
        colID.setCellValueFactory(new PropertyValueFactory("maTLS"));

        TableColumn colName = new TableColumn("Tên danh mục");
        colName.setCellValueFactory(new PropertyValueFactory("tenTL"));
        colName.setPrefWidth(250);

        this.tbDM.getColumns().addAll(colID, colName);
    }

    private void loadTableData(String kw) throws SQLException {
        List<TheLoaiSach> tls = tl.getTheLoai(kw);
        this.tbDM.getItems().clear();
        this.tbDM.setItems(FXCollections.observableList(tls));

    }

    public void load(MouseEvent evt) {
        TheLoaiSach tl = tbDM.getSelectionModel().getSelectedItem();
        if (tl != null) {
            this.maDM.setText(String.valueOf(tl.getMaTLS()));
            this.tenDM.setText(tl.getTenTL());
        } else {
            MessageBox.getBox("Thông báo", "Bạn chưa click vào ô cần!!", Alert.AlertType.ERROR).show();

        }

    }

    public void addDanhMuc(ActionEvent evt) throws SQLException {
        if (!this.tenDM.getText().isEmpty()) {
            TheLoaiSach tls = new TheLoaiSach(this.tenDM.getText());
            if (tl.addTheLoaiSach(tls)) {
                clear();
                loadTableData(null);
                MessageBox.getBox("Thông báo", "Thêm thành công!!", Alert.AlertType.INFORMATION).show();
            } else {
                MessageBox.getBox("Thông báo", "Thêm không thành công!!", Alert.AlertType.ERROR).show();
            }
        }
    }

    public void updateDanhMuc(ActionEvent evt) throws SQLException {
        if (!this.tenDM.getText().isEmpty()) {
            TheLoaiSach tls = new TheLoaiSach(Integer.parseInt(this.maDM.getText()), this.tenDM.getText());
            if (tl.update(tls)) {
                clear();
                loadTableData(null);
                MessageBox.getBox("Thông báo", "Sửa thành công!!", Alert.AlertType.INFORMATION).show();
            } else {
                MessageBox.getBox("Thông báo", "Sửa không thành công!!", Alert.AlertType.ERROR).show();
            }
        }
    }

    public void deleteDanhMuc(ActionEvent evt) throws SQLException {
        if (tl.delete(Integer.parseInt(this.maDM.getText()))) {
            clear();
            loadTableData(null);
            MessageBox.getBox("Thông báo", "Xóa thành công!!", Alert.AlertType.INFORMATION).show();
        } else {
            MessageBox.getBox("Thông báo", "Xóa không thành công!!", Alert.AlertType.ERROR).show();
        }
    }

     public void quanLySach(ActionEvent evt) throws IOException {
        Stage stage = (Stage) ((Node) evt.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("QuanLySach.fxml"));
        Parent manageView = loader.load();
        Scene scene = new Scene(manageView);
        QuanLySachController controller = loader.getController();
        controller.setUser(us);
        stage.setScene(scene);
        stage.show();
    }
     
    public void clear() {
        this.maDM.setText("");
        this.tenDM.setText("");
    }
}

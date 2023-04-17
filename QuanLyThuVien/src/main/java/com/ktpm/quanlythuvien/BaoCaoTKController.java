/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktpm.quanlythuvien;

import com.ktpm.pojo.PhieuMuonSach;
import com.ktpm.pojo.User;
import static com.ktpm.quanlythuvien.UserMuonSachController.user;
import com.ktpm.services.PhieuMuonService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

/**
 *
 * @author THANH NHAN
 */
public class BaoCaoTKController implements Initializable {

    private static PhieuMuonService pm = new PhieuMuonService();

    @FXML
    private BarChart barBC;
    @FXML
    private DatePicker namTK;
    private User us;

    public void setUser(User u) {
        this.us = u;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadChart(2023);
    }

    public void setYear(ActionEvent evt) {
        loadChart(this.namTK.getValue().getYear());
    }

    public void loadChart(int n) {
        barBC.getData().clear();
        XYChart.Series series = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();

        series.setName("Mượn");
        series2.setName("Trả");
        series.getData().add(new XYChart.Data("1", sl1q1(n)));
        series2.getData().add(new XYChart.Data("1", sl11q1(n)));

        series.getData().add(new XYChart.Data("2", sl2q2(n)));
        series2.getData().add(new XYChart.Data("2", sl22q2(n)));

        series.getData().add(new XYChart.Data("3", sl3q3(n)));
        series2.getData().add(new XYChart.Data("3", sl33q3(n)));

        series.getData().add(new XYChart.Data("4", sl4q4(n)));
        series2.getData().add(new XYChart.Data("4", sl44q4(n)));

        barBC.getData().addAll(series, series2);
    }

    public int sl1q1(int n) {
        int sl1 = 0;
        try {
            List<PhieuMuonSach> q1 = pm.getPhieuMuonSachQ1(n);
            for (PhieuMuonSach pms : q1) {
                sl1 += pms.getSoluong();
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaoCaoTKController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sl1;
    }

    public int sl11q1(int n) {
        int sl11 = 0;
        try {
            List<PhieuMuonSach> q1 = pm.getPhieuMuonSachQ1(n);
            for (PhieuMuonSach pms : q1) {

                if (pms.getTrangthai().equals("Đã trả")) {
                    sl11 += pms.getSoluong();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaoCaoTKController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sl11;
    }

    public int sl2q2(int n) {
        int sl1 = 0;
        try {
            List<PhieuMuonSach> q1 = pm.getPhieuMuonSachQ2(n);
            for (PhieuMuonSach pms : q1) {
                sl1 += pms.getSoluong();
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaoCaoTKController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sl1;
    }

    public int sl22q2(int n) {
        int sl11 = 0;
        try {
            List<PhieuMuonSach> q1 = pm.getPhieuMuonSachQ2(n);
            for (PhieuMuonSach pms : q1) {

                if (pms.getTrangthai().equals("Đã trả")) {
                    sl11 += pms.getSoluong();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaoCaoTKController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sl11;
    }

    public int sl3q3(int n) {
        int sl1 = 0;
        try {
            List<PhieuMuonSach> q1 = pm.getPhieuMuonSachQ3(n);
            for (PhieuMuonSach pms : q1) {
                sl1 += pms.getSoluong();
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaoCaoTKController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sl1;
    }

    public int sl33q3(int n) {
        int sl11 = 0;
        try {
            List<PhieuMuonSach> q1 = pm.getPhieuMuonSachQ3(n);
            for (PhieuMuonSach pms : q1) {

                if (pms.getTrangthai().equals("Đã trả")) {
                    sl11 += pms.getSoluong();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaoCaoTKController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sl11;
    }

    public int sl4q4(int n) {
        int sl1 = 0;
        try {
            List<PhieuMuonSach> q1 = pm.getPhieuMuonSachQ4(n);
            for (PhieuMuonSach pms : q1) {
                sl1 += pms.getSoluong();
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaoCaoTKController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sl1;
    }

    public int sl44q4(int n) {
        int sl11 = 0;
        try {
            List<PhieuMuonSach> q1 = pm.getPhieuMuonSachQ4(n);
            for (PhieuMuonSach pms : q1) {

                if (pms.getTrangthai().equals("Đã trả")) {
                    sl11 += pms.getSoluong();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaoCaoTKController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sl11;
    }
    public void thoat(ActionEvent evt) throws IOException, SQLException {
        User ur = user.getAD(this.us.getUsername(), this.us.getPassword());
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktpm.quanlythuvien;

import com.ktpm.pojo.PhieuMuonSach;
import com.ktpm.services.PhieuMuonService;
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
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;

/**
 *
 * @author THANH NHAN
 */
public class BaoCaoTKController implements Initializable {

    private static PhieuMuonService pm = new PhieuMuonService();
    XYChart.Series series = new XYChart.Series();
    XYChart.Series series2 = new XYChart.Series();

    @FXML
    private BarChart barBC;
    @FXML
    private DatePicker namTK;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadChart(2023);
    }

    public void setYear(ActionEvent evt) {
        Date.valueOf(this.namTK.getValue());
        series.getData().clear();
        series2.getData().clear();
        barBC.getData().clear();
        loadChart(this.namTK.getValue().getYear());

    }

    public void loadChart(int n) {
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


   

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktpm.services;

import com.ktpm.pojo.ChiTietPM;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author THANH NHAN
 */
public class ChiTietPmService {
      public boolean addChiTiet(ChiTietPM ctpm) throws SQLException {
        try ( Connection conn = JdbcUtils.getConn()) {
            conn.setAutoCommit(false);
            String sql = "INSERT INTO chitietpm(id_sach, id_pm) VALUES(?, ?)"; // SQL injection
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setInt(1, ctpm.getId_sach());
            stm.setInt(2, ctpm.getId_pm());
            stm.execute();
            try {
                conn.commit();
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                return false;
            }
        }
    }
}

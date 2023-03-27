/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktpm.services;

import com.ktpm.pojo.TheLoaiSach;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class TheLoaiService {
    public List<TheLoaiSach> getTheLoai() throws SQLException {
        List<TheLoaiSach> tls = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            Statement stm = conn.createStatement();

            ResultSet rs = stm.executeQuery("SELECT * FROM theloai");
            while (rs.next()) {
                String tenTL = rs.getString("tenTL");
                tls.add(new TheLoaiSach(tenTL));
            }
        }
        
        return tls;
    }
}

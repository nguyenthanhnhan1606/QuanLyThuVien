/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktpm.services;

import com.ktpm.pojo.Sach;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
public class SachService {
    public List<Sach> getSachs(String kw) throws SQLException {
        List<Sach> s = new ArrayList<>();

        try ( Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM sach";
            if (kw != null && !kw.isEmpty()) {
                sql += " WHERE content like concat('%', ?, '%')";
            }
            
            PreparedStatement stm = conn.prepareCall(sql);
            if (kw != null && !kw.isEmpty())
                stm.setString(1, kw);
           ResultSet rs = stm.executeQuery("SELECT * FROM sach");
            while (rs.next()) {
                String tenSach = rs.getString("tenSach");
                String tenTacGia = rs.getString("tenTacGia");
                Date namXB = rs.getDate("namXB");
                String moTa = rs.getString("moTa");
                String viTri = rs.getString("viTri");
                Date ngayNhapSach = rs.getDate("ngayNhapSach");
                int sachTheLoai = rs.getInt("sach_tl");
                s.add(new Sach(tenSach, tenTacGia, namXB, moTa, viTri, ngayNhapSach, sachTheLoai));
            }
        }
        return s;
    }
}

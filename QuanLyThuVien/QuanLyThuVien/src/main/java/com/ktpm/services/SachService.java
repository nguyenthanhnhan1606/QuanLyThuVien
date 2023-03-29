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
import java.util.List;

/**
 *
 * @author Admin
 */
public class SachService {

    public List<Sach> getSachs(String kw) throws SQLException {
        List<Sach> s = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM sach";
            if (kw != null && !kw.isEmpty()) {
                sql += " WHERE tenSach like concat('%', ?, '%')";
            }
            PreparedStatement stm = conn.prepareCall(sql);
            if (kw != null && !kw.isEmpty()) {
                stm.setString(1, kw);
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Sach sa = new Sach(rs.getInt("maSach"), rs.getString("tenSach"), rs.getString("tenTacGia"), rs.getDate("namXB"), rs.getString("moTa"), rs.getString("viTri"), rs.getDate("ngayNhapSach"), rs.getInt("sach_tl"));
                s.add(sa);
            }
        }
        return s;
    }

    public boolean addSach(Sach s) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            conn.setAutoCommit(false);
            String sql = "INSERT INTO sach(tenSach, tenTacGia, namXB, moTa, viTri, ngayNhapSach, sach_tl) VALUES(?, ?, ?, ?, ?, ?, ?)"; // SQL injection
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, s.getTenSach());
            stm.setString(2, s.getTenTacGia());
            stm.setDate(3, (java.sql.Date) s.getNamXB());
            stm.setString(4, s.getMoTa());
            stm.setString(5, s.getViTri());
            stm.setDate(6, (java.sql.Date) s.getNgayNhapSach());
            stm.setInt(7, s.getSach_tl());
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

    public boolean deleteSach(int maSach) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "DELETE FROM sach WHERE maSach=?";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setInt(1, maSach);

            return stm.executeUpdate() > 0;
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktpm.services;

import com.ktpm.pojo.TheLoaiSach;
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
public class TheLoaiService {

    public List<TheLoaiSach> getTheLoai() throws SQLException {
        List<TheLoaiSach> tls = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM theloaisach");
            while (rs.next()) {
                TheLoaiSach tl = new TheLoaiSach(rs.getInt("maTLS"), rs.getString("tenTL"));
                tls.add(tl);
            }
        }
        return tls;
    }

    public List<TheLoaiSach> getTheLoai(String kw) throws SQLException {
        List<TheLoaiSach> tls = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM theloaisach ";
            if (kw != null && !kw.isEmpty()) {
                sql += " Where tenTL like concat('%', ?, '%')";
            }
            PreparedStatement stm = conn.prepareCall(sql);
            if (kw != null && !kw.isEmpty()) {
                stm.setString(1, kw);
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                TheLoaiSach tl = new TheLoaiSach(rs.getInt("maTLS"), rs.getString("tenTL"));
                tls.add(tl);
            }
        }
        return tls;
    }

    public boolean addTheLoaiSach(TheLoaiSach tl) throws SQLException {
        TheLoaiService tls = new TheLoaiService();
        if (tls.Check(tl.getTenTL())) {
            try (Connection conn = JdbcUtils.getConn()) {
                conn.setAutoCommit(false);
                String sql = "INSERT INTO theloaisach(tenTL) VALUES(?)"; // SQL injection
                PreparedStatement stm = conn.prepareStatement(sql);
                stm.setString(1, tl.getTenTL());
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
        return false;
    }

    public boolean update(TheLoaiSach tl) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "Update theloaisach set tenTL=? Where maTLS=?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, tl.getTenTL());
            stm.setInt(2, tl.getMaTLS());
            int r = stm.executeUpdate();
            return r > 0;
        }
    }

    public boolean delete(int maTLS) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "DELETE FROM theloaisach WHERE maTLS=?";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setInt(1, maTLS);
            return stm.executeUpdate() > 0;
        }
    }

    public boolean Check(String text) throws SQLException {
        TheLoaiService tl = new TheLoaiService();
        List<TheLoaiSach> tls = tl.getTheLoai();
        for (TheLoaiSach tls1 : tls) {
            if (tls1.getTenTL().toUpperCase().equals(text.toUpperCase())) {
                return false;
            }
        }
        return true;
    }
}

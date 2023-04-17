/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktpm.services;

import com.ktpm.pojo.DoiTuong;
import com.ktpm.services.JdbcUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author THANH NHAN
 */
public class DoiTuongService {
    
    public List<DoiTuong> getDoiTuong() throws SQLException {
        List<DoiTuong> results = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()){
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM doituong");
            
            while (rs.next()){
                DoiTuong bp = new DoiTuong(rs.getInt("maDT"),rs.getString("loaiDT"));
                results.add(bp);
            }
            
        }
        return results;
    }
    public List<DoiTuong> getDoiTuong(String kw) throws SQLException {
        List<DoiTuong> doituong = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM doituong ";
            if (kw != null && !kw.isEmpty()) {
                sql += " Where loaiDT like concat('%', ?, '%')";
            }
            PreparedStatement stm = conn.prepareCall(sql);
            if (kw != null && !kw.isEmpty()) {
                stm.setString(1, kw);
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                DoiTuong dt = new DoiTuong(rs.getInt("maDT"), rs.getString("loaiDT"));
                doituong.add(dt);
            }
        }
        return doituong;
    }

    public boolean addDoiTuong(DoiTuong d) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            conn.setAutoCommit(false);
            String sql = "INSERT INTO doituong(loaiDT) VALUES(?)"; // SQL injection
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, d.getLoaiDT());
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
    
     public boolean update(DoiTuong d) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "Update doituong set loaiDT=? Where maDT=?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, d.getLoaiDT());
            stm.setInt(2, d.getMaDT());
            int r = stm.executeUpdate();
            return r > 0;
        }
    }
     
     public boolean delete(int maDT) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "DELETE FROM doituong WHERE maDT=?";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setInt(1, maDT);
            return stm.executeUpdate() > 0;
        }
    }
}

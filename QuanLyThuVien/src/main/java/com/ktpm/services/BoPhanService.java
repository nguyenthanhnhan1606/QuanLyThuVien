/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktpm.services;

import com.ktpm.pojo.BoPhan;
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
public class BoPhanService {

    public List<BoPhan> getBoPhan() throws SQLException {
        List<BoPhan> results = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM bophan");

            while (rs.next()) {
                BoPhan bp = new BoPhan(rs.getInt("maBP"), rs.getString("tenBP"));
                results.add(bp);
            }

        }
        return results;
    }

    public List<BoPhan> getBoPhan(String kw) throws SQLException {
        List<BoPhan> bophan = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM bophan ";
            if (kw != null && !kw.isEmpty()) {
                sql += " Where tenBP like concat('%', ?, '%')";
            }
            PreparedStatement stm = conn.prepareCall(sql);
            if (kw != null && !kw.isEmpty()) {
                stm.setString(1, kw);
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                BoPhan dt = new BoPhan(rs.getInt("maBP"), rs.getString("tenBP"));
                bophan.add(dt);
            }
        }
        return bophan;
    }

    public boolean addBoPhan(BoPhan b) throws SQLException {
        BoPhanService bp = new BoPhanService();
        if (bp.Check(b.getTenBP())) {
            try (Connection conn = JdbcUtils.getConn()) {
                conn.setAutoCommit(false);
                String sql = "INSERT INTO bophan(tenBP) VALUES(?)"; // SQL injection
                PreparedStatement stm = conn.prepareStatement(sql);
                stm.setString(1, b.getTenBP());
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

    public boolean update(BoPhan b) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "Update bophan set tenBP=? Where maBP=?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, b.getTenBP());
            stm.setInt(2, b.getMaBP());
            int r = stm.executeUpdate();
            return r > 0;
        }
    }

    public boolean delete(int maBP) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "DELETE FROM bophan WHERE maBP=?";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setInt(1, maBP);
            return stm.executeUpdate() > 0;
        }
    }

    public boolean Check(String text) throws SQLException {
        BoPhanService bp = new BoPhanService();
        List<BoPhan> b = bp.getBoPhan();
        for (BoPhan b1 : b) {
            if (b1.getTenBP().toUpperCase().equals(text.toUpperCase())) {
                return false;
            }
        }
        return true;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktpm.services;

import com.ktpm.pojo.DoiTuong;
import com.ktpm.services.JdbcUtils;
import java.sql.Connection;
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
                DoiTuong bp = new DoiTuong(rs.getString("loaiDT"));
                results.add(bp);
            }
            
        }
        return results;
    }
}

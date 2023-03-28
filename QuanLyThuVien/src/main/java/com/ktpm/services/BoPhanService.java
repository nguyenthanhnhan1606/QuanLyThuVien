/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktpm.services;

import com.ktpm.pojo.BoPhan;
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
public class BoPhanService {
    public List<BoPhan> getBoPhan() throws SQLException{
        List<BoPhan> results = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()){
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM bophan");
            
            while (rs.next()){
                BoPhan bp = new BoPhan(rs.getInt("maBP"),rs.getString("tenBP"));
                results.add(bp);
            }
            
        }
        return results;
    }
}

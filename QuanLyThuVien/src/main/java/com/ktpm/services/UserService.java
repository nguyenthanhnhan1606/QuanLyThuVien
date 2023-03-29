/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktpm.services;

import com.ktpm.pojo.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Admin
 */
public class UserService {
     public boolean addUser(User u) throws SQLException {
        // 1 cau chi 4 lua chon
        // chi 1 lua chon dung
        try ( Connection conn = JdbcUtils.getConn()) {
            conn.setAutoCommit(false);
            String sql = "INSERT INTO user(username,password, ten, gioitinh, ngaysinh, hanthe,diachi,email,sdt,user_bophan,user_doituong,user_role) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; // SQL injection
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, u.getUsername());
            stm.setString(2, u.getPassword());
            stm.setString(3,u.getTen());
            stm.setString(4,u.getGioitinh());
            stm.setDate(5,  u.getNgaysinh());
            stm.setDate(6,  u.getHanthe());  
            stm.setString(7,u.getDiachi());
            stm.setString(8,u.getEmail());
            stm.setString(9,u.getSdt());
            stm.setInt(10,u.getUser_bophan());
            stm.setInt(11,u.getUser_doituong());
            stm.setInt(12,u.getUser_role());
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktpm.services;

import com.ktpm.pojo.BoPhan;
import com.ktpm.pojo.User;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class UserService {

    public boolean addUser(User u) throws SQLException {
        if (Check(u)) {
            try (Connection conn = JdbcUtils.getConn()) {
                conn.setAutoCommit(false);
                String sql = "INSERT INTO user(username,password, ten, gioitinh, ngaysinh, hanthe,diachi,email,sdt,user_bophan,user_doituong,user_role) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; // SQL injection
                PreparedStatement stm = conn.prepareStatement(sql);
                stm.setString(1, u.getUsername());
                stm.setString(2, u.getPassword());
                stm.setString(3, u.getTen());
                stm.setString(4, u.getGioitinh());
                stm.setDate(5, u.getNgaysinh());
                stm.setDate(6, u.getHanthe());
                stm.setString(7, u.getDiachi());
                stm.setString(8, u.getEmail());
                stm.setString(9, u.getSdt());
                stm.setInt(10, u.getUser_bophan());
                stm.setInt(11, u.getUser_doituong());
                stm.setInt(12, u.getUser_role());
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

    public List<User> getUser() throws SQLException {
        List<User> results = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM user");

            while (rs.next()) {
                User e = new User(rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("ten"),
                        rs.getString("gioitinh"),
                        rs.getDate("hanthe"),
                        rs.getDate("ngaysinh"),
                        rs.getString("email"),
                        rs.getString("diachi"),
                        rs.getString("sdt"),
                        rs.getInt("user_bophan"),
                        rs.getInt("user_doituong"),
                        rs.getInt("user_role"));
                results.add(e);
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return results;
    }

    public boolean Check(User u) throws SQLException {
        UserService user = new UserService();
        List<User> users = user.getUser();
        for (User user1 : users) {
            if (user1.getUsername().equals(u.getUsername())) {
                return false;
            }

        }
        return true;
    }

}

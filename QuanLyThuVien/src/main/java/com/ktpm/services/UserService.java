/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktpm.services;

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

    PasswordService p = new PasswordService();

    public boolean addUser(User u) throws SQLException {
        if (checkEmty(u.getUsername(), u.getPassword(), u.getEmail())) {
            return false;
        } else {
            if (Check(u)) {
                if (checkChar(u.getPassword())) {
                    try (Connection conn = JdbcUtils.getConn()) {
                        conn.setAutoCommit(false);
                        String sql = "INSERT INTO user(username,password, ten, gioitinh, ngaysinh, hanthe,diachi,email,sdt,user_bophan,user_doituong,user_role) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; // SQL injection
                        PreparedStatement stm = conn.prepareStatement(sql);
                        stm.setString(1, u.getUsername().trim());
                        stm.setString(2, u.getPassword().trim());
                        stm.setString(3, u.getTen().trim());
                        stm.setString(4, u.getGioitinh());
                        stm.setDate(5, u.getNgaysinh());
                        stm.setDate(6, u.getHanthe());
                        stm.setString(7, u.getDiachi().trim());
                        stm.setString(8, u.getEmail().trim());
                        stm.setString(9, u.getSdt().trim());
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

                } else {
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
    public User getU(String username, String password) throws SQLException {
        UserService user = new UserService();
        List<User> users = user.getUser();
        for (User user1 : users) {
            if (user1.getUsername().equals(username) && user1.getPassword().equals(password)) {
                return user1;
            }
        }
        return null;
    }

    public boolean checkUpdate(User u) throws SQLException {
        return u.getUser_doituong() <= 0 || u.getEmail().isEmpty() || u.getTen().isEmpty() || u.getGioitinh().isEmpty() || u.getHanthe() == null || u.getUser_bophan() <= 0;
    }

    public boolean checkEmty(String username, String password, String email) {
        return username.isEmpty() || password.isEmpty() || email.isEmpty();
    }

    //ĐĂNG NHẬP
    public boolean checkLogin(String username, String password) throws SQLException {
        UserService user = new UserService();
        List<User> users = user.getUser();
        for (User user1 : users) {
            if (user1.getUsername().equals(username) && user1.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
    
     public boolean checkLoginAdmin(String username, String password) throws SQLException {
        UserService user = new UserService();
        List<User> users = user.getUser();
        for (User user1 : users) {
            if (user1.getUsername().equals(username) && user1.getPassword().equals(password) && user1.getUser_role()==2) {
                return true;
            }
        }
        return false;
    }
     
     
    public boolean checkChar(String password) {
        return p.check(password);
    }

    public boolean update(User u) throws SQLException {
        if (checkUpdate(u)) {
            return false;
        } else {
            try (Connection conn = JdbcUtils.getConn()) {
                String sql = "Update user set ten=?, gioitinh=?,ngaysinh=?,hanthe=?,diachi=?,email=?,sdt=?,user_bophan=?,user_doituong=? Where id=?";
                PreparedStatement stm = conn.prepareStatement(sql);
                stm.setString(1, u.getTen());
                stm.setString(2, u.getGioitinh());
                stm.setDate(3, (java.sql.Date) u.getNgaysinh());
                stm.setDate(4, (java.sql.Date) u.getHanthe());
                stm.setString(5, u.getDiachi());
                stm.setString(6, u.getEmail());
                stm.setString(7, u.getSdt());
                stm.setInt(8, u.getUser_bophan());
                stm.setInt(9, u.getUser_doituong());
                stm.setInt(10, u.getId());
                int r = stm.executeUpdate();
                return r > 0;
            }
        }
    }

    public boolean deleteUser(int id) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "DELETE FROM user WHERE id=?";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setInt(1, id);

            return stm.executeUpdate() > 0;
        }
    }

}

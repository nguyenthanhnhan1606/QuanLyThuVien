/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktpm.pojo;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.Date;
/**
 *
 * @author THANH NHAN
 */
public class User {

    private int id;
    private String username;
    private String password;
    private String ten;
    private String gioitinh;
    private Date ngaysinh;
    private Date hanthe;
    private String email;
    private String diachi;
    private String sdt;
    private int user_bophan;
    private int user_doituong;
    private int user_role;
    
    public User() throws ParseException {
        this.hanthe = new SimpleDateFormat("yyyy-MM-dd").parse(localDate().toString());
    }

   
    public User(String username, String password, String ten, String gioitinh, Date ngaysinh, String email, String diachi,
            String sdt, int user_bophan, int user_doituong) 
    {
        Date d= new Date(2002,02,11);
        this.username=username;
        this.password=password;
        this.ten=ten;
        this.gioitinh=gioitinh;
        this.ngaysinh=ngaysinh;
        this.hanthe= d;
        this.email=email;
        this.diachi=diachi;
        this.sdt=sdt;
        this.user_bophan=user_bophan;
        this.user_doituong=user_doituong;
        this.user_role=1;
        
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the ten
     */
    public String getTen() {
        return ten;
    }

    /**
     * @param ten the ten to set
     */
    public void setTen(String ten) {
        this.ten = ten;
    }

    /**
     * @return the gioitinh
     */
    public String getGioitinh() {
        return gioitinh;
    }

    /**
     * @param gioitinh the gioitinh to set
     */
    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    /**
     * @return the ngaysinh
     */
    public Date getNgaysinh() {
        return ngaysinh;
    }

    /**
     * @param ngaysinh the ngaysinh to set
     */
    public void setNgaysinh(Date ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    /**
     * @return the hanthe
     */
    public Date getHanthe() {
        return hanthe;
    }

    /**
     * @param hanthe the hanthe to set
     */
    public void setHanthe(Date hanthe) {
        this.hanthe = hanthe;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the diachi
     */
    public String getDiachi() {
        return diachi;
    }

    /**
     * @param diachi the diachi to set
     */
    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    /**
     * @return the sdt
     */
    public String getSdt() {
        return sdt;
    }

    /**
     * @param sdt the sdt to set
     */
    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    /**
     * @return the user_bophan
     */
    public int getUser_bophan() {
        return user_bophan;
    }

    /**
     * @param user_bophan the user_bophan to set
     */
    public void setUser_bophan(int user_bophan) {
        this.user_bophan = user_bophan;
    }

    /**
     * @return the user_doituong
     */
    public int getUser_doituong() {
        return user_doituong;
    }

    /**
     * @param user_doituong the user_doituong to set
     */
    public void setUser_doituong(int user_doituong) {
        this.user_doituong = user_doituong;
    }

    /**
     * @return the user_role
     */
    public int getUser_role() {
        return user_role;
    }

    /**
     * @param user_role the user_role to set
     */
    public void setUser_role(int user_role) {
        this.user_role = user_role;
    }

}

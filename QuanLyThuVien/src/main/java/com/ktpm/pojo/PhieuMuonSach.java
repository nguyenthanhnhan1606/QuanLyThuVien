/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktpm.pojo;

import java.util.Date;

/**
 *
 * @author THANH NHAN
 */
public class PhieuMuonSach {
    private int id;
    private Date ngaymuon;
    private int id_user;
    
    
    public PhieuMuonSach(Date ngaymuon, int id_user)
    {
        this.ngaymuon=ngaymuon;
        this.id_user=id_user;
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
     * @return the ngaymuon
     */
    public Date getNgaymuon() {
        return ngaymuon;
    }

    /**
     * @param ngaymuon the ngaymuon to set
     */
    public void setNgaymuon(Date ngaymuon) {
        this.ngaymuon = ngaymuon;
    }

    /**
     * @return the id_user
     */
    public int getId_user() {
        return id_user;
    }

    /**
     * @param id_user the id_user to set
     */
    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
}

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
    private Date hantra;
    private int id_user;
    private String trangthai;
    private int soluong;
    
    
    public PhieuMuonSach(Date ngaymuon,Date hantra, int id_user,int soluong, String trangthai)
    {
        this.ngaymuon=ngaymuon;
        this.hantra=hantra;
        this.id_user=id_user;
        this.soluong=soluong;
        this.trangthai=trangthai;
    }
    
     public PhieuMuonSach(int id,Date ngaymuon,Date hantra, int id_user,int soluong, String trangthai)
    {
        this.id=id;
        this.ngaymuon=ngaymuon;
        this.hantra=hantra;
        this.id_user=id_user;
        this.soluong=soluong;
        this.trangthai=trangthai;
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

    /**
     * @return the trangthai
     */
    public String getTrangthai() {
        return trangthai;
    }

    /**
     * @param trangthai the trangthai to set
     */
    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    /**
     * @return the soluong
     */
    public int getSoluong() {
        return soluong;
    }

    /**
     * @param soluong the soluong to set
     */
    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    /**
     * @return the hantra
     */
    public Date getHantra() {
        return hantra;
    }

    /**
     * @param hantra the hantra to set
     */
    public void setHantra(Date hantra) {
        this.hantra = hantra;
    }
}

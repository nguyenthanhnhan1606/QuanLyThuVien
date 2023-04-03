/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktpm.pojo;

/**
 *
 * @author THANH NHAN
 */
public class TheLoaiSach {
    private int maTLS;
    private String tenTL;

    
    public TheLoaiSach(int maTLS, String tenTL)
    {
        this.maTLS=maTLS;
        this.tenTL=tenTL;
    }

    @Override
    public String toString() {
        return this.tenTL; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    
    /**
     * @return the maTLS
     */
    public int getMaTLS() {
        return maTLS;
    }

    /**
     * @param maTLS the maTLS to set
     */
    public void setMaTLS(int maTLS) {
        this.maTLS = maTLS;
    }

    /**
     * @return the tenTL
     */
    public String getTenTL() {
        return tenTL;
    }

    /**
     * @param tenTL the tenTL to set
     */
    public void setTenTL(String tenTL) {
        this.tenTL = tenTL;
    }
}

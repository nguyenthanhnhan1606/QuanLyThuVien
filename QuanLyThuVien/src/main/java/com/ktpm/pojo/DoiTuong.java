/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktpm.pojo;

/**
 *
 * @author THANH NHAN
 */
public class DoiTuong {
    private int maDT;
    private String loaiDT;

    
    public DoiTuong(String loaiDT)
    {
        this.loaiDT=loaiDT;
    }
    /**
     * @return the maDT
     */
    public int getMaDT() {
        return maDT;
    }

    /**
     * @param maDT the maDT to set
     */
    public void setMaDT(int maDT) {
        this.maDT = maDT;
    }

    /**
     * @return the loaiDT
     */
    public String getLoaiDT() {
        return loaiDT;
    }

    /**
     * @param loaiDT the loaiDT to set
     */
    public void setLoaiDT(String loaiDT) {
        this.loaiDT = loaiDT;
    }
}

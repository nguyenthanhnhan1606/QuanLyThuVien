/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktpm.pojo;

/**
 *
 * @author THANH NHAN
 */
public class BoPhan {
    private int maBP;
    private String tenBP;

     public BoPhan(String tenBP)
    {
        this.tenBP=tenBP;
    }
    public BoPhan(int maBP,String tenBP)
    {
        this.maBP=maBP;
        this.tenBP=tenBP;
    }

    @Override
    public String toString() {
        return this.tenBP; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    /**
     * @return the maBP
     */
    public int getMaBP() {
        return maBP;
    }

    /**
     * @param maBP the maBP to set
     */
    public void setMaBP(int maBP) {
        this.maBP = maBP;
    }

    /**
     * @return the tenBP
     */
    public String getTenBP() {
        return tenBP;
    }

    /**
     * @param tenBP the tenBP to set
     */
    public void setTenBP(String tenBP) {
        this.tenBP = tenBP;
    }
    
}

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
public class Sach {

    private int maSach;
    private String tenSach;
    private String tenTacGia;
    private Date namXB;
    private String moTa;
    private String viTri;
    private Date ngayNhapSach;
    private int sach_tl;
    private String trangthai;
    
    public Sach(String tenSach, String tenTacGia, Date namXB, String moTa, String viTri,Date ngayns ,int sach_tl,String trangthai)
    {
        this.tenSach=tenSach;
        this.tenTacGia=tenTacGia;
        this.namXB=namXB;
        this.moTa=moTa;
        this.viTri=viTri;
        this.ngayNhapSach=ngayns;
        this.sach_tl=sach_tl;
        this.trangthai=trangthai;
    }
    
    public Sach(int maSach,String tenSach, String tenTacGia, Date namXB, String moTa, String viTri,Date ngayns ,int sach_tl,String trangthai)
    {
        this.maSach=maSach;
        this.tenSach=tenSach;
        this.tenTacGia=tenTacGia;
        this.namXB=namXB;
        this.moTa=moTa;
        this.viTri=viTri;
        this.ngayNhapSach=ngayns;
        this.sach_tl=sach_tl;
        this.trangthai=trangthai;
    }
    

    /**
     * @return the maSach
     */
    public int getMaSach() {
        return maSach;
    }

    /**
     * @param maSach the maSach to set
     */
    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    /**
     * @return the tenSach
     */
    public String getTenSach() {
        return tenSach;
    }

    /**
     * @param tenSach the tenSach to set
     */
    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    /**
     * @return the tenTacGia
     */
    public String getTenTacGia() {
        return tenTacGia;
    }

    /**
     * @param tenTacGia the tenTacGia to set
     */
    public void setTenTacGia(String tenTacGia) {
        this.tenTacGia = tenTacGia;
    }

    /**
     * @return the namXB
     */
    public Date getNamXB() {
        return namXB;
    }

    /**
     * @param namXB the namXB to set
     */
    public void setNamXB(Date namXB) {
        this.namXB = namXB;
    }

    /**
     * @return the moTa
     */
    public String getMoTa() {
        return moTa;
    }

    /**
     * @param moTa the moTa to set
     */
    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    /**
     * @return the viTri
     */
    public String getViTri() {
        return viTri;
    }

    /**
     * @param viTri the viTri to set
     */
    public void setViTri(String viTri) {
        this.viTri = viTri;
    }

    /**
     * @return the ngayNhapSach
     */
    public Date getNgayNhapSach() {
        return ngayNhapSach;
    }

    /**
     * @param ngayNhapSach the ngayNhapSach to set
     */
    public void setNgayNhapSach(Date ngayNhapSach) {
        this.ngayNhapSach = ngayNhapSach;
    }

    /**
     * @return the sach_tl
     */
    public int getSach_tl() {
        return sach_tl;
    }

    /**
     * @param sach_tl the sach_tl to set
     */
    public void setSach_tl(int sach_tl) {
        this.sach_tl = sach_tl;
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
    


}

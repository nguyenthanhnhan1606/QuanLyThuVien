/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktpm.pojo;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author THANH NHAN
 */
public class data {
    public static List<Sach> sa = new ArrayList<>();
    public static List<Sach> sa1= new ArrayList<>();
    /**
     * @return the sa
     */
    public List<Sach> getSa() {
        return sa;
    }

    /**
     * @param sa the sa to set
     */
    public void setSa(List<Sach> sa) {
        this.sa = sa;
    }
    
    public boolean kts(Sach s)
    {
        for(int i=0;i<getSa().size();i++)
            if(getSa().get(i).getMaSach()==s.getMaSach())
                return false;
        return true;
    }

    
     public boolean kts1(Sach s)
    {
        for(int i=0;i<getSa1().size();i++)
            if(getSa1().get(i).getMaSach()==s.getMaSach())
                return false;
        return true;
    }
    /**
     * @return the sa1
     */
    public static List<Sach> getSa1() {
        return sa1;
    }

    /**
     * @param aSa1 the sa1 to set
     */
    public static void setSa1(List<Sach> aSa1) {
        sa1 = aSa1;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktpm.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author THANH NHAN
 */
public class data {
    public static List<Sach> sa = new ArrayList<>();
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
}

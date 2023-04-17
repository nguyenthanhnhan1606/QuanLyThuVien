/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
import com.ktpm.services.DoiTuongService;

import com.ktpm.pojo.DoiTuong;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
 *
 * @author Admin
 */
public class DoiTuongTester {

    @Test
    public void testGetDoiTuong_0args() throws Exception {
        DoiTuongService instance = new DoiTuongService();
        List<DoiTuong> actual = instance.getDoiTuong();
        for (DoiTuong dt : actual) {
            Assertions.assertNotNull(dt);
        }
    }

    @Test
    public void testGetDoiTuong_String() throws Exception {
        String kw = "Sinh viên";
        DoiTuongService instance = new DoiTuongService();
        List<DoiTuong> actual = instance.getDoiTuong(kw);
        Assertions.assertEquals(1, actual.size());
    }

//    @Test
//    public void testAddDoiTuong() throws Exception {
//        int maDT = 10;
//        DoiTuongService instance = new DoiTuongService();
//        DoiTuong dt = new DoiTuong(maDT, "Công nhân viên");
//        boolean actual = instance.addDoiTuong(dt);
//        Assertions.assertTrue(actual);
//    }

//    @Test
//    public void testUpdate() throws Exception {
//        DoiTuongService instance = new DoiTuongService();
//        DoiTuong dt = new DoiTuong(1, "Hiệu trưởng");
//        boolean actual = instance.update(dt);
//        Assertions.assertTrue(actual);
//    }

//    @Test
//    public void testDelete() throws Exception {
//        int maDT = 4;
//        DoiTuongService instance = new DoiTuongService();
//        boolean actual = instance.delete(maDT);
//        Assertions.assertTrue(actual);
//    }

}
import com.ktpm.services.TheLoaiService;

import com.ktpm.pojo.TheLoaiSach;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class TheLoaiTester {
    
    @Test
    public void testGetTheLoai_0args() throws Exception {
        TheLoaiService instance = new TheLoaiService();
        List<TheLoaiSach> actual = instance.getTheLoai();
        for (TheLoaiSach tl : actual) {
            Assertions.assertNotNull(tl);
        }
       
    }

    public void testGetTheLoai_String() throws Exception {
        String kw = "Giáo trình";
        TheLoaiService instance = new TheLoaiService();
        List<TheLoaiSach> actual = instance.getTheLoai(kw);
        Assertions.assertEquals(1, actual.size());
    }

//    @Test
//    public void testAddTheLoaiSach() throws Exception {
//        int i = 10;
//        TheLoaiService instance = new TheLoaiService();
//        TheLoaiSach tl = new TheLoaiSach(i, "Sách test");
//        boolean actual = instance.addTheLoaiSach(tl);
//        Assertions.assertTrue(actual);
//    }
//
//    
//    
//    
//    @Test
//    public void testUpdate() throws Exception {
//        TheLoaiService instance = new TheLoaiService();
//        TheLoaiSach tl = new TheLoaiSach(9, "Sách test1");
//        boolean actual = instance.update(tl);
//        Assertions.assertTrue(actual);
//        
//        
//        
//    }
//
//    @Test
//    public void testDelete() throws Exception {
//        int maTLS = 0;
//        TheLoaiService instance = new TheLoaiService();
//        boolean actual = instance.delete(maTLS);
//        Assertions.assertTrue(actual);
//    }
    
}
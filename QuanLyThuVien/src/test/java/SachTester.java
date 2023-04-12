
import com.ktpm.pojo.Sach;
import com.ktpm.services.SachService;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.*;

/**
 *
 * @author THANH NHAN
 */
public class SachTester {

    private static SachService sach;

    //Kiểm tra tìm kiếm
    @Test
    public void testSearch() {
        try {
            sach = new SachService();
            List<Sach> s = sach.getAllSach("lu");
            Assertions.assertEquals(3, s.size());
            for (Sach sa : s) {
                Assertions.assertTrue(sa.getTenSach().contains("lu"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SachTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Kiểm tra sách chưa đặt
    @Test
    public void testSachCD() {
        try {
            sach = new SachService();
            List<Sach> s = sach.getSachs(null);
            Assertions.assertEquals(6, s.size());
        } catch (SQLException ex) {
            Logger.getLogger(SachTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testSachCDF() {
        try {
            sach = new SachService();
            List<Sach> s = sach.getAllSach(null);
            boolean actual = s.size() == 7;
            Assertions.assertFalse(actual);
        } catch (SQLException ex) {
            Logger.getLogger(SachTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    Kiểm tra thêm sách
//    @Test
//    public void testAddSach() throws SQLException {
//        Date t = Date.valueOf(LocalDate.now());
//        sach = new SachService();
//        Sach sa = new Sach("Sách test",
//                "ntn",
//                t, 
//                "Đây là sách test",
//                "tầng 1",
//                t,
//                1,
//                "Chưa đặt");
//        boolean actual = sach.addSach(sa);
//        Assertions.assertTrue(actual);
//    }
    @Test
    public void testAddSachF() throws SQLException {
        Date t = Date.valueOf(LocalDate.now());
        sach = new SachService();
        Sach sa = new Sach("",
                "ntn",
                t,
                "Đây là sách test",
                "tầng 1",
                t,
                1,
                "Chưa đặt");
        boolean actual = sach.addSach(sa);
        Assertions.assertFalse(actual);
    }

    //Kiểm tra upate sách
    @Test
    public void testUpdateS() throws SQLException {
        Date t = Date.valueOf(LocalDate.now());
        sach = new SachService();
        Sach sa = new Sach(27, "test mới nhất",
                "ntn",
                t,
                "Đây là sách test",
                "tầng 1",
                t,
                1,
                "Chưa đặt");
        boolean actual = sach.update(sa);
        Assertions.assertTrue(actual);
    }

    @Test
    public void testUpdateTT() throws SQLException {
        sach = new SachService();
        boolean actual = sach.updateTT(27);
        Assertions.assertTrue(actual);
    }

    @Test
    public void testUpdateTTCu() throws SQLException {
        sach = new SachService();
        boolean actual = sach.updateTtCu(25);
        Assertions.assertTrue(actual);
    }

    @Test
    public void testgetSachPm() throws SQLException {
        sach = new SachService();
        List<Sach> s = sach.getSachOnPM(31);
        Assertions.assertEquals(1, s.size());
    }
    
    @Test
    public void testgetSachPmHuy() throws SQLException {
        sach = new SachService();
        List<Sach> s = sach.getSachOnPMHuy(31);
        for (Sach sach1 : s) {
            Assertions.assertNull(sach1);
        }
    }
    
    @Test
    public void testDel() throws SQLException {
        sach = new SachService();
        boolean actual = sach.deleteSach(26);
        Assertions.assertTrue(actual);
    }
}

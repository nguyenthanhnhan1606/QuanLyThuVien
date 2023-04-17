
import com.ktpm.pojo.PhieuMuonSach;
import com.ktpm.services.JdbcUtils;
import com.ktpm.services.PhieuMuonService;
import com.ktpm.services.UserService;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
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
public class PhieuMuonTester {

    private static PhieuMuonService pm;

//    @Test
//    public void testAddPhieu() throws SQLException {
//        pm = new PhieuMuonService();
//        Date d = Date.valueOf(LocalDate.now());
//        PhieuMuonSach pms = new PhieuMuonSach(d, d, 10, 0, "Đã hủy");
//        boolean actual = pm.addPhieuMuon(pms);
//        Assertions.assertTrue(actual);
//    }
    @Test
    public void testGetPm() throws SQLException {
        pm = new PhieuMuonService();
        PhieuMuonSach pms = pm.getPM(2023, 05, 06, 8);
        Assertions.assertNotNull(pms.getId());
    }

    @Test
    public void testGetPmF() throws SQLException {
        pm = new PhieuMuonService();
        PhieuMuonSach pms = pm.getPM(2023, 05, 06, 200);
        Assertions.assertNull(pms);
    }

    @Test
    public void testGetPmTT() throws SQLException {
        pm = new PhieuMuonService();
        PhieuMuonSach pms = pm.getPMTT(2023, 4, 15, 8);
        Assertions.assertNotNull(pms);
    }

    @Test
    public void testGetPmTTF() throws SQLException {
        pm = new PhieuMuonService();
        PhieuMuonSach pms = pm.getPMTT(2023, 05, 06, 200);
        Assertions.assertNull(pms);
    }

    @Test
    public void testKiemTraMuon() throws SQLException {
        pm = new PhieuMuonService();
        boolean actual = pm.kiemTraMuon(8);
        Assertions.assertFalse(actual);
    }

    @Test
    public void testKiemTraMuonF() throws SQLException {
        pm = new PhieuMuonService();
        boolean actual = pm.kiemTraMuon(18);
        Assertions.assertTrue(actual);
    }
    
    @Test
    public void testGetHis() throws SQLException {
        pm = new PhieuMuonService();
        List<PhieuMuonSach> p = pm.getHis(8);
        Assertions.assertTrue(p.size()==5);
    }
    
    @Test
    public void testGetPhieu() throws SQLException {
        pm = new PhieuMuonService();
        List<PhieuMuonSach> p = pm.getPhieuMuonSach();
        Assertions.assertTrue(p.size()==5);
    }
    
    @Test
    public void testGetPhieuXN() throws SQLException {
        pm = new PhieuMuonService();
        List<PhieuMuonSach> p = pm.getPhieuMuonSachXN(null);
        Assertions.assertTrue(p.size()==2);
    }
    
     @Test
    public void testGetPhieuTS() throws SQLException {
        pm = new PhieuMuonService();
        List<PhieuMuonSach> p = pm.getPhieuMuonSachTS(null);
        Assertions.assertTrue(p.size()==3);
    }
    
     @Test
    public void testGetPhieuQ1() throws SQLException {
        pm = new PhieuMuonService();
        List<PhieuMuonSach> p = pm.getPhieuMuonSachQ1(2023);
        Assertions.assertTrue(p.size()==0);
    }
    @Test
    public void testGetPhieuQ2() throws SQLException {
        pm = new PhieuMuonService();
        List<PhieuMuonSach> p = pm.getPhieuMuonSachQ2(2023);
        Assertions.assertTrue(p.size()==3);
    }
    
    @Test
    public void testGetPhieuQ3() throws SQLException {
        pm = new PhieuMuonService();
        List<PhieuMuonSach> p = pm.getPhieuMuonSachQ3(2023);
        Assertions.assertTrue(p.size()==0);
    }
    @Test
    public void testGetPhieuQ4() throws SQLException {
        pm = new PhieuMuonService();
        List<PhieuMuonSach> p = pm.getPhieuMuonSachQ4(2023);
        Assertions.assertTrue(p.size()==0);
    }
}



/**
 *
 * @author THANH NHAN
 */
import com.ktpm.services.BoPhanService;
import com.ktpm.pojo.BoPhan;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BoPhanTester {

    @Test
    public void testGetBoPhan_0args() throws Exception {
        BoPhanService instance = new BoPhanService();
        List<BoPhan> actual = instance.getBoPhan();
        for (BoPhan tl : actual) {
            Assertions.assertNotNull(tl);
        }
    }

    @Test
    public void testGetBoPhan_String() throws Exception {
        String kw = "Khoa Công Nghệ Thông Tin";
        BoPhanService instance = new BoPhanService();
        List<BoPhan> actual = instance.getBoPhan(kw);
        Assertions.assertEquals(1, actual.size());
    }

//    @Test
//    public void testAddBoPhan() throws Exception {
//        int maBP = 10;
//        BoPhanService instance = new BoPhanService();
//        BoPhan bp = new BoPhan(maBP, "Khoa Luật");
//        boolean actual = instance.addBoPhan(bp);
//        Assertions.assertTrue(actual);
//    }
    
    @Test
    public void testUpdate() throws Exception {
        BoPhanService instance = new BoPhanService();
        BoPhan bp = new BoPhan(4, "Khoa Đồ Họa");
        boolean actual = instance.update(bp);
        Assertions.assertTrue(actual);
    }
    
    @Test
    public void testDelete() throws Exception {
        int maBP = 6;
        BoPhanService instance = new BoPhanService();
        boolean actual = instance.delete(maBP);
        Assertions.assertTrue(actual);
    }

}
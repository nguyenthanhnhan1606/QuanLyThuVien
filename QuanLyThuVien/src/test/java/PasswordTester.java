import com.ktpm.services.PasswordService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;


public class PasswordTester {
    
    // Password bao gồm: 1 ký tự hoa, 1 ký tự thường, 1 ký tự số, 1 l ký tự đặc biệt, từ 6-45 ký tự
    @Test
    public void testCheck() {
        String password0 = "admin@123"; // Không có ký tự hoa
        String password1 = "ADMIN@123"; // Không có ký tự thường
        String password2 = "ADMIN@abc"; // Không có ký tự số
        String password3 = "ADMIN123"; // Không có ký tự đặc biệt
        String password4 = "ADMIN"; // 5 ký tự
        String password5 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"; // 46 ký tự
        
        String password = "Admin@123"; // Đủ yêu cầu
        
        PasswordService instance = new PasswordService();
        boolean expected = true;
        boolean actual = instance.check(password);
        Assertions.assertEquals(expected, actual);
        
    }
    
    // Email bao gồm:
    // - Ký tự đầu phải là chữ cái
    // - Ký tự tiếp theo tùy chọn (Không chứ ký tự đặc biệt) + @ (Chỉ xuất hiện 1 lần)
    // - Sau @ là tên domain (gmail.com)
    @Test
    public void testCheckEmail() {
        String email0 = "Admin123@yahoo.com"; // Khác tên domain
        String email1 = "0admin123@gmail.com"; // Bắt đầu bằng chữ số
        String email2 = "admin!@gmail.com"; // Chứa ký tự đặc biệt
        String email3 = "Adm@n@gmail.com"; // Ký tự @ xuất hiện 2 lần
        
        
        String email = "Admin123@gmail.com"; // Đủ yêu cầu
        
        PasswordService instance = new PasswordService("email");
        boolean expected = true;
        boolean actual = instance.checkEmail(email);
        Assertions.assertEquals(expected, actual);
       
    }
    
    // SĐT bao gồm: Chỉ chưa chữ số, độ dài từ 9-12 ký tự
    @Test
    public void testCheckSdt() {
        String sdt0 = "0903838"; // 8 ký tự
        String sdt1 = "0903838081123"; // 13 ký tự
        String sdt2 = "a090383801"; // Chứa ký tự chữ
        String sdt3 = "0903838081@"; // 13 ký tự
        
        String sdt = "0903838081";
        PasswordService instance = new PasswordService(1);
        boolean expected = true;
        boolean actual = instance.checkSdt(sdt);
        Assertions.assertEquals(expected, actual);
        
        
    }
    
}

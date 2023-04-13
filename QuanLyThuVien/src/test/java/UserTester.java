
import com.ktpm.pojo.User;
import com.ktpm.services.JdbcUtils;
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author THANH NHAN
 */
public class UserTester {

    private static Connection conn;
    private static UserService user;

    @BeforeAll
    public static void beforeAll1() {
        try {
            conn = JdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(UserTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @AfterAll
    public static void afterAll1() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testSearch() {
        try {
            user = new UserService();
            List<User> us = user.getUser("Nguyễn");
            Assertions.assertEquals(2, us.size());
            for (User u : us) {
                Assertions.assertTrue(u.getTen().contains("Nguyễn"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SachTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Kiểm tra addUser 
//    public void testAddT() throws SQLException, NoSuchAlgorithmException {
//        Date t = Date.valueOf(LocalDate.now());
//        user = new UserService();
//        User u = new User("testnew", "Admin@123", "abcdef", "Nam", t, "bcd@gmail.com", "akjdan", "adas", 1, 1);
//        boolean actual = user.addUser(u);
//        Assertions.assertTrue(actual);
//    }
    @Test
    public void testAddF() throws SQLException, NoSuchAlgorithmException {
        Date t = Date.valueOf(LocalDate.now());
        user = new UserService();
        User u = new User("test001", "Admin@123", "abcdef", "Nam", t, "bcd@gmail.com", "akjdan", "adas", 1, 1);
        boolean actual = user.addUser(u);
        Assertions.assertFalse(actual);
    }

    @Test
    public void testAddF1() throws SQLException, NoSuchAlgorithmException {
        Date t = Date.valueOf(LocalDate.now());
        user = new UserService();
        User u = new User("testnew1", "admin@123", "abcdef", "Nam", t, "bcd@gmail.com", "akjdan", "adas", 1, 1);
        boolean actual = user.addUser(u);
        Assertions.assertFalse(actual);
    }

    @Test
    public void testAddF2() throws SQLException, NoSuchAlgorithmException {
        Date t = Date.valueOf(LocalDate.now());
        user = new UserService();
        User u = new User("", "admin@123", "abcdef", "Nam", t, "bcd@gmail.com", "akjdan", "adas", 1, 1);
        boolean actual = user.addUser(u);
        Assertions.assertFalse(actual);
    }

    //Kiểm tra có tham số nào null không
    @Test
    public void testisEmty() {
        user = new UserService();
        boolean actual = user.checkEmty("", "Admin@123", "abc@gmail.com");
        Assertions.assertTrue(actual);
    }

    @Test
    public void testEmty() {
        user = new UserService();
        boolean actual = user.checkEmty("test11", "Admin@123", "abc@gmail.com");
        Assertions.assertFalse(actual);
    }

    //kiểm tra mật khẩu có đủ mạnh k
    @Test
    public void testPass() {
        user = new UserService();
        boolean actual = user.checkChar("Admin@123");
        Assertions.assertTrue(actual);
    }

    @Test
    public void testPassKm() {
        user = new UserService();
        boolean actual = user.checkChar("admin@123");
        Assertions.assertFalse(actual);
    }

    //kiểm tra xem có lấy được thông tin user khi đăng nhập k
    @Test
    public void testGetUser() throws SQLException {
        user = new UserService();
        User u = user.getU("test001", "Admin@123");
        Assertions.assertNotNull(u);
    }

    @Test
    public void testGetUserF() throws SQLException {
        user = new UserService();
        User u = user.getU("test001", "test123");
        Assertions.assertNull(u);
    }

    //Kiểm tra đăng nhập
    @Test
    public void testLogin() throws SQLException {
        user = new UserService();
        boolean actual = user.checkLogin("test001", "Admin@123");
        Assertions.assertTrue(actual);
    }

    @Test
    public void testLoginF() throws SQLException {
        user = new UserService();
        boolean actual = user.checkLogin("test001", "test123");
        Assertions.assertFalse(actual);
    }

    //Kiểm tra đăng nhập của admin
    @Test
    public void testLoginAdmin() throws SQLException {
        user = new UserService();
        boolean actual = user.checkLoginAdmin("test06", "1");
        Assertions.assertTrue(actual);
    }

    @Test
    public void testLoginAdminF() throws SQLException {
        user = new UserService();
        boolean actual = user.checkLoginAdmin("test001", "test123");
        Assertions.assertFalse(actual);
    }

    //Kiểm tra đăng kí có bị trùng username không
    @Test
    public void testUsername() throws SQLException, NoSuchAlgorithmException {
        Date t = Date.valueOf(LocalDate.now());
        user = new UserService();
        User u = new User("testnew2", "Admin@123", "abcdef", "Nam", t, "bcd@gmail.com", "akjdan", "adas", 1, 1);
        boolean actual = user.Check(u);
        Assertions.assertTrue(actual);
    }

    @Test
    public void testUsernameF() throws SQLException, NoSuchAlgorithmException {
        Date t = Date.valueOf(LocalDate.now());
        user = new UserService();
        User u = new User("test001", "Admin@123", "abcdef", "Nam", t, "bcd@gmail.com", "akjdan", "adas", 1, 1);
        boolean actual = user.Check(u);
        Assertions.assertFalse(actual);
    }

    //Kiểm tra sủa thông tin user 
    @Test
    public void testUpdate() throws SQLException, NoSuchAlgorithmException {
        Date t = Date.valueOf(LocalDate.now());
        user = new UserService();
        User u = new User(28, "testnew", "Admin@123", "Nguyễn Văn D", "Nam", t, t, "bcd@gmail.com", "akjdan", "adas", 1, 1, 1,"","");
        boolean actual = user.update(u);
        Assertions.assertTrue(actual);
    }

    @Test
    public void testUpdateF() throws SQLException, NoSuchAlgorithmException {
        Date t = Date.valueOf(LocalDate.now());
        user = new UserService();
        User u = new User("testnew", "Admin@123", "Nguyễn Văn D", "Nam", t, "bcd@gmail.com", "akjdan", "adas", 1, 0);
        boolean actual = user.update(u);
        Assertions.assertFalse(actual);
    }

    //Kiểm tra xóa 1 tài khoản user
    @Test
    public void testDel() throws SQLException {
        user = new UserService();
        boolean actual = user.deleteUser(29);
        Assertions.assertTrue(actual);
    }

    @Test
    public void testDelF() throws SQLException {
        user = new UserService();
        boolean actual = user.deleteUser(30);
        Assertions.assertFalse(actual);
    }

}

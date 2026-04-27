package test;
import database.DBManager;import main.User;import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import java.util.List;import static org.junit.jupiter.api.Assertions.*;
/**
 * @author Victoria Ha
 * @version 0.1.0
 * Description:
 * @since 4/6/2026
 */
public class DBTests {
    private User juicydrop;
    private DBManager db; //i dont know how the tests interact  with the dbmanager


    @BeforeEach
    void setUp() {
        juicydrop = new User("SenorSour","unomas");
    }

    @AfterEach
    void tearDown() {
    }

   @Test
    void userInsert() {
        String username = "SenorSour";
        String password = "unomas";

        db.insertUser(username, password);
        List<User> users = userDao.getAllUsersList();
        assertNotNull(users.get(0));
        assertEquals(username, users.get(0).getUsername());

    }
}
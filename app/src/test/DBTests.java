import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
/**
 * @author Victoria Ha
 * @version 0.1.0
 * Description:
 * @since 4/6/2026
 */
public class DBTests {
    private userDao;
    private UserDB db;

    @BeforeEach
    void setUp() {
        public void createDb(){
        }
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void userInsert() {
        String username = "SenorSour;
        String password = "unomas";
        User user = new User(username, password);
        userDao.insert(user);
        List<User> users = userDao.getAllUsersList();
        assertNotNull(users.get(0));
        assertEquals(username, users.get(0).getUsername());
        // write your test here
    }
}
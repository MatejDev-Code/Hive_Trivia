import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import database.DBManager;
import java.util.List;


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
        db = DBManager.getInstance();
    }

    @AfterEach
    void tearDown() {
        DBManager.close();
    }

   @Test
    void userInsert() {
        String username = "SenorSour";
        String password = "unomas";

        db.insertUser(juicydrop);
        List<User> users = db.getAllUsers();
        assertNotNull(users.get(0));
        assertEquals(username, users.get(0).getUsername());

    }
}
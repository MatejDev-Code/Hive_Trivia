import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import model.PulledQuestion;


class QuestionAPITest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testAPIPull(){
        try {
            PulledQuestion pulledQuestion = QuestionAPI.getSingleQuestion();

            assertNotNull(pulledQuestion, "Successfully Returned Object");
        } catch (Exception e) {
            fail("Api threw an exception");
        }
    }

}
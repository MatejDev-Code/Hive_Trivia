import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 Question.java
 * All elements of the question that is being pulled from the API
 @author Matteo Ristoski
 @version 0.1.0
 @since 5/2/2026
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public record Question(
        @JsonProperty("category") String category,
        @JsonProperty("id") String id,
        @JsonProperty("question") QuestionText question,
        @JsonProperty("correctAnswer") String correctAnswer,
        @JsonProperty("incorrectAnswers") List<String> incorrectAnswers
) {}




import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

/**
 QuestionAPI.java
 * Pull questions from a public REST API
 @author Matteo Ristoski
 @version 0.1.0
 @since 5/2/2026
 */


public class QuestionAPI {

    private QuestionAPI() {}

    public static final String QUESTIONS_API = "https://the-trivia-api.com/v2/questions?limit=5&types=text_choice";
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static List<Question> toList(String inputStream) {
        try {
            return OBJECT_MAPPER.readValue(inputStream, new TypeReference<>() {
            });
        } catch (IOException exc) {
            throw new UncheckedIOException(exc);
        }
    }

    public static Question toObject(InputStream inputStream) {
        try {
            return OBJECT_MAPPER.readValue(inputStream, Question.class);
        } catch (IOException exc) {
            throw new UncheckedIOException(exc);
        }
    }

    public static String toJson(Question question) {
        try {
            return OBJECT_MAPPER.writeValueAsString(question);
        } catch (JsonProcessingException exc) {
            throw new UncheckedIOException(exc);
        }
    }

    public static List<Question> getQuestions() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(QUESTIONS_API))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        return toList(response.body());
    }

}


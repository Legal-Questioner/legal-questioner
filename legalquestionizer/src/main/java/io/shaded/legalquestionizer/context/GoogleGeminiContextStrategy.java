package io.shaded.legalquestionizer.context;

import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.generativeai.preview.GenerativeModel;
import io.github.cdimascio.dotenv.Dotenv;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class GoogleGeminiContextStrategy implements ContextStrategy {
  private final GenerativeModel model;
  private final Dotenv dotenv;
  private final int queryCharLimit;
  private final String location;
  private final String projectId;

  public GoogleGeminiContextStrategy() throws IOException {
    final int defaultCharLimit = 30000;
    final String defaultLocation = "us-central1";

    model = launchGemini();
    dotenv = Dotenv.load();
    queryCharLimit = dotenvAsInt("GEMINI_CHAR_LIMIT", defaultCharLimit);
    location = dotenvAsString("LOCATION", defaultLocation);

    projectId = dotenv.get("PROJECT_ID");
    if (projectId == null) {
      throw new NullPointerException();
    }
  }

  private GenerativeModel launchGemini() throws IOException {
    final String modelName = "gemini-pro";

    try (VertexAI vertexAI = new VertexAI(projectId, location)) {
      return new GenerativeModel(modelName, vertexAI);
    }
  }

  private int dotenvAsInt(String key, int defaultValue) {
    final String value = dotenv.get(key);
    if (value != null) {
      return Integer.parseInt(value);
    } else {
      return defaultValue;
    }
  }

  private String dotenvAsString(String key, String defaultValue) {
    final String value = dotenv.get(key);
    if (value != null) {
      return value;
    } else {
      return defaultValue;
    }
  }

  @Override
  public String getSimplification(String question,
                                  String[] documentTexts) throws IOException {
    final String query = formatToGeminiQuery(question, documentTexts);
    return model.generateContent(query).toString();
  }

  private String formatToGeminiQuery(String question, String[] documentTexts) {
    final String findWhitespaceRegex = "[\\r\\n]+";

    String formattedDocumentTexts = StringUtils
      .join(List.of(documentTexts), ' ')
      .replaceAll(findWhitespaceRegex, " ");
    formattedDocumentTexts = formattedDocumentTexts
      .substring(0, Math.min(formattedDocumentTexts.length(),
        queryCharLimit));

    return String.format("answer the question %s by summarizing the " +
      "following text: %s", question, formattedDocumentTexts);
  }
}

package io.shaded.legalquestionizer.context

import com.google.cloud.vertexai.VertexAI
import com.google.cloud.vertexai.generativeai.preview.GenerativeModel
import org.springframework.stereotype.Component


@Component
class GoogleGeminiContextStrategy : ContextStrategy {
  val model: GenerativeModel = launchGemini()

  override fun getSimplification(question: String,
                                 documentTexts: Array<String>): String {
    val query = formatToGeminiQuery(question, documentTexts)
    return model.generateContent(query).toString()
  }

  private fun formatToGeminiQuery(question: String,
                                  documentTexts: Array<String>): String {
    val formattedDocumentTexts = documentTexts
      .map { it.replace("\r", "") }
      .map { it.replace("\n", " ") }
      .joinToString(limit = GEMINI_CHARACTER_LIMIT) { "\n" }

    return "answer the question $question " +
      " by summarizing the following text: $formattedDocumentTexts"
  }

  private fun launchGemini(): GenerativeModel {
    VertexAI(PROJECT_ID, LOCATION).use {
      return GenerativeModel(MODEL_NAME, it)
    }
  }

  companion object {
    private const val GEMINI_CHARACTER_LIMIT = 30000
    private const val PROJECT_ID = "your-google-cloud-project-id"
    private const val LOCATION = "us-central1"
    private const val MODEL_NAME = "gemini-pro"
  }
}

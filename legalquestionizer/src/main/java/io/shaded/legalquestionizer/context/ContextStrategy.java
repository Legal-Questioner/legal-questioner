package io.shaded.legalquestionizer.context;

import java.io.IOException;

public interface ContextStrategy {
  String getSimplification(String question,
                           String[] documentTexts) throws IOException;
}

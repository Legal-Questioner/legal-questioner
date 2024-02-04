package io.shaded.legalquestionizer.context;

import org.jetbrains.annotations.NotNull;

public interface ContextStrategy {
  String getSimplification(@NotNull String question,
                                  @NotNull String[] documentTexts);
}

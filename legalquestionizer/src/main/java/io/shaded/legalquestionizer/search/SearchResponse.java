package io.shaded.legalquestionizer.search;

import java.util.List;
import java.util.UUID;

public record SearchResponse(Type type, List<UUID> documents) {

  enum Type {
    DOCUMENT, CONTEXT, ALL
  }
}

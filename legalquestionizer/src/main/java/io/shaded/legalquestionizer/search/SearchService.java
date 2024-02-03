package io.shaded.legalquestionizer.search;

import org.jdbi.v3.core.Jdbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SearchService {
  private final Jdbi jdbi;

  @Autowired
  public SearchService(Jdbi jdbi) {
    this.jdbi = jdbi;
  }

  /**
   * Searches both the documents and contexts to obtain the closes documents
   * for that input
   *
   * @param input The input string form the front end.
   * @return A list of documents that are similar to that input
   */
  public List<UUID> search(String input) {
    // Handle the case of null input / empty string.
    if (input == null || input.isEmpty()) {
      return List.of();
    }

    return jdbi.withHandle(handle -> handle.createQuery
        ("""
          SELECT id FROM documents WHERE search_vector @@ ts_query(:input)
          UNION
          SELECT document_id FROM document_contexts WHERE search_vector @@ ts_query(:input)
          """)
      .bind("input", input)
      .mapTo(UUID.class)
      .list());
  }

  /**
   * Searches documents to find the closes document with that input string.
   *
   * @param input The input string form the front end.
   * @return A list of documents that are similar to that input
   */
  public List<UUID> searchDocuments(String input) {
    // Handle the case of null input / empty string.
    if (input == null || input.isEmpty()) {
      return List.of();
    }

    return jdbi.withHandle(handle -> handle.createQuery
        ("SELECT id FROM documents WHERE search_vector @@ ts_query(:input)")
      .bind("input", input)
      .mapTo(UUID.class)
      .list());
  }

  /**
   * Searches the questions and responses search in the database for a
   * matching document.
   *
   * @param input The input string from the front end
   * @return A list of documents that are similar to that input
   */
  public List<UUID> searchContexts(String input) {
    // Handle the case of null input / empty string.
    if (input == null || input.isEmpty()) {
      return List.of();
    }

    return jdbi.withHandle(handle -> handle.createQuery
        ("SELECT document_id FROM document_contexts WHERE search_vector @@ ts_query(:input)")
      .bind("input", input)
      .mapTo(UUID.class)
      .list());
  }
}

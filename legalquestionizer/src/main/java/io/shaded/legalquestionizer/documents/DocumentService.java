package io.shaded.legalquestionizer.documents;

import org.jdbi.v3.core.Jdbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DocumentService {
  private final Jdbi jdbi;

  @Autowired
  public DocumentService(Jdbi jdbi) {
    this.jdbi = jdbi;
  }

  public Document createDocument(String name, String contents) {
    final Document document = new Document(UUID.randomUUID(), name, contents);

    this.jdbi.withHandle(handle -> handle.createUpdate(
        """
              INSERT INTO documents (id, name, contents, search_vector)
              VALUES (:id, :name, :contents, to_tsvector(:contents))
          """)
      .bind("id", document.id())
      .bind("name", document.name())
      .bind("contents", document.contents())
      .execute());

    return document;
  }

  public Optional<Document> getDocumentById(UUID id) {
    return this.jdbi.withHandle(handle -> handle.createQuery(
        """
          SELECT id, name, contents FROM documents WHERE id = :id
          """))
      .bind("id", id)
      .mapToBean(Document.class)
      .findFirst();
  }


  public Optional<Document> getDocumentByName(String name) {
    return this.jdbi.withHandle(handle -> handle.createQuery(
        """
          SELECT id, name, contents FROM documents WHERE name = :name
          """))
      .bind("name", name)
      .mapToBean(Document.class)
      .findFirst();
  }

}

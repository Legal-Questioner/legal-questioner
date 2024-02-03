package io.shaded.legalquestionizer.search;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/search")
public class SearchController {
  private final SearchService searchService;

  public SearchController(SearchService searchService) {
    this.searchService = searchService;
  }

  @GetMapping
  public ResponseEntity<SearchResponse> searchDocuments(@RequestParam(required = false) String type, @RequestBody SearchRequest request) {
    return switch (type.toLowerCase()) {
      case "contexts" -> ResponseEntity.ok(new SearchResponse(SearchResponse.Type.CONTEXT, this.searchService.searchContexts(request.input())));
      case "documents" -> ResponseEntity.ok(new SearchResponse(SearchResponse.Type.DOCUMENT, this.searchService.searchDocuments(request.input())));
      default ->  ResponseEntity.ok(new SearchResponse(SearchResponse.Type.ALL,
        this.searchService.search(request.input())));
    };
  }
}

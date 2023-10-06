package darkchoco.rest.controller;

import darkchoco.rest.domain.Article;
import darkchoco.rest.dto.AddArticleRequest;
import darkchoco.rest.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController  // HTTP Response로 객체 데이터를 JSON 형식으로 return
public class BlogApiController {

    private final BlogService blogService;

    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest req) {
        // 클라이언트에서 JSON 데이터를 서버로 보내면, 서버에서는 @RequestBody를 사용하여 HTTP request body에 담긴 값들을
        // 자바객체로 변환시킨다.
        // 서버에서 클라이언트로 Response를 보내려면 @ResponseBody를 사용하여 자바 객체를 HTTP response body로 변환하여
        // 클라이언트로 전송한다.
        Article savedArticle = blogService.save(req);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }
}

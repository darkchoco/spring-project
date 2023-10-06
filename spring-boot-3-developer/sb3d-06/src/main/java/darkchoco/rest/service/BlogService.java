package darkchoco.rest.service;

import darkchoco.rest.domain.Article;
import darkchoco.rest.dto.AddArticleRequest;
import darkchoco.rest.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest req) {
        return blogRepository.save(req.toEntity());
    }

    public List<Article> findAll() {
        return blogRepository.findAll();
    }
}

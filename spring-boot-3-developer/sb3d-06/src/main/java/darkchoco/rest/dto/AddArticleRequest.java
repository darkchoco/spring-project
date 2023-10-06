package darkchoco.rest.dto;

import darkchoco.rest.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * DTO(Data Transfer Object).
 * 계층끼리 데이터를 교환하기 위해 사용하는 Object이다.
 * DAO는 DB와 연결되고 데이터를 조회하고 수정하는데 사용하는 Object이기 때문에 데이터 수정과 관련된 로직이 포함된다.
 * 하지만 DTO는 단순히 전달자 역할만 하는 Object이기 때문에 별도의 비즈니스 로직을 포함하지 않는다.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AddArticleRequest {

    private String title;
    private String content;

    /**
     * DTO를 Entity로 만들어준다.
     */
    public Article toEntity() {
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }
}

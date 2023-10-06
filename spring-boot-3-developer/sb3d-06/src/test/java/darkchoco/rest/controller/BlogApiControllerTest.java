package darkchoco.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import darkchoco.rest.domain.Article;
import darkchoco.rest.dto.AddArticleRequest;
import darkchoco.rest.repository.BlogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class BlogApiControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    BlogRepository blogRepository;

    @BeforeEach
    public void mockMvcSetup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
        blogRepository.deleteAll();
    }

    @DisplayName("addArticle: 블로그 글 추가")
    @Test
    public void addArticle() throws Exception {
        // given
        final String url = "/api/articles";
        final String title = "세계의 끝과 하드보일드 원더랜드";
        final String content = """
                암호를 취급하는 계산사로서 활약하는 나(私)가 자신에게 설치된 장치의 수수께끼를 풀기 위한 해답을 \
                찾아나서는 이야기이다.
                반관반민의 계산사가 소속된 조직(시스템)과 그들을 적대하는 기호사의 조직 공장(팩토리)은, 암호의 작성과 \
                해독의 기술로 번갈아가며 경쟁하고 있다. 계산사인 나는, 암호 처리 중에 최고도의 셔플링(인간의 잠재의식을 \
                이용한 수치 변환술)을 다루는 존재이다. 어느날, 나는 노박사의 비밀 연구소로 호출된다. \
                살이 찐 아가씨(박사의 손녀)의 안내로 야미쿠로가 있는 지하를 빠져나와 연구소에 도착, 박사에게서 셔플링 \
                시스템을 이용한 임무를 의뢰받는다. 아파트로 돌아가 박사에게 받은 선물을 열어보니, \
                거기엔 일각수의 두개골이 들어있었다. 나는 두개골을 조사하러 떠난 도서관에서 레퍼런스 담당의 여인과 \
                만난다. 다음 날 아침, 손녀에게서 박사가 야미쿠로에게 습격당한 것 같다는 전화를 받게 된다. \
                나는 수수께끼 2인조의 습격으로 배에 부상을 입고, 방도 철저하게 파괴당한다. \
                그 후, 손녀가 방으로 나타나 나에게 세계가 끝난다는 소식을 전한다.
                """;
        final AddArticleRequest userRequest = new AddArticleRequest(title, content);

        // when
        ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(userRequest)));

        // then
        result.andExpect(status().isCreated());

        List<Article> articles = blogRepository.findAll();

        assertThat(articles.size()).isEqualTo(1);
        assertThat(articles.get(0).getTitle()).isEqualTo(title);
        assertThat(articles.get(0).getContent()).isEqualTo(content);
    }
}

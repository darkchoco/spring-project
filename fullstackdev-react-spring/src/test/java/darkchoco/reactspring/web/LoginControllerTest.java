package darkchoco.reactspring.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void HttpStatusShouldBeOKWhenCallLogin() throws Exception {
        // Testing authentication with correct credentials
        this.mockMvc.perform(post("/login")
                .content("{\"username\":\"admin\", \"password\":\"admin\"}")
                .header(HttpHeaders.CONTENT_TYPE, "application/json"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}

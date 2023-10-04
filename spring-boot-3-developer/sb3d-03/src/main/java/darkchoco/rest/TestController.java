package darkchoco.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class TestController {

    TestService testService;

    @GetMapping("/test")
    public List<Member> getAllMembers() {
        return testService.getAllMembers();
    }
}

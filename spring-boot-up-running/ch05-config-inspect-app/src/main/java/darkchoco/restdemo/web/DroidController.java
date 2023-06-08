package darkchoco.restdemo.web;

import darkchoco.restdemo.domain.Droid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/droid")
public class DroidController {

    private final Droid droid;

    @GetMapping
    Droid getDroid() {
        return droid;
    }
}

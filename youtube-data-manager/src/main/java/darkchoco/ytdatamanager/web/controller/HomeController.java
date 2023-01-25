package darkchoco.ytdatamanager.web.controller;

import darkchoco.ytdatamanager.YouTube;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final YouTube youTube;

    public HomeController(YouTube youTube) {
        this.youTube = youTube;
    }

    @GetMapping("/")
    String index(Model model) {
        model.addAttribute("channelVideos", //
                youTube.channelVideo("UCBVfhzdqhtJm8x_2Ed-HZXA", //
                        10, YouTube.Sort.VIEW_COUNT));
        return "index";
    }
}

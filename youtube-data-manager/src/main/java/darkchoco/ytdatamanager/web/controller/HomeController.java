package darkchoco.ytdatamanager.web.controller;

import darkchoco.ytdatamanager.YouTube;
import darkchoco.ytdatamanager.web.model.PlaylistItemsResponse;
import darkchoco.ytdatamanager.web.model.PlaylistResponse;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HomeController {

    private final YouTube youTube;

    private final Environment env;

    public HomeController(YouTube youTube, Environment env) {
        this.youTube = youTube;
        this.env = env;
    }

    @GetMapping("/pl")
    public PlaylistResponse allPlaylists() {
        return youTube.allPlaylists(env.getProperty("youtube.channelid"), 20);
    }

    @GetMapping("/plitems")
    public PlaylistItemsResponse allPlaylistItems() {
        return youTube.allPlaylistItems(env.getProperty("youtube.playlistid"), 20);
    }
}

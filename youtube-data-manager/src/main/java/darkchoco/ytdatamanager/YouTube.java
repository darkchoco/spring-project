package darkchoco.ytdatamanager;

import darkchoco.ytdatamanager.web.model.PlaylistItemsResponse;
import darkchoco.ytdatamanager.web.model.PlaylistResponse;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

public interface YouTube {

    @GetExchange("/playlists?part=snippet")
    PlaylistResponse allPlaylists(@RequestParam String channelId,
                                  @RequestParam int maxResults);

    @GetExchange("/playlistItems?part=snippet")
    PlaylistItemsResponse allPlaylistItems(@RequestParam String playlistId,
                                           @RequestParam int maxResults);
}

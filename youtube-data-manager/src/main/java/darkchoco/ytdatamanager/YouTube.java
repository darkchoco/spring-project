package darkchoco.ytdatamanager;

import darkchoco.ytdatamanager.web.model.SearchListResponse;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

public interface YouTube {

    @GetExchange("/search?part=snippet&type=video")
    SearchListResponse channelVideo(@RequestParam String channelId,  //
                                    @RequestParam int maxResults,  //
                                    @RequestParam Sort sort);

    enum Sort {
        DATE("date"), //
        VIEW_COUNT("viewCount"), //
        TITLE("title"), //
        RATING("rating");

        private final String type;

        Sort(String type) {
            this.type = type;
        }
    }
}

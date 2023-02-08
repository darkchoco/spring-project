package darkchoco.ytdatamanager.web.model;

import java.util.Map;

record Snippet(String publishedAt,
               String channelId,
               String title,
               String description,
               Map<String, Thumbnail> thumbnails,
               String channelTitle) {

}

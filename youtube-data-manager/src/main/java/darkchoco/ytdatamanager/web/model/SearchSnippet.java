package darkchoco.ytdatamanager.web.model;

import java.util.Map;

record SearchSnippet(String publishedAt,
                     String channelId,
                     String title,
                     String description,
                     Map<String, SearchThumbnail> thumbnails,
                     String channelTitle) {

}

package darkchoco.ytdatamanager.web.model;

public record PlaylistResponse(String kind,
                               String etag,
                               String nextPageToken,
                               String prevPageToken,
                               PageInfo pageInfo,
                               Item[] items) {

}

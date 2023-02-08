package darkchoco.ytdatamanager.web.model;

public record PlaylistItemsResponse(String kind,
                                    String etag,
                                    String nextPageToken,
                                    String prevPageToken,
                                    PageInfo pageInfo,
                                    Item[] items) {

}

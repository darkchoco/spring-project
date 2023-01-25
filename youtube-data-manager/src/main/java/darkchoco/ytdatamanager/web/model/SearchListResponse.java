package darkchoco.ytdatamanager.web.model;

public record SearchListResponse(String kind,
                                 String etag,
                                 String nextPageToken,
                                 String prevPageToken,
                                 PageInfo pageInfo,
                                 SearchResult[] items) {

}

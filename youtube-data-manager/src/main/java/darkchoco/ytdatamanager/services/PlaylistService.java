package darkchoco.ytdatamanager.services;

import darkchoco.ytdatamanager.web.model.PlaylistResponse;

public interface PlaylistService {

    Iterable<PlaylistResponse> findAll();
}

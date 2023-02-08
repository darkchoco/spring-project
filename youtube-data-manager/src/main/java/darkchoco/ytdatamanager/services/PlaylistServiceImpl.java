package darkchoco.ytdatamanager.services;

import darkchoco.ytdatamanager.web.model.PlaylistResponse;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PlaylistServiceImpl implements PlaylistService {

    private final Map<String, PlaylistResponse> playlists = new ConcurrentHashMap<>();

    @Override
    public Iterable<PlaylistResponse> findAll() {
        return playlists.values();
    }
}

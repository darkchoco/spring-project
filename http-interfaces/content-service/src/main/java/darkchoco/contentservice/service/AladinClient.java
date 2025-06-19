package darkchoco.contentservice.service;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

public interface AladinClient {

    @GetExchange("/ItemSearch.aspx")
    String findAllByQuery(@RequestParam String TTBKey,
                          @RequestParam String Query,
                          @RequestParam int Start,
                          @RequestParam int MaxResults,
                          @RequestParam String Output,
                          @RequestParam int Version);
}

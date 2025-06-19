package darkchoco.contentservice.controller;

import darkchoco.contentservice.service.AladinClient;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AladinController {

    public final AladinClient aladinClient;

    public AladinController(AladinClient aladinClient) {
        this.aladinClient = aladinClient;
    }
}

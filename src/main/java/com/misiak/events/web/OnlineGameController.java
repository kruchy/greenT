package com.misiak.events.web;

import com.misiak.events.model.Clan;
import com.misiak.events.model.Players;
import com.misiak.events.service.EventScheduler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@RestController
public class OnlineGameController {

    private final EventScheduler eventScheduler;

    public OnlineGameController(EventScheduler eventScheduler) {
        this.eventScheduler = eventScheduler;
    }

    @PostMapping(value = "/onlinegame/calculate", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<List<Clan>>> calculate(@RequestBody Players players) {
        return ok(eventScheduler.scheduleClans(players.groupCount(), players.clans()));
    }
}

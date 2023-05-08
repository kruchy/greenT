package com.kruchy.events.service;

import com.kruchy.events.model.Clan;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EventSchedulerTest {

    EventScheduler eventScheduler = new EventScheduler();

    @Test
    public void shouldScheduleSmallEvent() throws IOException {
        int groupCount = 6;
        Clan clanOf4 = clan(4, 50);
        Clan clanOf2 = clan(2, 70);
        Clan clanOf6 = clan(6, 60);
        Clan clanOf1 = clan(1, 15);
        Clan clanOf5 = clan(5, 40);
        Clan clanOf3 = clan(3, 45);
        Clan clanOf1With12 = clan(1, 12);
        Clan clanOf4With40 = clan(4, 40);
        List<Clan> clans = List.of(
                clanOf4,
                clanOf2,
                clanOf6,
                clanOf1,
                clanOf5,
                clanOf3,
                clanOf1With12,
                clanOf4With40
        );

        List<List<Clan>> expectedGroups = List.of(
                of(clanOf2, clanOf4),
                of(clanOf6),
                of(clanOf3, clanOf1, clanOf1With12),
                of(clanOf4With40),
                of(clanOf5)
        );

        List<List<Clan>> actualGroups = eventScheduler.scheduleClans(groupCount, clans);

        assertEquals(expectedGroups, actualGroups);
    }

    private static Clan clan(int numberOfPlayers, int points) {
        return new Clan(numberOfPlayers, points);
    }


}
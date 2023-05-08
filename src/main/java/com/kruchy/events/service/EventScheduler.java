package com.kruchy.events.service;

import com.kruchy.events.model.Clan;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EventScheduler {

    public List<List<Clan>> scheduleClans(int groupCount, List<Clan> clans) {
        List<List<Clan>> scheduledGroups = new ArrayList<>();
        Queue<Clan> clanQueue = prepareClanQueue(clans);

        while (!clanQueue.isEmpty()) {
            List<Clan> group = new ArrayList<>();
            int remainingPlayersInGroup = groupCount;
            boolean clanFits = true;

            while (remainingPlayersInGroup > 0 && !clanQueue.isEmpty() && clanFits) {
                Clan currentClan = findFittingClan(clanQueue, remainingPlayersInGroup);

                if (currentClan != null) {
                    group.add(currentClan);
                    clanQueue.remove(currentClan);
                    remainingPlayersInGroup -= currentClan.numberOfPlayers();
                } else {
                    clanFits = false;
                }
            }

            if (!group.isEmpty()) {
                scheduledGroups.add(group);
            }
        }

        return scheduledGroups;
    }

    // Helper function to prepare the clan queue by sorting clans based on the given criteria
    private Queue<Clan> prepareClanQueue(List<Clan> clans) {
        // Sort by points descending, then by number of players ascending
        Comparator<Clan> clanComparator = Comparator
                .comparingInt(Clan::points).reversed()
                .thenComparingInt(Clan::numberOfPlayers);

        return new LinkedList<>(clans.parallelStream()
                .sorted(clanComparator)
                .toList());
    }

    // Helper function to find the first clan that fits in the remaining space
    private Clan findFittingClan(Queue<Clan> queue, int space) {
        return queue.stream()
                .filter(clan -> clan.numberOfPlayers() <= space)
                .findFirst()
                .orElse(null);
    }
}

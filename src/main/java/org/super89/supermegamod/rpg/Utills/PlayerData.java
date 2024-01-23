package org.super89.supermegamod.rpg.Utills;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class PlayerData {
    private UUID playerId;
    private Set<String> visitedZones;

    public PlayerData(UUID playerId) {
        this.playerId = playerId;
        this.visitedZones = new HashSet<>();
    }

    public UUID getPlayerId() {
        return playerId;
    }

    public Set<String> getVisitedZones() {
        return visitedZones;
    }

    public void addVisitedZone(String zoneName) {
        visitedZones.add(zoneName);
    }
}

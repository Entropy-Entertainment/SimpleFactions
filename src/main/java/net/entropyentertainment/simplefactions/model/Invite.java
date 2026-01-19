package net.entropyentertainment.simplefactions.model;

import java.sql.Time;

public record Invite(int id, int senderID, int ReceiverID, int FactionID, Time time) {
}

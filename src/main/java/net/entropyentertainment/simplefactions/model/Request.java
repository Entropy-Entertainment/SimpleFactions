package net.entropyentertainment.simplefactions.model;

public class Request {

    private Integer id;
    private int senderId;
    private int factionId;
    private long created_at;


    public Request(Integer id, int senderId, int factionId, Long created_at){
        this.id = id;
        this.senderId = senderId;
        this.factionId = factionId;
        this.created_at = created_at;
    }

    public Request(int receiverID, int factionID){
        this(null, receiverID, factionID, null);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setReceiverID(int receiverID) {
        this.senderId = receiverID;
    }

    public int getFactionId() {
        return factionId;
    }

    public void setFactionId(int factionId) {
        this.factionId = factionId;
    }

    public long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(long created_at) {
        this.created_at = created_at;
    }
}

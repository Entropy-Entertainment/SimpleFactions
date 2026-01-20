package net.entropyentertainment.simplefactions.model;

public class Member {

    private int id;
    private String name;
    private Integer factionId;

    public Member(int id, String name, Integer factionId){
        this.id = id;
        this.name = name;
        this.factionId = factionId;
    }

    public Member(int id, String name){
        this(id, name, null);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFactionId() {
        return factionId;
    }

    public void setFactionId(Integer factionId) {
        this.factionId = factionId;
    }
}

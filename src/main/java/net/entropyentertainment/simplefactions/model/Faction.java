package net.entropyentertainment.simplefactions.model;

public class Faction
{
    private Integer id;
    private String name;
    private int ownerId;
    private int color;

    public Faction(Integer id, String name, int ownerId, int color){
        this.id = id;
        this.name = name;
        this.ownerId = ownerId;
        this.color = color;
    }

    public Faction(String name, int ownerID, int color){
        this(null, name, ownerID, color);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}

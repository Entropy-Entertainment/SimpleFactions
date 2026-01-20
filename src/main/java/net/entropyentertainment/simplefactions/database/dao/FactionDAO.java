package net.entropyentertainment.simplefactions.database.dao;

import net.entropyentertainment.simplefactions.model.Faction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FactionDAO {
    private Connection connection;

    public FactionDAO(Connection connection){
        this.connection = connection;

        try (Statement statement = connection.createStatement())
        {
            statement.execute("""
                CREATE TABLE IF NOT EXISTS Factions (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    owner_id INTEGER NOT NULL,
                    color INTEGER NOT NULL,
                
                        FOREIGN KEY (owner_id)
                        REFERENCES Members(id)
                        ON DELETE CASCADE
                );
                """);
            statement.execute("""
                CREATE UNIQUE INDEX IF NOT EXISTS uq_factions_name
                ON Factions(name);
            """);
            statement.execute("""
                CREATE INDEX IF NOT EXISTS idx_factions_owner
                ON Factions(owner_id);
            """);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Faction insert(Faction faction){
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO Factions(name, owner_id, color) VALUES(?, ?, ?)", Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, faction.getName());
            ps.setInt(2, faction.getOwnerId());
            ps.setInt(3, faction.getColor());
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()){
                if(keys.next()){
                    faction.setId(keys.getInt(1));
                }
            }
            return faction;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Faction faction){
        try (PreparedStatement ps = connection.prepareStatement("UPDATE Factions SET name = ?, owner_id = ?, color = ? WHERE id = ?")){
            ps.setString(1, faction.getName());
            ps.setInt(2, faction.getOwnerId());
            ps.setInt(3, faction.getColor());
            ps.setInt(4, faction.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Faction findById(int factionId){
        try (PreparedStatement ps = connection.prepareStatement("SELECT id, name, owner_id, color FROM Factions where id = ?")){
            ps.setInt(1, factionId);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                return new Faction(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("owner_id"),
                        rs.getInt("color")
                );
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Faction findByOwnerId(int ownerId){
        try (PreparedStatement ps = connection.prepareStatement("SELECT id, name, owner_id, color FROM Factions where owner_id = ?")){
            ps.setInt(1, ownerId);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                return new Faction(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("owner_id"),
                        rs.getInt("color")
                );
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Faction> findAll(){
        List<Faction> factions = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement("SELECT id, name, owner_id, color FROM Factions")){
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                factions.add(new Faction(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("owner_id"),
                        rs.getInt("color")
                ));
            }

            return factions;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteById(int factionID){
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM Factions WHERE id = ?")){
            ps.setInt(1, factionID);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
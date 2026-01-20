package net.entropyentertainment.simplefactions.database.dao;

import net.entropyentertainment.simplefactions.model.Invite;
import net.entropyentertainment.simplefactions.model.Request;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RequestDAO {

    private Connection connection;

    public RequestDAO(Connection connection){
        this.connection = connection;

        try (Statement statement = connection.createStatement())
        {
            statement.execute("""
                CREATE TABLE IF NOT EXISTS Requests (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    sender_id INTEGER NOT NULL,
                    faction_id INTEGER NOT NULL,
                    created_at INTEGER NOT NULL,
                
                    FOREIGN KEY (faction_id)
                        REFERENCES Factions(id)
                        ON DELETE CASCADE,
                
                    FOREIGN KEY (sender_id)
                        REFERENCES Members(id)
                        ON DELETE CASCADE
                );
                """);
            statement.execute("""
                CREATE INDEX IF NOT EXISTS idx_requests_faction
                ON Requests(faction_id);
                """);
            statement.execute("""
                CREATE UNIQUE INDEX IF NOT EXISTS uq_requests_sender_faction
                ON Requests(sender_id, faction_id);
               """);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Request insert(Request request){
        long nowEpoch = System.currentTimeMillis() / 1000;

        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO Requests(sender_id, faction_id, created_at) VALUES(?, ?, ?)", Statement.RETURN_GENERATED_KEYS)){
            ps.setInt(1, request.getSenderId());
            ps.setInt(2, request.getFactionId());
            ps.setLong(3, nowEpoch);
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    return new Request(id, request.getSenderId(), request.getFactionId(), nowEpoch);
                } else {
                    throw new SQLException("Failed to retrieve generated ID for invite");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Request findById(int id){
        try (PreparedStatement ps = connection.prepareStatement("SELECT id, sender_id, faction_id, created_at FROM Requests where id = ?")){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                return new Request(
                        rs.getInt("id"),
                        rs.getInt("sender_id"),
                        rs.getInt("faction_id"),
                        rs.getLong("created_at")
                );
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Request> findAllBySenderId(int senderId){
        List<Request> requests = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement("SELECT id, sender_id, faction_id, created_at FROM Requests where sender_id = ?")){
            ps.setInt(1, senderId);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                requests.add(new Request(
                        rs.getInt("id"),
                        rs.getInt("sender_id"),
                        rs.getInt("faction_id"),
                        rs.getLong("created_at")
                ));
            }
            return requests;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Request> findAllByFactionId(int factionId){
        List<Request> requests = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement("SELECT id, sender_id, faction_id, created_at FROM Requests where faction_id = ?")){
            ps.setInt(1, factionId);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                requests.add(new Request(
                        rs.getInt("id"),
                        rs.getInt("sender_id"),
                        rs.getInt("faction_id"),
                        rs.getLong("created_at")
                ));
            }
            return requests;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteById(int id){
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM Requests WHERE id = ?")){
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteExpired(long lifespan){
        try (PreparedStatement ps = connection.prepareStatement(" DELETE FROM Requests WHERE created_at < strftime('%s','now') - ?")){
            ps.setLong(1, lifespan);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

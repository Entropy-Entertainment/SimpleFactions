package net.entropyentertainment.simplefactions.database.dao;

import net.entropyentertainment.simplefactions.model.Member;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {

    private final Connection connection;

    public MemberDAO(Connection connection){
        this.connection = connection;

        try (Statement statement = connection.createStatement())
        {
            statement.execute("""
                CREATE TABLE IF NOT EXISTS Members (
                    id INTEGER PRIMARY KEY,
                    name TEXT NOT NULL,
                    faction_id INTEGER,
                
                    FOREIGN KEY (faction_id)
                        REFERENCES Factions(id)
                        ON DELETE SET NULL
                );
                """);
            statement.execute("""
                CREATE INDEX IF NOT EXISTS idx_members_faction
                ON Members(faction_id);
                """);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insert(Member member){
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO Members(id, name, faction_id) VALUES(?, ?, ?)")){
            ps.setInt(1, member.getId());
            ps.setString(2, member.getName());
            if (member.getFactionId() == null) {
                ps.setNull(3, Types.INTEGER);
            } else {
                ps.setInt(3, member.getFactionId());
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Member member){
        try (PreparedStatement ps = connection.prepareStatement("UPDATE Members SET name = ?, faction_id = ? WHERE id = ?")){
            ps.setString(1, member.getName());
            if (member.getFactionId() == null) {
                ps.setNull(2, Types.INTEGER);
            } else {
                ps.setInt(2, member.getFactionId());
            }
            ps.setInt(3, member.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Member findById(int memberID){
        try (PreparedStatement ps = connection.prepareStatement("SELECT id, name, faction_id FROM Members where id = ?")){
            ps.setInt(1, memberID);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                Integer factionId = rs.getInt("faction_id");
                if(rs.wasNull()) factionId = null;

                return new Member(
                        rs.getInt("id"),
                        rs.getString("name"),
                        factionId
                );
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Member> findAll(){
        List<Member> members = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement("SELECT id, name, faction_id FROM Members")){
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Integer factionId = rs.getInt("faction_id");
                if(rs.wasNull()) factionId = null;

                members.add(new Member(
                        rs.getInt("id"),
                        rs.getString("name"),
                        factionId
                ));
            }

            return members;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteById(int memberID){
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM Members WHERE id = ?")){
            ps.setInt(1, memberID);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

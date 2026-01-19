package net.entropyentertainment.simplefactions.database.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class MemberDAO {

    private Connection connection;

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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

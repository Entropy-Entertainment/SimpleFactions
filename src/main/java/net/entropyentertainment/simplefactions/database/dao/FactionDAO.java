package net.entropyentertainment.simplefactions.database.dao;

import net.entropyentertainment.simplefactions.model.Faction;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

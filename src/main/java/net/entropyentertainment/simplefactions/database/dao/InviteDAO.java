package net.entropyentertainment.simplefactions.database.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class InviteDAO {

    private Connection connection;

    public InviteDAO(Connection connection){
        this.connection = connection;

        try (Statement statement = connection.createStatement())
        {
            statement.execute("""
                CREATE TABLE IF NOT EXISTS Invites (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    sender_id INTEGER NOT NULL,
                    receiver_id INTEGER NOT NULL,
                    time INTEGER NOT NULL,
                
                    FOREIGN KEY (sender_id)
                        REFERENCES Members(id)
                        ON DELETE CASCADE,
                
                    FOREIGN KEY (receiver_id)
                        REFERENCES Members(id)
                        ON DELETE CASCADE
                );
                """);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

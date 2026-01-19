package net.entropyentertainment.simplefactions.database;

import net.entropyentertainment.simplefactions.database.dao.FactionDAO;
import net.entropyentertainment.simplefactions.database.dao.InviteDAO;
import net.entropyentertainment.simplefactions.database.dao.MemberDAO;

import java.io.File;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Database {

    private static Database instance;

    private Connection connection;
    private FactionDAO factionDAO;
    private MemberDAO memberDAO;
    private InviteDAO inviteDAO;

    public Database(Path databasePath) {
        instance = this;

        try {
            File dbDir = databasePath.toFile();
            if (!dbDir.exists()) {
                boolean created = dbDir.mkdirs();
                if (!created) {
                    throw new RuntimeException("Failed to create directories: " + databasePath);
                }
            }

            File dbFile = new File(dbDir, "SimpleFactions.db");
            String jdbcUrl = "jdbc:sqlite:" + dbFile.getAbsolutePath();

            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(jdbcUrl);
            connection.createStatement().execute("PRAGMA foreign_keys = ON");

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static synchronized void initialize(Path databasePath) {
        if (instance != null) {
            throw new IllegalStateException("Database already initialized");
        }
        instance = new Database(databasePath);
    }

    public static Database getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Database not initialized");
        }
        return instance;
    }

    public FactionDAO factions() {
        if(factionDAO == null){
            factionDAO = new FactionDAO(connection);
        }
        return factionDAO;
    }

    public MemberDAO members() {
        if(memberDAO == null){
            memberDAO = new MemberDAO(connection);
        }
        return memberDAO;
    }

    public InviteDAO invites() {
        if(inviteDAO == null){
            inviteDAO = new InviteDAO(connection);
        }
        return inviteDAO;
    }
}
